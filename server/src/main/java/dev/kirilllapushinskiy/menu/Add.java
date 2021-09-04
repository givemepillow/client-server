package dev.kirilllapushinskiy.menu;

import dev.kirilllapushinskiy.commands.AbstractCommand;
import dev.kirilllapushinskiy.communication.*;
import dev.kirilllapushinskiy.core.*;

import java.time.ZonedDateTime;
import java.util.Comparator;

public class Add extends AbstractCommand {

    static private final String COMMAND_NAME = "ADD";

    protected Add() {
        super(COMMAND_NAME, false);
    }

    public static Add init() {
        return INSTANCE;
    }

    private static final Add INSTANCE = new Add();

    public void run(Session session) {

        int state = session.getState();
        HumanBeing hb;

        switch (state) {

            case 0:
                super.run(session);
                hb = new HumanBeing();
                hb.coordinates = new Coordinates();
                hb.car = new Car();
                session.setObject(hb);
                session.setCurrentCommand(COMMAND_NAME);
                session.setMessage(new PromptMessage("Добавление элемента в коллекцию.\nВведите имя:"));
                session.upState();
                // Введите название автомобиля.
                break;
            case 1:
                hb = (HumanBeing) session.getObject();
                hb.name = getParameterName(session);
                if (session.getState() == 2)
                    session.setMessage(new PromptMessage("Создание координат.\nВведите x:"));
                break;
            case 2:
                hb = (HumanBeing) session.getObject();
                hb.coordinates.setX(getParameterX(session));
                if (session.getState() == 3)
                    session.setMessage(new PromptMessage("Введите y:"));
                break;
            case 3:
                hb = (HumanBeing) session.getObject();
                hb.coordinates.setY(getParameterY(session));
                if (session.getState() == 4)
                    session.setMessage(new PromptMessage("Является ли человек реальным.\nЧеловеческое существо реально?"));
                break;
            case 4:
                hb = (HumanBeing) session.getObject();
                hb.realHero = getParameterRealHero(session);
                if (session.getState() == 5)
                    session.setMessage(new PromptMessage("Имеется ли у существа зубочистка.\nЧеловеческое существо с зубочисткой?"));
                break;
            case 5:
                hb = (HumanBeing) session.getObject();
                hb.hasToothpick = getParameterHasToothpick(session);
                if (session.getState() == 6)
                    session.setMessage(new PromptMessage("Скорость удара существа.\nВведите скорость удара:"));
                break;
            case 6:
                hb = (HumanBeing) session.getObject();
                hb.impactSpeed = getParameterImpactSpeed(session);
                if (session.getState() == 7)
                    session.setMessage(new PromptMessage("Саундтрек. \nВведите имя саундтрека:"));
                break;
            case 7:
                hb = (HumanBeing) session.getObject();
                hb.soundtrackName = getParameterSoundtrackName(session);
                if (session.getState() == 8)
                    session.setMessage(new PromptMessage("Тип оружия.\nВведите тип оружия (AXE, RIFLE, PISTOL, KNIFE, BAT):"));
                break;
            case 8:
                hb = (HumanBeing) session.getObject();
                hb.weaponType = getParameterWeaponType(session);
                if (session.getState() == 9)
                    session.setMessage(new PromptMessage("Введите настроение (SADNESS, LONGING, CALM, RAGE):"));
                break;
            case 9:
                hb = (HumanBeing) session.getObject();
                hb.mood = getParameterMood(session);
                if (session.getState() == 10)
                    session.setMessage(new PromptMessage("Характеристики машины.\nВведите название автомобиля:"));
                break;
            case 10:
                hb = (HumanBeing) session.getObject();
                hb.car.setName(getParameterNameCar(session));
                if (session.getState() == 11)
                    session.setMessage(new PromptMessage("Машина хорошая?"));
                break;
            case 11:
                hb = (HumanBeing) session.getObject();
                hb.car.setCool(getParameterCool(session));
                if (session.getState() == 12) {
                    hb.id = getParameterId();
                    hb.creationDate = ZonedDateTime.now();
                    AppServer.humanBeings.add(hb);
                    session.destroyObject();
                    Save.run();
                    session.setMessage(new FinishMessage("Готово! Объект успешно сохранён."));
                    break;
                }
            default:
                session.setMessage(new ErrorMessage("Ошибка состояния!"));
        }

        //HumanBeing humanBeing = getParameterHumanBeing(scanner, getParameterId());

    }

    /*public HumanBeing getParameterHumanBeing(Scanner scanner, Integer ID) {
        Integer id;
        id = ID;
        String name = getParameterName(scanner);
        Coordinates coordinates = getParameterCoordinates(scanner);
        ZonedDateTime creationDate = ZonedDateTime.now();
        Boolean realHero = getParameterRealHero(scanner);
        Boolean hasToothpick = getParameterHasToothpick(scanner);
        Double impactSpeed = getParameterImpactSpeed(scanner);
        String soundtrackName = getParameterSoundtrackName(scanner);
        WeaponType weaponType = getParameterWeaponType(scanner);
        Mood mood = getParameterMood(scanner);
        Car car = getParameterCar(scanner);

        return new HumanBeing(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, soundtrackName, weaponType, mood, car);
    }*/

    /*public Car getParameterCar(Scanner scanner) {
        System.out.print("Характеристики машины. ");
        String nameCar = getParameterNameCar(scanner);

        if (nameCar == null) {
            return null;
        }

        boolean cool = getParameterCool(scanner);
        return new Car(nameCar, cool);
    }*/

    public boolean getParameterCool(Session session) {
        String command = session.getResponse().trim();
        if (isAccept(command)) {
            session.upState();
            return true;
        } else if (isReject(command)) {
            return false;
        } else {
            session.setMessage(new AnswerMessage("Некорректный аргумент для Cool (Необходимо: да или нет)!"));
            return false;
        }
    }

    public String getParameterNameCar(Session session) {
        String nameCar;
        try {
            nameCar = session.getResponse().trim();
            if (nameCar.equals("")) {
                return null;
            }
        } catch (Exception exception) {
            session.setMessage(new AnswerMessage(exception.getMessage()));
            return null;
        }
        session.upState();
        return nameCar;
    }

    public Mood getParameterMood(Session session) {
        Mood mood = null;
        try {
            String text = session.getResponse().trim();
            if (!text.equals("")) {
                mood = Mood.valueOf(text.toUpperCase());
            }

        } catch (IllegalArgumentException exception) {
            session.setMessage(new AnswerMessage("Некорректный аргумент! Он должен соответствовать представленному набору (SADNESS, LONGING, CALM, RAGE)!"));
            return null;
        }
        session.upState();
        return mood;
    }

    public WeaponType getParameterWeaponType(Session session) {

        WeaponType weaponType = null;
        try {
            String text = session.getResponse().trim();
            if (!text.equals("")) {
                weaponType = WeaponType.valueOf(text.toUpperCase());
            }

        } catch (IllegalArgumentException exception) {
            session.setMessage(new AnswerMessage("Некорректный аргумент! Он должен соответствовать представленному набору (AXE, RIFLE, PISTOL, KNIFE, BAT)!"));
            return null;
        }
        session.upState();
        return weaponType;
    }

    public String getParameterSoundtrackName(Session session) {

        String soundtrackName;

        try {
            soundtrackName = session.getResponse().trim();
            if (soundtrackName.equals("")) {
                throw new IllegalArgumentException("soundtrackName не может быть пустым!");
            }
        } catch (Exception exception) {
            session.setMessage(new AnswerMessage(exception.getMessage()));
            return null;
        }
        session.upState();
        return soundtrackName;
    }

    public Double getParameterImpactSpeed(Session session) {

        double impactSpeed;

        try {
            String text = session.getResponse().trim();
            if (text.equals("")) {
                return null;
            }

            impactSpeed = Double.parseDouble(text);
            if (impactSpeed < 0) {
                throw new IllegalArgumentException("impactSpeed не может быть отрицательным!");
            }
            if (impactSpeed > 10) {
                throw new IllegalArgumentException("impactSpeed не может быть больше 10!");
            }
        } catch (NumberFormatException exception) {
            session.setMessage(new AnswerMessage("Некорректный аргумент для переменной impactSpeed (Необходимо: [0;10])!"));
            return null;
        } catch (IllegalArgumentException exception) {
            session.setMessage(new AnswerMessage(exception.getMessage()));
            return null;
        }
        session.upState();
        return impactSpeed;
    }

    public Boolean getParameterHasToothpick(Session session) {

        String command;
        boolean hasToothpick;
        command = session.getResponse().trim();
        if (isAccept(command)) {
            hasToothpick = true;
        } else if (isReject(command)) {
            hasToothpick = false;
        } else {
            session.setMessage(new AnswerMessage("Некорректный аргумент для hasToothpick (Необходимо: да или нет)!"));
            return null;
        }
        session.upState();
        return hasToothpick;
    }

    private Boolean isAccept(String userAnswer) {
        return userAnswer.equalsIgnoreCase("да") || userAnswer.equalsIgnoreCase("yes") ||
                userAnswer.equalsIgnoreCase("y") || userAnswer.equalsIgnoreCase("д") ||
                userAnswer.equalsIgnoreCase("true");
    }

    private Boolean isReject(String userAnswer) {
        return userAnswer.equalsIgnoreCase("нет") || userAnswer.equalsIgnoreCase("no") ||
                userAnswer.equalsIgnoreCase("n") || userAnswer.equalsIgnoreCase("н") ||
                userAnswer.equalsIgnoreCase("false");
    }

    public Boolean getParameterRealHero(Session session) {
        String command;


        boolean realHero;

        command = session.getResponse().trim();
        if (isAccept(command)) {
            realHero = true;
        } else if (isReject(command)) {
            realHero = false;
        } else {
            session.setMessage(new AnswerMessage("Некорректный аргумент для realHero (Необходимо: да или нет)!"));
            return null;
        }
        session.upState();
        return realHero;
    }

    /*public Coordinates getParameterCoordinates(Session session) {


        Long x = getParameterX(session);
        int y = getParameterY(session);

        return new Coordinates(x, y);
    }*/

    public int getParameterY(Session session) {
        int y;

        try {
            y = Integer.parseInt(session.getResponse());
            if (y > 270) {
                session.setMessage(new AnswerMessage("Y должен быть меньше 270!"));
                return 0;
            }
        } catch (NumberFormatException exception) {
            session.setMessage(new AnswerMessage("Некорректный аргумент для переменной y!"));
            return 0;
        }

        session.upState(); // !!!
        return y;
    }

    public Long getParameterX(Session session) {
        long x;
        try {
            x = Long.parseLong(session.getResponse().trim());
            if (x <= -146) {
                session.setMessage(new AnswerMessage("X должен быть больше -146!"));
                return null;
            }
        } catch (NumberFormatException exception) {
            session.setMessage(new AnswerMessage("Некорректный аргумент для переменной х!"));
            return null;
        }
        session.upState();
        return x;
    }


    public String getParameterName(Session session) {
        String name;

        name = session.getResponse().trim();
        if (name.equals("")) {
            session.setMessage(new AnswerMessage("Введите значение для имени!"));
            return null;
        }
        session.upState(); //!!!
        return name;
    }


    public Integer getParameterId() {
        HumanBeing humanBeingWithMaxID = AppServer.humanBeings
                .stream()
                .max(Comparator.comparing(HumanBeing::getId))
                .orElse(null);

        int id;
        if (humanBeingWithMaxID == null) {
            id = 1;
        } else {
            id = humanBeingWithMaxID.getId() + 1;
        }

        if (id <= 0) {
            throw new IllegalArgumentException("id не может быть отрицательным или равным нулю!");
        }
        return id;
    }
}
