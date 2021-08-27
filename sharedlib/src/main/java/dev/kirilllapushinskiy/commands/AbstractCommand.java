package dev.kirilllapushinskiy.commands;

abstract class AbstractCommand implements Command {

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

    public AbstractCommand(String commandName, boolean requiredArgs) {
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

    public void run() {
        System.out.println("RUN: TEST_COMMAND.");
    }
}
