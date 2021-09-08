package dev.kirilllapushinskiy.network;

import dev.kirilllapushinskiy.commands.CommandPackage;
import dev.kirilllapushinskiy.communication.*;
import dev.kirilllapushinskiy.core.Utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class Communicator {

    private static final int BUFFER_SIZE = 1024 * 12;

    private static final Scanner scanner = Utils.getScanner();

    private static final Client client = Client.set();

    static private void sendCommandPackage(CommandPackage commandPackage) throws IOException {
        ByteBuffer bytePackage = Serializator.serialize(commandPackage);
        client.send(bytePackage);
    }

    static public void remoteCommandExecutionProcess(CommandPackage commandPackage) throws IOException, ClassNotFoundException {
        sendCommandPackage(commandPackage);
        boolean accomplishment = true;

        while (accomplishment) {
            Message message = receiveMassage();
             if (message instanceof ErrorMessage) {
                 System.out.println("[SERVER] " + message.getMessage());
                 accomplishment = false;
             } else if (message instanceof PromptMessage || message instanceof AnswerMessage) {
                 System.out.print("[SERVER] " + message.getMessage().trim() + " ");
                 String line = scanner.nextLine(); // lol fix: next -> nextLine )))
                 AnswerMessage answer = new AnswerMessage(line);
                 sendMessage(answer);
             } else if (message instanceof FinishMessage) {
                System.out.println("[SERVER] " + message.getMessage());
                System.out.println();
                accomplishment = false;
             }
        }
    }

    static protected void sendMessage(Message message) throws IOException {
        ByteBuffer byteMessage = Serializator.serialize(message);
        client.send(byteMessage);
    }

    static protected Message receiveMassage() throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        client.receive(buffer);
        return (Message) Serializator.deserialize(buffer);
    }

}
