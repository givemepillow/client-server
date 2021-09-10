import common.Command;

import java.io.IOException;

public class AppServer {

    public static void main(String[] args) {
        try {


            Server.init();
            CommandHandler.registration(
                    HELP
            );
            Server.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    final static Command HELP = new Help();

}
