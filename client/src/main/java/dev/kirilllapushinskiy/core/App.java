package dev.kirilllapushinskiy.core;

import dev.kirilllapushinskiy.commands.Command;
import dev.kirilllapushinskiy.commands.CommandPackage;
import dev.kirilllapushinskiy.commands.TestCommand1;
import dev.kirilllapushinskiy.commands.TestCommand2;
import dev.kirilllapushinskiy.handlers.CommandHandler;
import dev.kirilllapushinskiy.network.Client;
import dev.kirilllapushinskiy.network.Communicator;

import java.io.IOException;

public class App {

    static private final Command EXIT = Exit.init();
    static private final Command TEST1 = TestCommand1.init();
    static private final Command TEST2 = TestCommand2.init();
    static private final Command INFO = Info.init();

    public static void main(String[] args) {

        CommandHandler.registration(
                EXIT,
                TEST1,
                TEST2,
                INFO
        );

        try {
            Client.initialization();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("\n[ERROR] Connection could not be set up. :(");
            return;
        }

        while (true) {
            Command command = CommandHandler.start();

            if (command.equals(EXIT)) {
                System.out.println("\nbye-bye! :)");
                break;
            } else if (command.equals(INFO)) {
                System.out.println("by kirilllapushinskiy: https://t.me/kirilllapushinskiy\n");
                continue;
            }

            CommandPackage pack = CommandPackage.pack(command);

            try {
                Communicator.remoteCommandExecutionProcess(pack);
            } catch (IOException e) {
                System.out.println("[ERROR] The command could not be sent to the server. Please, try later...\n");
            } catch (ClassNotFoundException e) {
                System.out.println("[ERROR] The response from the server could not be processed!");
            }
        }
    }

}
