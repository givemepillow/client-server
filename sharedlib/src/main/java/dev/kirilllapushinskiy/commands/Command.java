package dev.kirilllapushinskiy.commands;

import dev.kirilllapushinskiy.communication.Session;

public interface Command {

    String getCommandName();

    String[] getCommandArgs();

    Boolean withArgs();

    Boolean requiredArgs();

    void setCommandArgs(String[] args);

    @Override
    boolean equals(Object o);

    Boolean is(String commandName);

    default void run(Session session) {
        session.addHistory(getCommandName());
    }

}
