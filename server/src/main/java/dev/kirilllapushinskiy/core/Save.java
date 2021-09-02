package dev.kirilllapushinskiy.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static dev.kirilllapushinskiy.utils.Utils.SYSTEM_VARIABLE;
import static dev.kirilllapushinskiy.utils.Utils.getObjectMapper;

public class Save {

    public static void run() {

        System.out.println("Сохранение коллекции в файл: ");
        ObjectMapper objectMapper = getObjectMapper();

        try {
            ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
            String output = writer.writeValueAsString(AppServer.humanBeings);
            String filename = System.getenv(SYSTEM_VARIABLE);
            try (
                    FileOutputStream fos = new FileOutputStream(filename);
                    PrintStream printStream = new PrintStream(fos)
            ) {
                printStream.println(output);
                System.out.println("Коллекция сохранена в файл!");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}