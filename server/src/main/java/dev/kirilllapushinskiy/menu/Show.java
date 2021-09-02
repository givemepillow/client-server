package dev.kirilllapushinskiy.menu;


import dev.kirilllapushinskiy.commands.AbstractCommand;
import dev.kirilllapushinskiy.communication.AnswerMessage;
import dev.kirilllapushinskiy.communication.Session;
import dev.kirilllapushinskiy.core.HumanBeing;
import dev.kirilllapushinskiy.core.AppServer;

public class Show extends AbstractCommand {

    static private final String COMMAND_NAME = "SHOW";

    protected Show() {
        super(COMMAND_NAME, false);
    }

    public static Show init() {
        return INSTANCE;
    }

    private static final Show INSTANCE = new Show();

    public void run(Session session) {
        super.run(session);
        StringBuilder answer = new StringBuilder("Элементы коллекции: \n");
        if (AppServer.humanBeings.isEmpty()) {
            answer = new StringBuilder("Коллекция пуста!");
        } else {
            for (HumanBeing humanBeing : AppServer.humanBeings) {
                answer.append(humanBeing);
            }
        }
        session.setMessage(new AnswerMessage(answer.toString()));

    }
}
