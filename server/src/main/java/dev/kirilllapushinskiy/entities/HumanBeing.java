package dev.kirilllapushinskiy.entities;

import com.fasterxml.jackson.annotation.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class HumanBeing implements Comparable<HumanBeing> {
    public Integer id;
    public String name;
    public Coordinates coordinates;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    public ZonedDateTime creationDate;
    public Boolean realHero;
    public Boolean hasToothpick;
    public Double impactSpeed;
    public String soundtrackName;
    public WeaponType weaponType;
    public Mood mood;
    public Car car;

    @JsonCreator
    public HumanBeing() {}

    public HumanBeing(Integer id, String name, Coordinates coordinates, ZonedDateTime creationDate, boolean realHero, boolean hasToothpick, Double impactSpeed, String soundtrackName, WeaponType weaponType, Mood mood, Car car) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.soundtrackName = soundtrackName;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
    }

    @Override
    public String toString() {
        return "#" + id + " " + name +
                "\nКоординаты = " + coordinates +
                "\nДата создания: " + creationDate.
                format(DateTimeFormatter.ofPattern("dd-MM-yyyy' 'HH:mm:ss")) +
                " " + creationDate.getZone() +
                "\nНастоящий персонаж - " + realHero +
                ", имеет зубочистку - " + hasToothpick +
                ".\nСкорость удара: " + impactSpeed +
                "\nСаундтрек: '" + soundtrackName + '\'' +
                "\nНастроение: " + mood +
                "\nТип оружия: " + weaponType +
                "\nТранспортное средство: " + car +
                "\n";
    }

    @JsonCreator
    public Integer getId() { return id; }

    @JsonGetter
    public String getName() { return name; }

    @JsonGetter
    public Coordinates getCoordinates() { return coordinates; }

    @JsonGetter
    public ZonedDateTime getCreationDate() { return creationDate;}

    @JsonGetter
    public Boolean getRealHero() { return realHero; }

    @JsonGetter
    public boolean isHasToothpick() { return hasToothpick; }

    @JsonGetter
    public Double getImpactSpeed() { return impactSpeed; }

    @JsonGetter
    public String getSoundtrackName() { return soundtrackName; }

    @JsonGetter
    public WeaponType getWeaponType() { return weaponType; }

    @JsonGetter
    public Mood getMood() { return mood; }

    @JsonGetter
    public Car getCar() { return car; }

    @Override
    public int compareTo(HumanBeing other) {
        return Integer.compare(this.id, other.id);
    }
}
