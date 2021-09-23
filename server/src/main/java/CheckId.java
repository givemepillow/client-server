import common.AbstractCommand;
import common.Response;
import common.Session;

public class CheckId extends AbstractCommand {

    protected CheckId() {
        super("checkid", true, false);
    }

    @Override
    public void run(Session session) {
        if (Storage.humanBeings.size() == 0) {
            session.setResponse(new Response("В коллекции нет объектов! Поиск по идентификатору невозможен!"));
        } else {
            int id;
            try {
                String text = this.getCommandArgument();
                id = Integer.parseInt(text);
                Integer finalId = id;
                if (id < 0 || Storage.humanBeings.stream().noneMatch((humanBeing -> humanBeing.getId().equals(finalId)))) {
                    session.setResponse(new Response("Такого идентификатора не существует!"));
                } else {
                    session.setResponse(new Response("OK"));
                }
            } catch (NumberFormatException exception) {
                session.setResponse(new Response("Некорректный идентификатор!"));
            } catch (IllegalArgumentException exception) {
                session.setResponse(new Response(exception.getMessage()));
            }
        }
    }
}