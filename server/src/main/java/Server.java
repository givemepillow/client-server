import common.CommandPackage;
import common.Response;
import common.Serializator;

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


public class Server {

    private Server() {}

    private static DatagramChannel channel;

    private static Selector selector;

    private static final ByteBuffer buffer = ByteBuffer.allocate(2048);

    private static final SessionCollection sessions = SessionCollection.getSessions();

    public static void init() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), 6690));
        channel = datagramChannel;
        selector = Selector.open();
        channel.configureBlocking(false);
    }

    public static void start() {

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
                            DatagramChannel ch = (DatagramChannel) key.channel();
                            buffer.clear();
                            SocketAddress address = ch.receive(buffer);

                            if (!sessions.contains(address)) {
                                sessions.add(new ClientSession(address));
                            }

                            CommandPackage pack = (CommandPackage) Serializator.deserialize(buffer);

                            CommandHandler.execute(pack, sessions.get(address));

                            key.interestOps(SelectionKey.OP_WRITE);

                        } else if (key.isWritable()) {

                            DatagramChannel ch = (DatagramChannel) key.channel();

                            for (ClientSession s : sessions) {
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

}


