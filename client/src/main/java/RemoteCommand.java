import common.AbstractCommand;
import common.Response;

public abstract class RemoteCommand extends AbstractCommand {


    protected RemoteCommand(String commandName, boolean requiredArgument, boolean requiredHumanBeing) {
        super(commandName, requiredArgument, requiredHumanBeing);
    }

    @Override
    public void run() {
        //AddHistory();
        Response response = Communicator.remoteCommandExecution(this);
        System.out.println(response);
    }
}
