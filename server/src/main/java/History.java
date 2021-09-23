import common.AbstractCommand;
import common.Response;
import common.Session;

public class History extends AbstractCommand {
    public History(){
        super("history", false, false);
    }

    @Override
    public void run(Session session) {
        session.setResponse(new Response(session.getHistory()));
    }
}