import common.AbstractCommand;
import common.HumanBeing;
import common.Response;
import common.Session;

import java.util.Comparator;

public class AddIfMax extends AbstractCommand {
	protected AddIfMax() {
		super("add_if_max", false, true);
	}

	@Override
	public void run(Session session) {
		HumanBeing humanBeing = session.getHumanBeing();

		HumanBeing humanBeingWithMaxImpactSpeed = Storage.humanBeings
				.stream()
				.max(Comparator.comparing(HumanBeing::getImpactSpeed))
				.orElse(null);

		if (Storage.humanBeings.isEmpty() || humanBeingWithMaxImpactSpeed.getImpactSpeed() < humanBeing.getImpactSpeed()) {
			session.setResponse(new Response("Скорость удара существа оказалась больше максимальных значений в коллекции. Добавляем существо в коллекцию!"));
			Storage.save(humanBeing);
		} else {
			session.setResponse(new Response("Скорость удара существа оказалась меньше максимальных значений в коллекции. Существо не добавляем!"));
		}
	}
}