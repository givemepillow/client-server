package dev.kirilllapushinskiy.menu;

import dev.kirilllapushinskiy.commands.AbstractCommand;

public class MaxSoundtrack extends AbstractCommand {

    static private final String COMMAND_NAME = "MaxSoundtrack";

    protected MaxSoundtrack() {
        super(COMMAND_NAME, false);
    }

    public static MaxSoundtrack init() {
        return INSTANCE;
    }

    private static final MaxSoundtrack INSTANCE = new MaxSoundtrack();

}