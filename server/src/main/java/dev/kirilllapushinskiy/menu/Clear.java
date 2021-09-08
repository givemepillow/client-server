package dev.kirilllapushinskiy.menu;


import dev.kirilllapushinskiy.commands.AbstractCommand;
import dev.kirilllapushinskiy.communication.FinishMessage;
import dev.kirilllapushinskiy.communication.Session;
import dev.kirilllapushinskiy.core.AppServer;

public class Clear extends AbstractCommand {

    static private final String COMMAND_NAME = "CLEAR";

    protected Clear() {
        super(COMMAND_NAME, false);
    }

    public static Clear init() {
        return INSTANCE;
    }

    private static final Clear INSTANCE = new Clear();

    public void run(Session s) {
        super.run(s);

        AppServer.humanBeings.clear();

        s.setMessage(new FinishMessage("Коллекция очищена!"));
    }
}