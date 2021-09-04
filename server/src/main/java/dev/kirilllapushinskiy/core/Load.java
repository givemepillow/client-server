package dev.kirilllapushinskiy.core;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.kirilllapushinskiy.entities.HumanBeing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.SortedSet;

import static dev.kirilllapushinskiy.utils.Utils.SYSTEM_VARIABLE;
import static dev.kirilllapushinskiy.utils.Utils.getObjectMapper;

/**
 * The type Load.
 */
public class Load {
    /**
     * Run.
     */
    public static void run() {
        String filename = System.getenv(SYSTEM_VARIABLE);
        String errorMessage = "Проблемы с JSON файлом!\n";

        StringBuilder jsonStrings = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                jsonStrings.append(currentLine).append("\n");
            }

            AppServer.humanBeings = getObjectMapper().readValue(jsonStrings.toString(), new TypeReference<SortedSet<HumanBeing>>(){});

        } catch (NullPointerException e) {
            errorMessage += "Убедитесь, что переменная окружения " + SYSTEM_VARIABLE + " ЗАДАНА.";
            System.err.println(errorMessage);
        } catch (FileNotFoundException e) {
            System.err.println("\nJSON файл НЕ СУЩЕСТВУЕТ при сохранении будет создан новый файл.");
            System.out.println("Установленный путь к файлу: \"" + filename + "\"\n");
        } catch (IOException e) {
            errorMessage += "Убедитесь, что указанный вами файл НУЖНОГО ФОРМАТА.\n" +
                    "Путь к файлу: \"" + filename + "\"";
            System.err.println(errorMessage);
        }
    }
}