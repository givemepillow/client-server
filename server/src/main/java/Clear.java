import common.AbstractCommand;
import common.Response;
import common.Session;

public class Clear extends AbstractCommand {

    public Clear() {
        super("clear", false, false);
    }

    @Override
    public void run(Session session) {
        Storage.clear();
        session.setResponse(new Response("Все сущности удалены."));
    }
}