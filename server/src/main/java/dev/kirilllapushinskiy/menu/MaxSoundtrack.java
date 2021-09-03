package dev.kirilllapushinskiy.menu;

import dev.kirilllapushinskiy.commands.AbstractCommand;
import dev.kirilllapushinskiy.communication.ErrorMessage;
import dev.kirilllapushinskiy.communication.FinishMessage;
import dev.kirilllapushinskiy.communication.Session;
import dev.kirilllapushinskiy.core.AppServer;
import dev.kirilllapushinskiy.core.HumanBeing;

import java.util.Comparator;


public class MaxSoundtrack extends AbstractCommand {

    static private final String COMMAND_NAME = "MaxSoundtrack";

    protected MaxSoundtrack() {
        super(COMMAND_NAME, true);
    }

    public static MaxSoundtrack init() {
        return INSTANCE;
    }

    private static final MaxSoundtrack INSTANCE = new MaxSoundtrack();

    public void run(Session session) {
        super.run(session);

        System.out.println();

        if (AppServer.humanBeings.isEmpty()) {
            session.setMessage(new ErrorMessage("Такой SoundtrackName отсутствует!"));
        } else {
            HumanBeing humanBeingWithMaxSoundtrack = AppServer.humanBeings
                    .stream()
                    .max(Comparator.comparing(HumanBeing::getSoundtrackName))
                    .orElse(null);
            session.setMessage(new FinishMessage("SoundtrackName является максимальным:\n" + humanBeingWithMaxSoundtrack));
        }
    }
}
