import common.Command;

public class AppClient {

    public static void main(String[] args) {
        Communicator.init();
        CommandHandler.registration(
                HELP,
                EXIT,
                CLEAR
        );
        CommandHandler.start();

    }

    final static Command HELP = new Help();
    final static Command EXIT = new Exit();
    final static Command CLEAR = new Clear();
}
