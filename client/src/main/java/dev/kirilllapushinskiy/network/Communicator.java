package dev.kirilllapushinskiy.network;

import dev.kirilllapushinskiy.commands.CommandPackage;
import dev.kirilllapushinskiy.communication.*;
import dev.kirilllapushinskiy.core.Utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class Communicator {

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
                 System.out.println("(SERVER) [ERROR] " + message.getMessage() + "\n");
                 accomplishment = false;
             } else if (message instanceof PromptMessage) {
                 System.out.print("(SERVER) " + message.getMessage().trim() + " ");
                 String line = scanner.nextLine(); // lol fix: next -> nextLine )))
                 AnswerMessage answer = new AnswerMessage(line);
                 sendMessage(answer);
             } else if (message instanceof AnswerMessage) {
                System.out.println("(SERVER) + " + message.getMessage() + "\n");
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
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        client.receive(buffer);
        return (Message) Serializator.deserialize(buffer);
    }

}