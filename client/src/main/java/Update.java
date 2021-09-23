import common.AbstractCommand;
import common.Command;
import common.HumanBeing;
import common.Response;

public class Update extends AbstractCommand {


    protected Update() {
        super("update", true, true);
    }

    public void run() {
        Command checkId = new CheckId();
        checkId.setCommandArgument(this.getCommandArgument());
        Response response = Communicator.remoteCommandExecution(checkId);
        if (response.getData().equalsIgnoreCase("OK")) {
            System.out.println("Обновление элемента в коллекции: ");
            HumanBeing humanBeing = Add.getParameterHumanBeing(Utils.getScanner());
            humanBeing.initId(Integer.parseInt(this.getCommandArgument()));
            this.setHumanBeing(humanBeing);
            response = Communicator.remoteCommandExecution(this);
        }
        System.out.println(response);


        //App.humanBeings.removeIf((human -> human.getId().equals(id)));
        //App.humanBeings.add(humanBeing);
    }
}