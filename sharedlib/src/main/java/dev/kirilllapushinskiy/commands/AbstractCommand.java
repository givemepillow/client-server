package dev.kirilllapushinskiy.commands;

import java.io.Serializable;

abstract class AbstractCommand implements Command, Serializable {

    protected final String commandName;

    protected final boolean requiredArgs;

    protected boolean withArgs;

    protected String[] commandArgs;

    protected AbstractCommand(String commandName, boolean requiredArgs) {
        this.commandName = commandName;
        this.requiredArgs = requiredArgs;
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getCommandArgs() {
        return commandArgs;
    }
    public Boolean withArgs() {
        return withArgs;
    }
    public Boolean requiredArgs() {
        return requiredArgs;
    }

    @Override
    public String toString() {
        return this.getCommandName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Command) {
            Command that = (Command) o;
            return getCommandName().equalsIgnoreCase(that.getCommandName());
        } else {
            return false;
        }
    }

    public void setCommandArgs(String[] args) {
        if (requiredArgs && args == null) {
            throw new IllegalArgumentException();
        } else if (args != null) {
            this.commandArgs = args;
            this.withArgs = true;
        } else {
            this.commandArgs = null;
            this.withArgs = false;
        }
    }

    public Boolean is(String commandName) {
        return getCommandName().equalsIgnoreCase(commandName);
    }
}
