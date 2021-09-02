package dev.kirilllapushinskiy.menu;


import dev.kirilllapushinskiy.commands.AbstractCommand;
import dev.kirilllapushinskiy.communication.AnswerMessage;
import dev.kirilllapushinskiy.communication.Message;
import dev.kirilllapushinskiy.communication.Session;

public class Help extends AbstractCommand {

    static private final String COMMAND_NAME = "HELP";

    protected Help() {
        super(COMMAND_NAME, false);
    }

    public static Help init() {
        return INSTANCE;
    }

    private static final Help INSTANCE = new Help();

    private String help =
            "Меню:\n" +
                    "help : вывести справку по доступным командам\n" +
                    "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                    "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                    "add {element} : добавить новый элемент в коллекцию\n" +
                    "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                    "remove_by_id id : удалить элемент из коллекции по его id\n" +
                    "clear : очистить коллекцию\n" +
                    "save : сохранить коллекцию в файл\n" +
                    "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                    "exit : завершить программу (без сохранения в файл)\n" +
                    "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                    "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                    "history : вывести последние 11 команд (без их аргументов)\n" +
                    "max_by_soundtrack_name : вывести любой объект из коллекции, значение поля soundtrackName которого является максимальным\n" +
                    "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки\n" +
                    "print_unique_weapon_type : вывести уникальные значения поля weaponType всех элементов в коллекции\n";

    public void run(Session session) {
        Message message = new AnswerMessage(help);
        session.setMessage(message);
        super.run(session);
    }

}