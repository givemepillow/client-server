package dev.kirilllapushinskiy.menu;

import dev.kirilllapushinskiy.commands.AbstractCommand;

public class Show extends AbstractCommand {

    static private final String COMMAND_NAME = "SHOW";

    protected Show() {
        super(COMMAND_NAME, false);
    }

    public static Show init() {
        return INSTANCE;
    }

    private static final Show INSTANCE = new Show();

}