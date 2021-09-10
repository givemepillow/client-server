package common;

import java.io.Serializable;

public class CommandPackage implements Serializable {

    private String commandName;
    private String commandArgument;
    private boolean withArgument;

    private CommandPackage(String commandName) {
        this.commandName = commandName;
    }

    private void setCommandArgument(String commandArgument) {
        if (commandArgument == null) {
            throw new IllegalArgumentException("Вместо аргумента передан null");
        }
        this.commandArgument = commandArgument;
        withArgument = true;
    }

    public String getCommandName() {
        if (commandName == null) {
            throw new IllegalStateException("Имя команды не получено!");
        }
        return commandName;
    }

    public String getCommandArgument() {
        if (!withArgument || commandArgument == null) {
            throw new IllegalStateException("Аргументы отсутствуют!");
        }
        return commandArgument;
    }

    public boolean isWithArgument() {
        return withArgument;
    }

    public static CommandPackage pack(Command command) {
        CommandPackage pack = new CommandPackage(command.getCommandName());
        if(command.isRequiredArgument()){
            pack.setCommandArgument(command.getCommandArgument());
        }
        return pack;
    }
}
