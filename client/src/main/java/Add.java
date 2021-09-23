import common.*;

import java.time.ZonedDateTime;
import java.util.Scanner;

public class Add extends AbstractCommand {

    public Add() {
        super("add", false, true);
    }

    public void run() {
        //AddHistory("add");
        System.out.println("Добавление элемента в коллекцию: ");

        HumanBeing humanBeing = getParameterHumanBeing(Utils.getScanner());
        //System.out.println(humanBeing); // TEMP
        this.setHumanBeing(humanBeing);
        Response response = Communicator.remoteCommandExecution(this);
        System.out.println(response);
        //AppClient.humanBeings.add(humanBeing);
    }

    public static HumanBeing getParameterHumanBeing(Scanner scanner) {
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

        return new HumanBeing(name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, soundtrackName, weaponType, mood, car);
    }

    public static Car getParameterCar(Scanner scanner) {
        System.out.print("Характеристики машины. ");
        String nameCar = getParameterNameCar(scanner);

        if (nameCar == null) {
            return null;
        }

        boolean cool = getParameterCool(scanner);
        return new Car(nameCar, cool);
    }

    public static boolean getParameterCool(Scanner scanner) {
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

    public static String getParameterNameCar(Scanner scanner) {
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

    public static Mood getParameterMood(Scanner scanner) {
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

    public static WeaponType getParameterWeaponType(Scanner scanner) {
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

    public static String getParameterSoundtrackName(Scanner scanner) {
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

    public static Double getParameterImpactSpeed(Scanner scanner) {
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

    public static Boolean getParameterHasToothpick(Scanner scanner) {
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

    private static Boolean isAccept(String userAnswer) {
        return userAnswer.equalsIgnoreCase("да") || userAnswer.equalsIgnoreCase("yes") ||
                userAnswer.equalsIgnoreCase("y") || userAnswer.equalsIgnoreCase("д") ||
                userAnswer.equalsIgnoreCase("true");
    }

    private static Boolean isReject(String userAnswer) {
        return userAnswer.equalsIgnoreCase("нет") || userAnswer.equalsIgnoreCase("no") ||
                userAnswer.equalsIgnoreCase("n") || userAnswer.equalsIgnoreCase("н") ||
                userAnswer.equalsIgnoreCase("false");
    }

    public static Boolean getParameterRealHero(Scanner scanner) {
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

    public static Coordinates getParameterCoordinates(Scanner scanner) {
        System.out.println("Создание координат: ");

        Long x = getParameterX(scanner);
        int y = getParameterY(scanner);

        return new Coordinates(x, y);
    }

    public static int getParameterY(Scanner scanner) {
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

    public static Long getParameterX(Scanner scanner) {
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

    public static String getParameterName(Scanner scanner) {
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
}
