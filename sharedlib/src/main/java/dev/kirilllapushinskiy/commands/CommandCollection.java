package dev.kirilllapushinskiy.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CommandCollection extends ArrayList<Command> {

    public CommandCollection(int size) {
        super(size);
        this.commandNames = new HashSet<>(size);
    }

    private final Set<String> commandNames;

    @Override
    public boolean add(Command command) {
        commandNames.add(command.getCommandName());
        return super.add(command);
    }


    @Override
    public boolean contains(Object o) {
        String name;
        if (o instanceof Command) {
            name = ((Command) o).getCommandName();
            return this.conclude(name);
        } else {
            return false;
        }
    }

    public boolean conclude(String str) {
        for (String commandName : commandNames) {
            if (commandName.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }



    public Command getCommand (String commandName) {
        for (Command c : this) {
            if (c.is(commandName)) {
                return c;
            }
        }
        return null;
    }
}
