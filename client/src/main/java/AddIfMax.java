import common.AbstractCommand;
import common.HumanBeing;
import common.Response;

public class AddIfMax extends AbstractCommand {
	protected AddIfMax() {
		super("add_if_max",false, true);
	}

    public void run() {
		System.out.println("Если скорость сущности будет меньше минимальной в коллекции, добавление в коллекцию: ");

		HumanBeing humanBeing = Add.getParameterHumanBeing(Utils.getScanner());
		setHumanBeing(humanBeing);
		Response response = Communicator.remoteCommandExecution(this);
		System.out.println(response);

	}
}