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
        System.out.println("\nЗавершение работы программы...");
        System.exit(0);
    }
}
