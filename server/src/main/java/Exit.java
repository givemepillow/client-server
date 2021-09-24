import common.AbstractCommand;
import common.Response;
import common.Session;

public class Exit extends AbstractCommand {

    protected Exit() {
        super("exit", false, false);
    }

    @Override
    public void run(Session session) {
        Storage.saver();
        session.setResponse(new Response("Внесённые на сервере изменения сохранены в файл " + Storage.getJsonFile() +"."));
    }
}
