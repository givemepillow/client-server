import common.AbstractCommand;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExecuteScript extends AbstractCommand {
    public ExecuteScript() {
        super("execute_script", true, false);
    }

    @Override
    public void run() {
        System.out.print("Считывание и исполнение скрипта: \n");

        String filename = getCommandArgument();

        if (Files.exists(Paths.get(filename)) && !Files.isRegularFile(Paths.get(filename))) {
            System.out.println("Нельзя передать специальный файл в качестве скрипта!");
        } else {
                FileReader scriptFile;
                try {
                    scriptFile = new FileReader(filename);
                    BufferedReader fileCommands = new BufferedReader(scriptFile);
                    CommandHandler.start(fileCommands);
                    scriptFile.close();
                } catch (FileNotFoundException | NullPointerException exception) {
                    System.out.println("Файл не найден (проверьте права доступа или создайте файл)!");
                } catch (IOException | IllegalStateException | IllegalArgumentException exception) {
                    System.out.println(exception.getMessage() + "\n");
                }

        }
    }
}
