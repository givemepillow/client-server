package dev.kirilllapushinskiy.network;

import dev.kirilllapushinskiy.commands.Command;
import dev.kirilllapushinskiy.commands.CommandPackage;
import dev.kirilllapushinskiy.communication.AbstractCommunicator;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ClientCommunicator extends AbstractCommunicator {

    static private void sendCommandPackage(CommandPackage commandPackage) throws IOException {
        ByteBuffer bytePackage = serialize(commandPackage);
        datagramChannel.send(bytePackage, serverAddress);
    }

    static public void start(CommandPackage commandPackage) throws IOException {
        sendCommandPackage(commandPackage);
    }

}
