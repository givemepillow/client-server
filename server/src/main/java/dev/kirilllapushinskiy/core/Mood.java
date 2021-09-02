package dev.kirilllapushinskiy.core;

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
