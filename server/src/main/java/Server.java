import common.CommandPackage;
import common.Response;
import common.Serializator;
import common.Session;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    private Server() {}

    private static DatagramChannel channel;

    private static Selector selector;

    private static final ByteBuffer buffer = ByteBuffer.allocate(2048 * 25);

    private static final SessionCollection sessions = SessionCollection.getSessions();

    public static void init() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), 6699));
        channel = datagramChannel;
        selector = Selector.open();
        channel.configureBlocking(false);
    }

    public static void start() {
        ExecutorService executorPool = Executors.newFixedThreadPool(50);
        try {
            channel.register(selector, SelectionKey.OP_READ);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                selector.select();
                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    try {
                        SelectionKey key = selectedKeys.next();
                        selectedKeys.remove();

                        if (!key.isValid()) {
                            continue;
                        }

                        if (key.isReadable()) {
                            // Получаем данные по UDP.
                            DatagramChannel ch = (DatagramChannel) key.channel();
                            SocketAddress address= ch.receive(buffer);

                            // Устанавливаем сессию.
                            if (!sessions.contains(address)) {
                                sessions.add(new ClientSession(address));
                            }
                            Session session = sessions.get(address);

                            // Извлекаем команду из буфера.
                            CommandPackage pack = (CommandPackage) Serializator.deserialize(buffer);

                            // Выполнение команды в отдельном потоке.
                            executorPool.submit(() -> exec(pack, session, key));

                            // Отчистка буфера.
                            Server.buffer.clear();

                            //key.interestOps(SelectionKey.OP_WRITE);

                        } else if (key.isWritable()) {

                            DatagramChannel ch = (DatagramChannel) key.channel();

                            for (ClientSession s : sessions) {
                                // Если по каким-то причинам поток исполняющий команду ещё не записал ответ,
                                // про пропускаем команду.
                                if (s.isReady())
                                    s.sendResponse(ch);
                            }

                            key.interestOps(SelectionKey.OP_READ);

                        }
                    } catch (IOException e) {
                        System.err.println("Ошибка селектора... " + (e.getMessage() != null ? e.getMessage() : ""));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                System.err.println("Ошибка итерации по селектору... " + (e.getMessage() != null ? e.getMessage() : ""));
            }

        }

    }

    private static void exec(CommandPackage pack, Session session, SelectionKey key) {
        // Запускаем команду на сервере. Не основной поток.
        try {
            CommandHandler.execute(pack, session);
        } catch (IllegalStateException e) {
            session.setResponse(new Response(e.getMessage()));
        }

        // Как только записали, говорим основному потоку, то что он может уже отправлять ответ.
        key.interestOps(SelectionKey.OP_WRITE);
        // Нужно разбудить селектор (уведомить о том, что что-то изменилось). Это необходимо из-за того, что
        // смена ожидаемого события произошла в другом (текущем) потоке и основной поток в котором находится селектор
        // ничего не узнал, так как селектор пробуждается сам только от событий в своём же потоке.
        selector.wakeup();

    }

}


