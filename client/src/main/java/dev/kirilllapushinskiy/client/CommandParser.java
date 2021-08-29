package dev.kirilllapushinskiy.client;


import java.util.*;

public class CommandParser {

    static private final Scanner scanner = new Scanner(System.in);

    static private String commandName;

    static private String[] commandArgs;


    static public boolean readCommand() {
        String line = scanner.nextLine().trim().replace("  ", " ");
        if (line.equals("")) {
            return false;
        } else {
            String[] args = line.replace("  ", " ").split(" ");
            ArrayList<String> elements = new ArrayList<>(Arrays.asList(args));
            commandName = elements.get(0);
            elements.remove(0);
            if (elements.size() == 0) {
                commandArgs = null;
            } else {
                commandArgs = elements.toArray(new String[0]);
            }
        }
        return true;
    }

    static public String getCommandName() {
        return commandName;
    }

    static public String[] getCommandArgs() {
        return commandArgs;
    }

    static public boolean withArgs() {
        return commandArgs != null;
    }

    static public boolean commandEquals(String str) {
        return commandName.equalsIgnoreCase(str);
    }

}
