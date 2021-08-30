package dev.kirilllapushinskiy;

import dev.kirilllapushinskiy.commands.Command;
import dev.kirilllapushinskiy.commands.CommandPackage;
import dev.kirilllapushinskiy.commands.TestCommand1;
import dev.kirilllapushinskiy.commands.TestCommand2;
import dev.kirilllapushinskiy.handlers.CommandHandler;
import dev.kirilllapushinskiy.network.ClientService;

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
            ClientService.initialization();
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

            System.out.println(pack.getPackedCommandName());
        }
    }

}
