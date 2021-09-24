import common.AbstractCommand;
import common.HumanBeing;
import common.Response;
import common.Session;

public class Add extends AbstractCommand {
    protected Add() {
        super("add", false, true);
    }

    @Override
    public void run(Session session) {
        Storage.save(session.getHumanBeing());
        Response response = new Response("Новая сущность добавлена.");
        session.setResponse(response);
    }
}