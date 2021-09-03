package dev.kirilllapushinskiy.core;

import dev.kirilllapushinskiy.commands.Command;
import dev.kirilllapushinskiy.commands.CommandPackage;
import dev.kirilllapushinskiy.communication.ErrorMessage;
import dev.kirilllapushinskiy.communication.FinishMessage;
import dev.kirilllapushinskiy.communication.Message;
import dev.kirilllapushinskiy.communication.Serializator;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class Server {

    private static ClientsCollection clients;

    private static DatagramChannel channel;

    private static Selector selector;

    private static ByteBuffer buffer;

    public static void initialization(int clientsCount) throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8000));
        channel = datagramChannel;
        selector = Selector.open();
        channel.configureBlocking(false);
        clients = new ClientsCollection(clientsCount);
        buffer = ByteBuffer.allocate(2048);
    }


    public static void start() {

        try {
            channel.register(selector, SelectionKey.OP_READ);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                System.out.println("wait");
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
                            SocketAddress address = ch.receive(buffer);
                            SessionClient client;

                            System.out.println(address.toString());

                            if (clients.conclude(address)) {
                                System.out.println("old");
                                client = clients.getClient(address);
                            } else {
                                System.out.println("new");
                                client = new SessionClient(address);
                                clients.add(client);
                            }


                            try {
                                Object o = Serializator.deserialize(buffer);

                                if (o instanceof CommandPackage) {
                                    CommandPackage pack = (CommandPackage) o;
                                    Command command = ServerCommandHandler.handle(pack);

                                    if (command.withArgs()) {
                                        client.setArgs(command.getCommandArgs());
                                    }

                                    System.out.println("run");
                                    command.run(client);
                                } else if (o instanceof Message) {
                                    Message msg = (Message) o;
                                    Command command = ServerCommandHandler.handle(client.currentCommand);
                                    command.run(client);
                                }


                            } catch (IllegalArgumentException | ClassNotFoundException e) {
                                Message message = new ErrorMessage(e.getMessage());
                                client.setMessage(message);
                                client.setState(0);
                                client.setCurrentCommand(null);
                            }
                            buffer.clear();




                            key.interestOps(SelectionKey.OP_WRITE);

                        } else if (key.isWritable()) {

                            DatagramChannel ch = (DatagramChannel)key.channel();

                            for (SessionClient c : clients) {
                                Message msg = c.getMessage();
                                if (msg instanceof ErrorMessage || msg instanceof FinishMessage) {
                                    c.setState(0);
                                    c.setCurrentCommand(null);
                                }
                                c.sendResponse(ch);
                            }

                            key.interestOps(SelectionKey.OP_READ);

                        }
                    } catch (IOException e) {
                        System.err.println("glitch, continuing... " +(e.getMessage()!=null?e.getMessage():""));
                    }
                }
            } catch (IOException e) {
                System.err.println("glitch, continuing... " +(e.getMessage()!=null?e.getMessage():""));
            }
        }

    }

}
