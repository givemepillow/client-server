package dev.kirilllapushinskiy.entities;

public enum Mood {
    SADNESS,
    LONGING,
    RAGE,
    CALM;

    @Override
    public String toString() {
        return name();
    }

}
