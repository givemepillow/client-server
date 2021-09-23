package common;

import java.util.ArrayList;
import java.util.HashSet;

public class CommandCollection extends ArrayList<Command> {
    private final HashSet<String> commandNames;

    public CommandCollection(int size) {
        super(size);
        commandNames = new HashSet<>(size);
    }

    @Override
    public boolean add(Command command) {
        commandNames.add(command.getCommandName());
        return super.add(command);
    }

    @Override
    public boolean contains(Object o) {
        String commandName;
        if (o instanceof Command) {
            commandName = ((Command) o).getCommandName();
            return commandNames.contains(commandName);
        } else if (o instanceof String) {
            commandName = (String) o;
            return commandNames.contains(commandName);
        } else return false;
    }

    public Command getCommand(String commandName) {
        if (!this.contains(commandName)) {
            throw new IllegalStateException("Данная команда не зарегистрирована на сервере!");
        }
        Command temp = null;
        for (Command c : this) {
            if (c.getCommandName().equalsIgnoreCase(commandName)) {
                temp =c;
            }
        }
        return temp;
    }
}
