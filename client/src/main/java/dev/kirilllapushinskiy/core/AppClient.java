package dev.kirilllapushinskiy.core;

import dev.kirilllapushinskiy.commands.*;
import dev.kirilllapushinskiy.handlers.CommandHandler;
import dev.kirilllapushinskiy.menu.*;
import dev.kirilllapushinskiy.network.Client;
import dev.kirilllapushinskiy.network.Communicator;

import java.io.IOException;
import java.net.InetAddress;

public class AppClient {

    static private final Command EXIT = Exit.init();
    static private final Command INFO = Info.init();
    static private final Command HELP = Help.init();
    static private final Command HISTORY = History.init();
    static private final Command SHOW = Show.init();
    static private final Command REMOVEBYID = RemoveById.init();
    static private final Command MAXSOUNDTRACK = MaxSoundtrack.init();
    static private final Command ADD = Add.init();
    static private final Command CLEAR = Clear.init();
    static private final Command UPDATE = Update.init();

    public static void main(String[] args) {

        CommandHandler.registration(
                EXIT,
                INFO,
                HELP,
                HISTORY,
                SHOW,
                REMOVEBYID,
                MAXSOUNDTRACK,
                ADD,
                CLEAR,
                UPDATE
        );

        try {
            Client.initialization(InetAddress.getLocalHost(), 8000);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("\nConnection could not be set up. :(");
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
                System.out.println("The command could not be sent to the server. Please, try later...\n");
            } catch (ClassNotFoundException e) {
                System.out.println("The response from the server could not be processed!");
            }
        }
    }

}
