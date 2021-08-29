package dev.kirilllapushinskiy.client;
import dev.kirilllapushinskiy.commands.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandHandler {

    static private final String EXIT = "exit";

    static private final Scanner scanner = new Scanner(System.in);

    static private final List<Command> commands = new ArrayList<Command>(10);

}
