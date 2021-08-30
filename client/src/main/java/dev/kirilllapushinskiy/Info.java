package dev.kirilllapushinskiy;

import dev.kirilllapushinskiy.commands.AbstractCommand;

public class Info extends AbstractCommand {

    static private final String COMMAND_NAME = "INFO";

    private Info() {
        super(COMMAND_NAME, false);
    }

    public static Info init() {
        return INSTANCE;
    }

    private static final Info INSTANCE = new Info();

}
