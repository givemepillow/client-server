package dev.kirilllapushinskiy.core;

import dev.kirilllapushinskiy.commands.AbstractCommand;

public class History extends AbstractCommand {

    static private final String COMMAND_NAME = "HISTORY";

    protected History() {
        super(COMMAND_NAME, false);
    }

    public static History init() {
        return INSTANCE;
    }

    private static final History INSTANCE = new History();

}
