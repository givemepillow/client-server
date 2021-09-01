package dev.kirilllapushinskiy.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public final class Client {

    private static final int DEFAULT_SERVER_PORT = 5000;

    private static DatagramChannel channel;

    private static InetSocketAddress serverAddress;

    private Client() {}

    private static final Client INSTANCE = new Client();

    public static Client set() {
        return INSTANCE;
    }

    public void send(ByteBuffer buffer) throws IOException {
        channel.send(buffer, serverAddress);
    }

    public void receive(ByteBuffer buffer) throws IOException {
        DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.capacity());
        channel.socket().receive(packet);
        //channel.receive(buffer);
    }

    public static void initialization() throws IOException {
        initialization(InetAddress.getLocalHost(), DEFAULT_SERVER_PORT);
    }

    public static void initialization(InetAddress address, int port) throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        serverAddress = new InetSocketAddress(address, port);
        channel = datagramChannel;
        channel.socket().setSoTimeout(3000);
    }

}
