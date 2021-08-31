package dev.kirilllapushinskiy.communication;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public abstract class AbstractCommunicator {

    private static final int DEFAULT_SERVER_PORT = 5000;

    private static DatagramChannel datagramChannel;

    private static InetSocketAddress serverAddress;

    protected AbstractCommunicator() {}

    public static void initialization() throws IOException {
        initialization(InetAddress.getLocalHost(), DEFAULT_SERVER_PORT);
    }

    public static void initialization(InetAddress address, int port) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        serverAddress = new InetSocketAddress(address, port);
        datagramChannel = channel;
    }

    static protected ByteBuffer serialize(Serializable object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(object);
        out.flush();
        return ByteBuffer.wrap(bos.toByteArray());
    }

    static protected Object deserialize(ByteBuffer buffer) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(buffer.array());
        ObjectInputStream in = new ObjectInputStream(bis);
        return in.readObject();
    }

}
