package common;

public interface Command {
    String getCommandName();
    String getCommandArgument();
    void setCommandArgument(String commandArgument);
    boolean isRequiredArgument();
    default void run() {}
    default void run(Session session) {}
}
