package dev.kirilllapushinskiy.menu;

import dev.kirilllapushinskiy.commands.AbstractCommand;
import dev.kirilllapushinskiy.communication.Session;

public class Info extends AbstractCommand {

    static private final String COMMAND_NAME = "INFO";

    private Info() {
        super(COMMAND_NAME, false);
    }

    public static Info init() {
        return INSTANCE;
    }

    private static final Info INSTANCE = new Info();

    @Override
    public void run(Session session) {}
}
