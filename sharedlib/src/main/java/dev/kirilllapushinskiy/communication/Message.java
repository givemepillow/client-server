package dev.kirilllapushinskiy.communication;

abstract class Message {

    protected String data;

    protected Message(String message) {
        if (message == null) throw new IllegalArgumentException("Null message!");
        this.data = message;
    }

    protected String getMessage() {
        if (data == null) throw new IllegalStateException("Empty message!");
        return this.data;
    }
}
