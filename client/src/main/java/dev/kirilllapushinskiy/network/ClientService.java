package dev.kirilllapushinskiy.network;

import dev.kirilllapushinskiy.commands.CommandPackage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientService {

    private static final int DEFAULT_SERVER_PORT = 5000;

    private static DatagramChannel datagramChannel;

    private static InetSocketAddress serverAddress;

    static public void initialization() throws IOException {
        initialization(InetAddress.getLocalHost(), DEFAULT_SERVER_PORT);
    }

    static public void initialization(InetAddress address, int port) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        serverAddress = new InetSocketAddress(address, port);
        datagramChannel = channel;
    }

    static public void sendCommandPackage(CommandPackage commandPackage) throws IOException {

        ByteBuffer bytePackage = serialize(commandPackage);
        datagramChannel.send(bytePackage, serverAddress);
    }

    static private ByteBuffer serialize(CommandPackage commandPackage) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(commandPackage);
        out.flush();
        return ByteBuffer.wrap(bos.toByteArray());
    }

}
