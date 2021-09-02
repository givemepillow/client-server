package dev.kirilllapushinskiy.core;

import dev.kirilllapushinskiy.commands.Command;
import dev.kirilllapushinskiy.menu.Help;
import dev.kirilllapushinskiy.menu.History;
import dev.kirilllapushinskiy.menu.Show;

import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class AppServer {

    public static SortedSet<HumanBeing> humanBeings = new TreeSet<>();

    static private final Command HELP = Help.init();
    static private final Command HISTORY = History.init();
    static private final Command SHOW = Show.init();

    public static void main(String[] args) throws IOException {

        Load.run();

        Server.initialization(100);

        ServerCommandHandler.registration(
                HELP,
                HISTORY,
                SHOW
        );

        Server.start();

    }
}
