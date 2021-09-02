package dev.kirilllapushinskiy.menu;

import dev.kirilllapushinskiy.commands.AbstractCommand;
import dev.kirilllapushinskiy.communication.FinishMessage;
import dev.kirilllapushinskiy.communication.Message;
import dev.kirilllapushinskiy.communication.Session;

public class History extends AbstractCommand {

    static private final String COMMAND_NAME = "HISTORY";

    protected History() {
        super(COMMAND_NAME, false);
    }

    public static History init() {
        return INSTANCE;
    }

    private static final History INSTANCE = new History();

    public void run(Session session) {
        StringBuilder answer = new StringBuilder();
        answer.append("Последние 11 команд: ");

        for (String text : session.getHistory()) {
            answer.append("\n").append(text);
        }
        Message message = new FinishMessage(answer.toString());
        session.setMessage(message);
        super.run(session);
    }

}
