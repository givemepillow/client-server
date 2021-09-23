import common.AbstractCommand;
import common.Response;
import common.Session;

public class Info extends AbstractCommand {

    protected Info() {
        super("info", false, false);
    }

    public void run(Session session) {
        StringBuilder s = new StringBuilder();
        if (Storage.humanBeings.isEmpty()) {
            s.append("Коллекция пуста!");
        } else {
            s.append("Информация о коллекции HumanBeings: \n");
            s.append("Количество: ").append(Storage.humanBeings.size()).append("\n");
            Storage.humanBeings.forEach(s::append);
        }
        session.setResponse(new Response(s.toString()));
    }
}
