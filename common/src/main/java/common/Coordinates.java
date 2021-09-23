package common;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Long x;
    private int y;

    public Coordinates(Long x, int y) {
        this.x = x;
        this.y = y;
    }

    @JsonCreator
    public Coordinates() {
    }

    @Override
    public String toString() {
        return "(" + x +
                ", " + y +
                ')';
    }
}
