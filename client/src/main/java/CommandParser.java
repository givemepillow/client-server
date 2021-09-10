import java.util.Scanner;

public class CommandParser {
    private static Scanner scanner = Utils.getScanner();
    private static String commandName;
    private static String commandArgument;
    private static boolean withArgument;

    private CommandParser() {
    }

    private static void reset() {
        commandArgument = null;
        commandName = null;
        withArgument = false;
    }

    public static String getCommandName() {
        if (commandName == null) {
            throw new IllegalStateException("Имя команды не получено!");
        }
        return commandName;
    }

    public static String getCommandArgument() {
        if (!withArgument || commandArgument == null) {
            throw new IllegalStateException("Аргументы отсутствуют!");
        }
        return commandArgument;
    }

    public static boolean isWithArgument() {
        return withArgument;
    }


    private static boolean readCommand() {
        reset();
        String line = scanner.nextLine().trim().replace("  ", " ");
        if (line.equals("")) {
            return false;
        }
        String[] args = line.split(" ");
        commandName = args[0];
        if (args.length == 2) {
            commandArgument = args[1];
            withArgument = true;
        } else if (args.length > 2) {
            throw new IllegalArgumentException("Поддерживается не более одного аргумента!\n");
        }
        return true;
    }

    public static void readNextCommand() {
        while (true) {
            System.out.print("Введите команду: ");
            try {

                if (!CommandParser.readCommand()) {
                    continue;
                }
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }

    }

}
