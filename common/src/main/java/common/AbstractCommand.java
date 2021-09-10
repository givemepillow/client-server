package common;

public abstract class AbstractCommand implements Command {

    private final String COMMAND_NAME;
    private final boolean REQUIRED_ARGUMENT;
    private String commandArgument;
    protected AbstractCommand(String commandName, boolean requiredArgument) {
        COMMAND_NAME = commandName;
        REQUIRED_ARGUMENT = requiredArgument;
    }


    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public String getCommandArgument() {
        if(!isRequiredArgument()){
            throw new IllegalStateException("Команда без аргумента!");
        }
        return commandArgument;
    }

    @Override
    public void setCommandArgument(String commandArgument){
        if(commandArgument == null){
            throw new IllegalArgumentException("Пустой аргумент!");
        }
        this.commandArgument = commandArgument;
    }


    @Override
    public boolean isRequiredArgument() {
        return REQUIRED_ARGUMENT;
    }

    @Override
    public String toString() {
        return getCommandName();
    }
}
