package dev.kirilllapushinskiy.commands;

import java.io.Serializable;

abstract class AbstractCommand implements Command, Serializable {

    protected final String commandName;

    protected final boolean requiredArgs;

    protected boolean withArgs;

    protected String[] commandArgs;

    protected void initializer(String[] commandArgs) {
        if (requiredArgs && commandArgs == null) {
            throw new IllegalArgumentException();
        } else if (commandArgs != null) {
            this.commandArgs = commandArgs;
            this.withArgs = true;
        } else {
            this.commandArgs = null;
            this.withArgs = false;
        }
    }

    protected void initializer() {
        if (requiredArgs) throw new IllegalStateException();
        this.commandArgs = null;
        this.withArgs = false;
    }

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
        if (o == null || getClass() != o.getClass()) return false;
        Command that = (Command) o;
        return commandName.equals(that.getCommandName());
    }

    public Boolean is(String commandName) {
        return getCommandName().equalsIgnoreCase(commandName);
    }
}
