package dev.kirilllapushinskiy.commands;

public interface Command {

    String getCommandName();

    String[] getCommandArgs();

    Boolean withArgs();

    Boolean requiredArgs();

    void setCommandArgs(String[] args);

    @Override
    boolean equals(Object o);

    Boolean is(String commandName);
}
