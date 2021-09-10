import common.Command;
import common.CommandCollection;
import common.CommandPackage;
import common.Session;

public class CommandHandler {

    private final static CommandCollection commands = new CommandCollection(20);

    public static void registration(Command... newCommands) {
        for (Command c : newCommands)
            if (commands.contains(c)) {
                throw new IllegalStateException("Команда " + c + " уже зарегистрирована!\n");
            } else {
                commands.add(c);
            }
    }

    public static void execute(CommandPackage pack, Session session) {
        Command c = commands.getCommand(pack.getCommandName());
        if(pack.isWithArgument())
            c.setCommandArgument(pack.getCommandArgument());

        c.run(session);
    }

}
