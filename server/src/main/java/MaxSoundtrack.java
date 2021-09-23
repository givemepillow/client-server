import common.AbstractCommand;
import common.HumanBeing;
import common.Response;
import common.Session;

import java.util.Comparator;

public class MaxSoundtrack extends AbstractCommand {

    protected MaxSoundtrack() {
        super("max_soundtrack", false, false);
    }

    public void run(Session session) {

        if (Storage.humanBeings.isEmpty()) {
            session.setResponse(new Response("Нет ни одного саундтрека!"));
        } else {
            HumanBeing humanBeingWithMaxSoundtrack = Storage.humanBeings
                    .stream()
                    .max(Comparator.comparing(HumanBeing::getSoundtrackName))
                    .orElse(null);
            session.setResponse(
                    new Response(
                            "Первым в лексикографическом порядке является следующий саундтрек: "
                                    + humanBeingWithMaxSoundtrack.getSoundtrackName()));
        }
    }
}