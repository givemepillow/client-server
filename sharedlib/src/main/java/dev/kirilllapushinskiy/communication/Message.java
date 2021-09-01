package dev.kirilllapushinskiy.communication;

import java.io.Serializable;

public abstract class Message implements Serializable {

    protected String data;

    protected Message(String message) {
        if (message == null) throw new IllegalArgumentException("Null message!");
        this.data = message;
    }

    public String getMessage() {
        if (data == null) throw new IllegalStateException("Empty message!");
        return this.data;
    }

}
