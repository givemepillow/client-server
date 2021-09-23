import common.AbstractCommand;
import common.HumanBeing;
import common.Response;
import common.Session;

public class Show extends AbstractCommand {

    public Show() {
        super("show", false, false);
    }

    @Override
    public void run(Session session) {
        StringBuilder response = new StringBuilder();
        if (Storage.humanBeings.isEmpty()) {
            response.append("Коллекция пуста!");
        } else {
            for (HumanBeing humanBeing : Storage.humanBeings) {
                response.append("\n");
                response.append(humanBeing.toString());
            }
        }
        session.setResponse(new Response(response.toString()));
    }
}
