import common.AbstractCommand;
import common.HumanBeing;
import common.Response;
import common.Session;

import java.util.Comparator;

public class AddIfMin extends AbstractCommand {
	protected AddIfMin() {
		super("add_if_min", false, true);
	}

	@Override
	public void run(Session session) {
		HumanBeing humanBeing = session.getHumanBeing();

		HumanBeing humanBeingWithMaxImpactSpeed = Storage.humanBeings
				.stream()
				.min(Comparator.comparing(HumanBeing::getImpactSpeed))
				.orElse(null);

		if (Storage.humanBeings.isEmpty() || humanBeingWithMaxImpactSpeed.getImpactSpeed() > humanBeing.getImpactSpeed()) {
			session.setResponse(new Response("Скорость удара существа оказалась меньше минимальных значений в коллекции. Добавляем существо в коллекцию!"));
			Storage.save(humanBeing);
		} else {
			session.setResponse(new Response("Скорость удара существа оказалась больше минимальных значений в коллекции. Существо не добавляем!"));
		}
	}
}