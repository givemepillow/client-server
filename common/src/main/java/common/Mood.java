package common;

import java.io.Serializable;

public enum Mood implements Serializable {
    SADNESS,
    LONGING,
    RAGE,
    CALM;

    @Override
    public String toString() {
        return name();
    }

}
