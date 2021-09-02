package dev.kirilllapushinskiy.core;

import dev.kirilllapushinskiy.commands.Command;
import dev.kirilllapushinskiy.commands.CommandPackage;
import dev.kirilllapushinskiy.commands.CommandRegistrator;

public class ServerCommandHandler extends CommandRegistrator {


    public static Command handle(CommandPackage commandPackage) {

        if (!commands.conclude(commandPackage.getPackedCommandName()))
            throw new IllegalArgumentException("Undefined command!");

        return commands.getCommand(commandPackage.getPackedCommandName());
    }

    public static Command handle(String commandName) {

        if (!commands.conclude(commandName))
            throw new IllegalArgumentException("Undefined command!");

        return commands.getCommand(commandName);
    }
}

