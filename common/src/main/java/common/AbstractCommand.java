package common;

public abstract class AbstractCommand implements Command {

    private final String COMMAND_NAME;
    private final boolean REQUIRED_ARGUMENT;
    private final boolean REQUIRED_HUMAN_BEING;

    private String commandArgument;
    private HumanBeing humanBeing;

    protected AbstractCommand(String commandName, boolean requiredArgument, boolean requiredHumanBeing) {
        COMMAND_NAME = commandName;
        REQUIRED_ARGUMENT = requiredArgument;
        REQUIRED_HUMAN_BEING = requiredHumanBeing;
    }

    @Override
    public boolean isInternal() {
        return false;
    }

    @Override
    public boolean isRequiredHumanBeing() {
        return REQUIRED_HUMAN_BEING;
    }

    @Override
    public HumanBeing getHumanBeing() {
        if(humanBeing == null)
            throw new IllegalStateException("Сущность не была установлена!");
        return humanBeing;
    }

    @Override
    public void setHumanBeing(HumanBeing humanBeing) {
        if (!REQUIRED_HUMAN_BEING)
            throw new IllegalArgumentException("Данная команда не использует существо!");
        this.humanBeing = humanBeing;
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
