package dev.kirilllapushinskiy.client;
import dev.kirilllapushinskiy.commands.Command;
import dev.kirilllapushinskiy.commands.CommandPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandHandler {

    static private final String EXIT = "exit";

    static private final Scanner scanner = new Scanner(System.in);

    static private final List<Command> commands = new ArrayList<Command>(10);

    public static void registration(Command... commands) {
        for (Command c : commands)
            if (CommandHandler.commands.contains(c)) {
                throw new IllegalStateException(c+ "already in commands!");
            } else {
                CommandHandler.commands.add(c);
            }
    }

}
