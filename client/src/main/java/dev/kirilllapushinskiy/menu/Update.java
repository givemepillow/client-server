package dev.kirilllapushinskiy.menu;

import dev.kirilllapushinskiy.commands.AbstractCommand;

public class Update extends AbstractCommand {

    static private final String COMMAND_NAME = "UPDATE";

    protected Update() {
        super(COMMAND_NAME, true);
    }

    public static Update init() {
        return INSTANCE;
    }

    private static final Update INSTANCE = new Update();

}