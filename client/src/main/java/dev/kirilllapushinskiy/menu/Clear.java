package dev.kirilllapushinskiy.menu;

import dev.kirilllapushinskiy.commands.AbstractCommand;

public class Clear extends AbstractCommand {

    static private final String COMMAND_NAME = "CLEAR";

    protected Clear() {
        super(COMMAND_NAME, false);
    }

    public static Clear init() {
        return INSTANCE;
    }

    private static final Clear INSTANCE = new Clear();

}