package dev.kirilllapushinskiy.client;
import dev.kirilllapushinskiy.commands.Command;
import dev.kirilllapushinskiy.commands.CommandCollection;

public class CommandHandler {

    static private final String EXIT = "exit";

    static private final CommandCollection commands = new CommandCollection(10);

    public static void registration(Command... commands) {
        for (Command c : commands)
            if (CommandHandler.commands.contains(c)) {
                throw new IllegalStateException(c+ "already in commands!");
            } else {
                CommandHandler.commands.add(c);
            }
    }

    public static void start() {
        if (commands.isEmpty())
            throw new IllegalStateException("No one command had been registered!");

        while (true) {
            System.out.print("Enter command: ");
            if (!CommandParser.readCommand()) continue;
            if (CommandParser.commandEquals(EXIT)) break;

            if (commands.conclude(CommandParser.getCommandName())) {
                Command c = commands.getCommand(CommandParser.getCommandName());
                if (c.requiredArgs()) {
                    if (CommandParser.withArgs()) {
                        c.setCommandArgs(CommandParser.getCommandArgs());
                    } else {
                        System.out.println("Command require arguments!");
                    }
                } else if (CommandParser.withArgs()) {
                    c.setCommandArgs(CommandParser.getCommandArgs());
                }

                //CommandPackage pack = CommandPackage.pack(c);

            } else {
                System.out.println("Command '" + CommandParser.getCommandName() + "' not recognized!");
            }

        }
        System.out.println("Bye-bye!");
    }

}
