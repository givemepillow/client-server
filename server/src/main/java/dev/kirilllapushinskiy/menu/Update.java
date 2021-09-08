package dev.kirilllapushinskiy.menu;


import dev.kirilllapushinskiy.commands.AbstractCommand;
import dev.kirilllapushinskiy.communication.AnswerMessage;
import dev.kirilllapushinskiy.communication.ErrorMessage;
import dev.kirilllapushinskiy.communication.FinishMessage;
import dev.kirilllapushinskiy.communication.Session;
import dev.kirilllapushinskiy.core.AppServer;
import dev.kirilllapushinskiy.core.Save;
import dev.kirilllapushinskiy.entities.HumanBeing;
import dev.kirilllapushinskiy.utils.Searcher;


public class Update extends AbstractCommand {

    static private final String COMMAND_NAME = "UPDATE";

    protected Update() {
        super(COMMAND_NAME, true);
    }

    public static Update init() {
        return INSTANCE;
    }

    private static final Update INSTANCE = new Update();

    public void run(Session session) {
        super.run(session);

        session.setCurrentCommand(COMMAND_NAME);

        Integer id = Searcher.searchByID(session);
        if (id == null) return;
        String prompt = "Обновление элемента в коллекции.";
        HumanBeing humanBeing;
        if (session.getState() != 12) {
            Add.init().constructHumanBeing(session, id, prompt);
        } else if (session.getState() == 12) {
            AppServer.humanBeings.removeIf((human -> human.getId().equals(((HumanBeing) session.getObject()).id)));
            AppServer.humanBeings.add((HumanBeing) session.getObject());
            Save.run();
            session.setMessage(new FinishMessage("Элемент обновлён!"));
        }
    }
}
