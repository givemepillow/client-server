import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import common.HumanBeing;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;

public class Storage {
    public static SortedSet<HumanBeing> humanBeings = new TreeSet<>();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    public static final String SYSTEM_VARIABLE = "PATH_TO_JSON_FILE";

    private static ObjectMapper getObjectMapper() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        OBJECT_MAPPER.registerModule(javaTimeModule);
        return OBJECT_MAPPER;
    }

    public static void init() {
        String filename = System.getenv(SYSTEM_VARIABLE);
        String errorMessage = "Проблемы с JSON файлом!\n";
        // https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
        StringBuilder jsonStrings = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                jsonStrings.append(currentLine).append("\n");
            }


            // https://www.baeldung.com/jackson-object-mapper-tutorial
            humanBeings = getObjectMapper().readValue(jsonStrings.toString(), new TypeReference<SortedSet<HumanBeing>>(){});

        } catch (NullPointerException e) {
            errorMessage += "Убедитесь, что переменная окружения " + SYSTEM_VARIABLE + " ЗАДАНА.";
            throw new NoSuchElementException(errorMessage);
        } catch (FileNotFoundException e) {
            System.err.println("\nJSON файл НЕ СУЩЕСТВУЕТ при сохранении будет создан новый файл.");
            System.out.println("Установленный путь к файлу: \"" + filename + "\"\n");
        } catch (IOException e) {
            errorMessage += "Убедитесь, что указанный вами файл НУЖНОГО ФОРМАТА.\n" +
                    "Путь к файлу: \"" + filename + "\"";
            throw new FileSystemNotFoundException(errorMessage);
        }
    }

    public static void update(HumanBeing hb) {
        Storage.humanBeings.removeIf((human -> human.getId().equals(hb.getId())));
        humanBeings.add(hb);
        saver();
        System.out.println("Обновлена сущность с id " + hb.getId());
    }

    public static void clear() {
        Storage.humanBeings.clear();
        // Убрать комментарии для сохранения в файл послед отчистки
        //saver();
        //System.out.println("Все сущности удалены.");
    }

    public static void save(HumanBeing hb) {
        hb.initId(getParameterId());
        humanBeings.add(hb);
        // Убрать комментарии для сохранения в файл послед добавления
        //saver();
        //System.out.println("Сохранена сущность с id " + hb.getId());
    }

    public static void remove(Integer id) {
        Storage.humanBeings.removeIf((human -> human.getId().equals(id)));
        // Убрать комментарии для сохранения в файл послед удаления
        //saver();
        //System.out.println("Удалена сущность с id " + id);
    }

    public static void saver() {
        ObjectMapper objectMapper = getObjectMapper();
        try {
            ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
            String output = writer.writeValueAsString(humanBeings);
            String filename = System.getenv(SYSTEM_VARIABLE);
            try (
                    FileOutputStream fos = new FileOutputStream(filename);
                    PrintStream printStream = new PrintStream(fos)
            ) {
                printStream.println(output);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }



    public static Integer getParameterId() {
        HumanBeing humanBeingWithMaxID = Storage.humanBeings
                .stream()
                .max(Comparator.comparing(HumanBeing::getId))
                .orElse(null);
        int id;
        if (humanBeingWithMaxID == null) {
            id = 1;
        } else {
            id = humanBeingWithMaxID.getId() + 1;
        }
        return id;
    }
}
