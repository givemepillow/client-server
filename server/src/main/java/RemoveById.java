import common.AbstractCommand;
import common.Response;
import common.Session;

public class RemoveById extends AbstractCommand {

    public RemoveById() {
        super("remove_by_id", true, false);
    }

    @Override
    public void run(Session session) {
        try {
            Integer id = Integer.parseInt(getCommandArgument());
            if (Storage.humanBeings.stream().noneMatch((humanBeing -> humanBeing.getId().equals(id)))) {
                session.setResponse(new Response("Сущности с id " + getCommandArgument() + " не существует."));
            } else {
                Storage.remove(id);
                session.setResponse(new Response("Объект удален из коллекции!"));
            }
        } catch (Exception e) {
            session.setResponse(new Response("Сущности с id " + getCommandArgument() + " не существует."));
        }
    }
}