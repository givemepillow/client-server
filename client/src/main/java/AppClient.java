import common.Command;

public class AppClient {

    public static void main(String[] args) {
        Communicator.init();
        CommandHandler.registration(
                HELP,
                EXIT,
                CLEAR,
                ADD,
                SHOW,
                HISTORY,
                UPDATE,
                INFO,
                REMOVEBYID,
                UNIQUEWEAPON,
                MAXSOUNTRACK,
                FILTERNAME,
                ADDIFMIN,
                ADDIFMAX,
                EXECUTESCRIPT
        );
        CommandHandler.start();

    }

    final static Command HELP = new Help();
    final static Command EXIT = new Exit();
    final static Command CLEAR = new Clear();
    final static Command ADD = new Add();
    final static Command SHOW = new Show();
    final static Command HISTORY = new History();
    final static Command UPDATE = new Update();
    final static Command INFO = new Info();
    final static Command REMOVEBYID = new RemoveById();
    final static Command UNIQUEWEAPON = new UniqueWeapon();
    final static Command MAXSOUNTRACK = new MaxSoundtrack();
    final static Command FILTERNAME = new FilterName();
    final static Command ADDIFMIN = new AddIfMin();
    final static Command ADDIFMAX = new AddIfMax();
    final static Command EXECUTESCRIPT = new ExecuteScript();
}
