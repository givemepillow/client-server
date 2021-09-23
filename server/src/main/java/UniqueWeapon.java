import common.AbstractCommand;
import common.HumanBeing;
import common.Response;
import common.Session;

import java.util.SortedSet;
import java.util.TreeSet;

public class UniqueWeapon extends AbstractCommand {

    public UniqueWeapon() {
        super("unique_weapon", false, false);
    }

    public void run(Session session) {

        if (Storage.humanBeings.isEmpty()) {
            session.setResponse(new Response("Значения поля weaponType отсутствуют!"));
        } else {
            StringBuilder s = new StringBuilder();
            s.append("Уникальные значения поля weaponType: ");
            SortedSet<String> weaponTypeSet = new TreeSet<>();

            for (HumanBeing humanBeing : Storage.humanBeings) {
                weaponTypeSet.add(humanBeing.getWeaponType().toString());
            }

            for (String weaponType : weaponTypeSet) {
                s.append("\n").append(weaponType);
            }
            session.setResponse(new Response(s.toString()));
        }
    }
}