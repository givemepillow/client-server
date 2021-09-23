package common;

import java.io.Serializable;

public class CommandPackage implements Serializable {

    private String commandName;
    private String commandArgument;
    private boolean withArgument;
    private HumanBeing humanBeing;
    private boolean withHumanBeing = false;

    private void setHumanBeing(HumanBeing hb) {
        this.humanBeing = hb;
        this.withHumanBeing = true;
    }

    public HumanBeing getHumanBeing() {
        if (!withHumanBeing)
            throw new IllegalArgumentException("Данная команда не содержит существо!");
        return this.humanBeing;
    }

    public boolean isWithHumanBeing() {
        return withHumanBeing;
    }

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
        if (command.isRequiredHumanBeing()) {
            pack.setHumanBeing(command.getHumanBeing());
        }
        return pack;
    }
}
