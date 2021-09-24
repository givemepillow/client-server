import common.AbstractCommand;
import common.Response;
import common.Session;

public class Update extends AbstractCommand {
    protected Update() {
        super("update", true, true);
    }

    @Override
    public void run(Session session) {
        Storage.update(session.getHumanBeing());
        Response response = new Response("Cущность обновлена.");
        session.setResponse(response);
    }
}
