package dev.kirilllapushinskiy;

import dev.kirilllapushinskiy.commands.AbstractCommand;
import dev.kirilllapushinskiy.commands.TestCommand1;

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
