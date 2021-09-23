package common;

public interface Command {
    String getCommandName();
    String getCommandArgument();
    void setCommandArgument(String commandArgument);
    boolean isRequiredArgument();
    boolean isInternal();
    default void run() {}
    default void run(Session session) {}
    boolean isRequiredHumanBeing();
    HumanBeing getHumanBeing();
    void setHumanBeing(HumanBeing hb);
}
