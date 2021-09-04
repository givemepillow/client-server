package dev.kirilllapushinskiy.handlers;

import dev.kirilllapushinskiy.commands.Command;
import dev.kirilllapushinskiy.commands.CommandRegistrator;

public class CommandHandler extends CommandRegistrator {

    public static Command start() {

        if (commands.isEmpty())
            throw new IllegalStateException("No one command had been registered!");

        while (true) {
            System.out.print("Enter command: ");
            if (!CommandParser.readCommand()) continue;

            if (commands.conclude(CommandParser.getCommandName())) {

                Command c = commands.getCommand(CommandParser.getCommandName());

                if (c.requiredArgs()) {

                    if (CommandParser.withArgs()) {
                        c.setCommandArgs(CommandParser.getCommandArgs());
                    } else {
                        System.out.println("Command require arguments!\n");
                        continue;
                    }

                } else if (CommandParser.withArgs()) {
                    System.out.println("The command does not require arguments!\n");
                    continue;
                }

                return c;

            } else {

                System.out.println("Command '" + CommandParser.getCommandName() + "' not recognized!\n");

            }
        }
    }
}
