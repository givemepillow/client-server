package common;

import java.io.Serializable;

public enum WeaponType implements Comparable<WeaponType>, Serializable {
    AXE,
    PISTOL,
    RIFLE,
    KNIFE,
    BAT;

    @Override
    public String toString() {
        return name();
    }

}
