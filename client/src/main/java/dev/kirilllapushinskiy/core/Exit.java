package dev.kirilllapushinskiy.core;

import dev.kirilllapushinskiy.commands.AbstractCommand;

public class Exit extends AbstractCommand {

    static private final String COMMAND_NAME = "EXIT";

    private Exit() {
        super(COMMAND_NAME, false);
    }

    public static Exit init() {
        return INSTANCE;
    }

    private static final Exit INSTANCE = new Exit();
}
