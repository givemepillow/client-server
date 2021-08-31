package dev.kirilllapushinskiy.commands;

public class TestCommand1 extends AbstractCommand {

    static private final String COMMAND_NAME = "T1";

    private TestCommand1() {
        super(COMMAND_NAME, true);
    }

    public static TestCommand1 init() {
        return INSTANCE;
    }

    private static final TestCommand1 INSTANCE = new TestCommand1();

}
