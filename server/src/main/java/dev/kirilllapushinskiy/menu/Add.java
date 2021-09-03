/*
package dev.kirilllapushinskiy.menu;

import dev.kirilllapushinskiy.commands.AbstractCommand;
import dev.kirilllapushinskiy.communication.Session;
import dev.kirilllapushinskiy.core.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Comparator;

public class Add extends AbstractCommand implements Serializable {

    static private final String COMMAND_NAME = "ADD";

    protected Add() {
        super(COMMAND_NAME, false);
    }

    public static Add init() {
        return INSTANCE;
    }

    private static final Add INSTANCE = new Add();

    public void run(Session session) {
        super.run(session); // ??
        System.out.println("Добавление элемента в коллекцию: ");

        HumanBeing humanBeing = getParameterHumanBeing(scanner, getParameterId());
        AppServer.humanBeings.add(humanBeing);
    }

    public HumanBeing getParameterHumanBeing(Scanner scanner, Integer ID) {
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
    }

    public Car getParameterCar(Scanner scanner) {
        System.out.print("Характеристики машины. ");
        String nameCar = getParameterNameCar(scanner);

        if (nameCar == null) {
            return null;
        }

        boolean cool = getParameterCool(scanner);
        return new Car(nameCar, cool);
    }

    public boolean getParameterCool(Scanner scanner) {
        boolean cool;
        while (true) {
            System.out.println("Машина хорошая?");
            String command = scanner.nextLine();
            if (isAccept(command)) {
                cool = true;
                break;
            } else if (isReject(command)) {
                cool = false;
                break;
            }

            System.out.println("Некорректный аргумент для Cool (Необходимо: да или нет)!");
        }
        return cool;
    }

    public String getParameterNameCar(Scanner scanner) {
        String nameCar;
        while (true) {
            System.out.print("Введите название автомобиля: ");

            try {
                nameCar = scanner.nextLine().trim();
                if (nameCar.equals("")) {
                    nameCar = null;
                    break;
                }
                break;
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        return nameCar;
    }

    public Mood getParameterMood(Scanner scanner) {
        Mood mood = null;

        while (true) {
            System.out.print("Введите настроение (SADNESS, LONGING, CALM, RAGE): ");
            try {
                String text = scanner.nextLine().trim();
                if (!text.equals("")) {
                    mood = Mood.valueOf(text.toUpperCase());
                }
                break;


            } catch (IllegalArgumentException exception) {
                System.out.println("Некорректный аргумент! Он должен соответствовать представленному набору (SADNESS, LONGING, CALM, RAGE)!");
            }
        }
        return mood;
    }

    public WeaponType getParameterWeaponType(Scanner scanner) {
        System.out.print("Тип оружия. ");
        WeaponType weaponType = null;

        while (true) {
            System.out.print("Введите тип оружия (AXE, RIFLE, PISTOL, KNIFE, BAT): ");
            try {
                String text = scanner.nextLine().trim();
                if (!text.equals("")) {
                    weaponType = WeaponType.valueOf(text.toUpperCase());
                }
                break;

            } catch (IllegalArgumentException exception) {
                System.out.println("Некорректный аргумент! Он должен соответствовать представленному набору (AXE, RIFLE, PISTOL, KNIFE, BAT)!");
            }
        }
        return weaponType;
    }

    public String getParameterSoundtrackName(Scanner scanner) {
        System.out.println("Саундтрек. ");
        String soundtrackName;
        while (true) {
            System.out.print("Введите имя саундтрека: ");

            try {
                soundtrackName = scanner.nextLine().trim();
                if (soundtrackName.equals("")) {
                    throw new IllegalArgumentException("soundtrackName не может быть пустым!");
                }
                break;
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        return soundtrackName;
    }

    public Double getParameterImpactSpeed(Scanner scanner) {
        System.out.print("Скорость удара существа. ");
        Double impactSpeed;
        while (true) {
            System.out.print("Введите скорость удара: ");

            try {
                String text = scanner.nextLine().trim();
                if (text.equals("")) {
                    impactSpeed = null;
                    break;
                }

                impactSpeed = Double.parseDouble(text);
                if (impactSpeed < 0) {
                    throw new IllegalArgumentException("impactSpeed не может быть отрицательным!");
                }
                if (impactSpeed > 10) {
                    throw new IllegalArgumentException("impactSpeed не может быть больше 10!");
                }
                break;
            } catch (NumberFormatException exception) {
                System.out.println("Некорректный аргумент для переменной impactSpeed (Необходимо: [0;10])!");
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return impactSpeed;
    }

    public Boolean getParameterHasToothpick(Scanner scanner) {
        System.out.print("Имеется ли у существа зубочистка. ");
        String command;
        boolean hasToothpick;
        while (true) {
            System.out.println("Человеческое существо с зубочисткой?");
            command = scanner.nextLine().trim();
            if (isAccept(command)) {
                hasToothpick = true;
                break;
            } else if (isReject(command)) {
                hasToothpick = false;
                break;
            }
            System.out.println("Некорректный аргумент для hasToothpick (Необходимо: да или нет)!");
        }
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

    public Boolean getParameterRealHero(Scanner scanner) {
        String command;
        System.out.print("Является ли человек реальным. ");

        boolean realHero;
        while (true) {
            System.out.println("Человеческое существо реально?");
            command = scanner.nextLine();
            if (isAccept(command)) {
                realHero = true;
                break;
            } else if (isReject(command)) {
                realHero = false;
                break;
            } else {
                System.out.println("Некорректный аргумент для realHero (Необходимо: да или нет)!");
            }
        }
        return realHero;
    }

    public Coordinates getParameterCoordinates(Scanner scanner) {
        System.out.println("Создание координат: ");

        Long x = getParameterX(scanner);
        int y = getParameterY(scanner);

        return new Coordinates(x, y);
    }

    public int getParameterY(Scanner scanner) {
        int y;

        while (true) {
            System.out.print("Введите y: ");

            try {
                y = Integer.parseInt(scanner.nextLine());
                if (y > 270) {
                    System.out.println("Y должен быть меньше 270!");
                    continue;
                }
                break;
            } catch (NumberFormatException exception) {
                System.out.println("Некорректный аргумент для переменной y!");
            }
        }
        return y;
    }

    public Long getParameterX(Scanner scanner) {
        long x;
        while (true) {
            System.out.print("Введите x: ");

            try {
                x = Long.parseLong(scanner.nextLine().trim());
                if (x <= -146) {
                    System.out.println("X должен быть больше -146!");
                    continue;
                }
                break;
            } catch (NumberFormatException exception) {
                System.out.println("Некорректный аргумент для переменной х!");
            }
        }
        return x;
    }

    public String getParameterName(Scanner scanner) {
        String name;

        while (true) {
            System.out.print("Введите имя: ");
            try {
                name = scanner.nextLine().trim();
                if (name.equals("")) {
                    throw new IllegalArgumentException("Введите значение для имени!");
                }
                break;
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
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
*/
