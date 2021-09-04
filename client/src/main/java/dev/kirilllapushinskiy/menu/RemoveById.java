package dev.kirilllapushinskiy.menu;

import dev.kirilllapushinskiy.commands.AbstractCommand;

public class RemoveById extends AbstractCommand {

    static private final String COMMAND_NAME = "RemoveById";

    protected RemoveById() {
        super(COMMAND_NAME, false);
    }

    public static RemoveById init() {
        return INSTANCE;
    }

    private static final RemoveById INSTANCE = new RemoveById();

}