import common.Command;
import common.CommandCollection;

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

    public static void start() {
        while (true) {
            CommandParser.readNextCommand();
            if (commands.contains(CommandParser.getCommandName())) {
                Command temp = commands.getCommand(CommandParser.getCommandName());
                if (temp.isRequiredArgument() && !CommandParser.isWithArgument()) {
                    System.out.println("Команда требует аргумент!\n");
                } else if (!temp.isRequiredArgument() && CommandParser.isWithArgument()) {
                    System.out.println("Команда не требует аргумента!\n");
                } else {
                    if(temp.isRequiredArgument()){
                        temp.setCommandArgument(CommandParser.getCommandArgument());
                    }
                    temp.run();
                }
            } else {
                System.out.println("Команда " + CommandParser.getCommandName() + " не зарегистрирована!\n");
            }
        }
    }

}
