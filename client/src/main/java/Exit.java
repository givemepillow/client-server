import common.AbstractCommand;

public class Exit extends AbstractCommand {

    public Exit() {
        super("exit", false, false);
    }

    @Override
    public void run() {
        System.out.println("\nЗавершение работы программы...");
        System.exit(0);
    }
}
