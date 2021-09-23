import common.Command;

import java.io.IOException;

public class AppServer {

    public static void main(String[] args) {
        try {
            Storage.init();

            Server.init();
            CommandHandler.registration(
                    HELP,
                    ADD,
                    SHOW,
                    HISTORY,
                    CHECKID,
                    UPDATE,
                    INFO,
                    REMOVEBYID,
                    UNIQUEWEAPON,
                    CLEAR,
                    MAXSOUNTRACK,
                    FILTERNAME,
                    ADDIFMIN,
                    ADDIFMAX,
                    CLIENTEXIT
            );
            Server.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    final static Command HELP = new Help();
    final static Command ADD = new Add();
    final static Command SHOW = new Show();
    final static Command UPDATE = new Update();
    final static Command HISTORY = new History();
    final static Command CHECKID = new CheckId();
    final static Command INFO = new Info();
    final static Command REMOVEBYID = new RemoveById();
    final static Command UNIQUEWEAPON = new UniqueWeapon();
    final static Command CLEAR = new Clear();
    final static Command MAXSOUNTRACK = new MaxSoundtrack();
    final static Command FILTERNAME = new FilterName();
    final static Command ADDIFMIN = new AddIfMin();
    final static Command ADDIFMAX = new AddIfMax();
    final static Command CLIENTEXIT = new Exit();
}
