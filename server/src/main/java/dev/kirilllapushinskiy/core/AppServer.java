package dev.kirilllapushinskiy.core;

import dev.kirilllapushinskiy.commands.Command;
import dev.kirilllapushinskiy.entities.HumanBeing;
import dev.kirilllapushinskiy.menu.*;

import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class AppServer {

    public static SortedSet<HumanBeing> humanBeings = new TreeSet<>();

    static private final Command HELP = Help.init();
    static private final Command HISTORY = History.init();
    static private final Command SHOW = Show.init();
    static private final Command REMOVEBYID = RemoveById.init();
    static private final Command MAXSOUNDTRACK = MaxSoundtrack.init();
    static private final Command ADD = Add.init();
    static private final Command CLEAR = Clear.init();
    static private final Command UPDATE = Update.init();

    public static void main(String[] args) throws IOException {

        Load.run();

        Server.initialization(100);

        ServerCommandHandler.registration(
                HELP,
                HISTORY,
                SHOW,
                REMOVEBYID,
                MAXSOUNDTRACK,
                ADD,
                CLEAR,
                UPDATE

        );

        Server.start();

    }
}
