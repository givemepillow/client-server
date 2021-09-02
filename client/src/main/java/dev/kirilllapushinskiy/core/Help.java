package dev.kirilllapushinskiy.core;

import dev.kirilllapushinskiy.commands.AbstractCommand;

public class Help extends AbstractCommand {

    static private final String COMMAND_NAME = "HELP";

    protected Help() {
        super(COMMAND_NAME, false);
    }

    public static Help init() {
        return INSTANCE;
    }

    private static final Help INSTANCE = new Help();

}
