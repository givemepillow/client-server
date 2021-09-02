package dev.kirilllapushinskiy.commands;

public class CommandRegistrator {

    static protected final CommandCollection commands = new CommandCollection(20);

    public static void registration(Command... commands) {
        for (Command c : commands)
            if (CommandRegistrator.commands.contains(c)) {
                throw new IllegalStateException(c + "already in commands!");
            } else {
                CommandRegistrator.commands.add(c);
            }
    }

}
