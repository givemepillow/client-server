package dev.kirilllapushinskiy.network;

import dev.kirilllapushinskiy.commands.CommandPackage;
import dev.kirilllapushinskiy.communication.AbstractCommunicator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientCommunicator extends AbstractCommunicator {

    private static final int DEFAULT_SERVER_PORT = 5000;

    private static DatagramChannel datagramChannel;

    private static InetSocketAddress serverAddress;

    static private void sendCommandPackage(CommandPackage commandPackage) throws IOException {
        ByteBuffer bytePackage = serialize(commandPackage);
        datagramChannel.send(bytePackage, serverAddress);
    }

    static public void start(CommandPackage commandPackage) throws IOException {
        sendCommandPackage(commandPackage);
    }

}
