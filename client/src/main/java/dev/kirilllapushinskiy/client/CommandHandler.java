package dev.kirilllapushinskiy.client;
import dev.kirilllapushinskiy.commands.Command;
import dev.kirilllapushinskiy.commands.CommandCollection;
import dev.kirilllapushinskiy.commands.CommandPackage;

import java.util.Arrays;
import java.util.Scanner;

public class CommandHandler {

    static private final String EXIT = "exit";

    static private final Scanner scanner = new Scanner(System.in);

    static private final CommandCollection commands = new CommandCollection(10);

    public static void registration(Command... commands) {
        for (Command c : commands)
            if (CommandHandler.commands.contains(c)) {
                throw new IllegalStateException(c+ "already in commands!");
            } else {
                CommandHandler.commands.add(c);
            }
    }

    public static void start() {
        if (commands.isEmpty())
            throw new IllegalStateException("No one command had been registered!");
        String line;
        while (true) {
            System.out.print("Enter command: ");
            line = scanner.nextLine();
            System.out.println("Read: " + line + ".");
            if (line.equalsIgnoreCase(EXIT)) {
                break;
            } else if (commands.conclude(line)) {
                CommandPackage p = commands.getCommandPackage(line);
                System.out.println(p.getPackedCommandName());
                System.out.println(Arrays.toString(p.getPackedCommandArgs()));
            } else {
                System.out.println("Command '" + line + "' not registered!");
            }
        }
        System.out.println("Bye-bye!");
    }

}
