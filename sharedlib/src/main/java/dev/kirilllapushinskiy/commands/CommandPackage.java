package dev.kirilllapushinskiy.commands;

public class CommandPackage {
    private CommandPackage() {}

    private String packedCommandName;

    private String[] packedCommandArgs;

    private boolean requiredArgs;

    private boolean withArgs;

    private static class CommandPackageHolder {
        public static final CommandPackage INSTANCE = new CommandPackage();
    }

    public static CommandPackage pack(Command command) {
        if (command == null)
            throw new IllegalArgumentException();
        CommandPackageHolder.INSTANCE.packedCommandName = command.getCommandName();
        CommandPackageHolder.INSTANCE.requiredArgs = command.requiredArgs();
        if (command.withArgs()) {
            CommandPackageHolder.INSTANCE.withArgs = true;
            CommandPackageHolder.INSTANCE.packedCommandArgs = command.getCommandArgs();
        } else {
            CommandPackageHolder.INSTANCE.withArgs = false;
            CommandPackageHolder.INSTANCE.packedCommandArgs = null;
        }

        return CommandPackageHolder.INSTANCE;
    }

    public String getPackedCommandName() {
        return this.packedCommandName;
    }

    public String[] getPackedCommandArgs() {
        return this.packedCommandArgs;
    }

    public boolean withArgs() {
        return this.withArgs;
    }

    public boolean requiredArgs() {
        return this.requiredArgs;
    }
}
