package dev.kirilllapushinskiy.menu;

import dev.kirilllapushinskiy.commands.AbstractCommand;
import dev.kirilllapushinskiy.communication.FinishMessage;
import dev.kirilllapushinskiy.communication.Session;
import dev.kirilllapushinskiy.core.AppServer;
import dev.kirilllapushinskiy.core.Save;
import dev.kirilllapushinskiy.utils.Searcher;


public class RemoveById extends AbstractCommand {

    static private final String COMMAND_NAME = "RemoveById";

    protected RemoveById() {
        super(COMMAND_NAME, true);
    }

    public static RemoveById init() {
        return INSTANCE;
    }

    private static final RemoveById INSTANCE = new RemoveById();

    public void run(Session session) {
        super.run(session);
        Integer id = Searcher.searchByID(session);
        if (!(id == null)) {
            AppServer.humanBeings.removeIf((human -> human.getId().equals(id)));
            Save.run();
            session.setMessage(new FinishMessage("Объект c номером " + id + " удален из коллекции!"));
        }
    }
}
