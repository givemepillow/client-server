import common.AbstractCommand;
import common.HumanBeing;
import common.Response;
import common.Session;

import java.util.List;
import java.util.stream.Collectors;

public class FilterName extends AbstractCommand {

    public FilterName() {
        super("filter_name", true, false);
    }

    public void run(Session session) {

        if (Storage.humanBeings.isEmpty()) {
            session.setResponse(new Response("Коллекция пустая! Поиск невозможен!"));
        } else {
            String name = getCommandArgument();
            // https://metanit.com/java/tutorial/10.6.php
            List<HumanBeing> humanBeingsWithSubstringName = Storage.humanBeings
                    .stream()
                    .filter(humanBeing -> humanBeing
                            .getName()
                            .toLowerCase()
                            .startsWith(name.toLowerCase()))
                    .sorted()
                    .collect(Collectors.toList());

            if (humanBeingsWithSubstringName.isEmpty()) {
                session.setResponse(new Response("Совпадений не найдено."));
            } else {
                StringBuilder s = new StringBuilder();
                for (HumanBeing humanBeing : humanBeingsWithSubstringName) {
                    s.append("\n").append(humanBeing);
                }
                session.setResponse(new Response(s.toString()));
            }
        }
    }
}