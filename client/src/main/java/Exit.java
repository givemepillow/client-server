import common.AbstractCommand;
import common.Response;

public class Exit extends AbstractCommand {

    public Exit() {
        super("exit", false, false);
    }

    @Override
    public void run() {
        Response response = Communicator.remoteCommandExecution(this);
        System.out.println(response);
        if (response.getData().equalsIgnoreCase("Сервер не доступен, попробуйте позже... :("))
            System.out.println("Ваши изменения на сервере не были сохранены.");
        System.out.println("\nЗавершение работы программы...");
        System.exit(0);
    }
}
