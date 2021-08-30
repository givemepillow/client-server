package dev.kirilllapushinskiy.communication;

abstract class Message {

    protected String data;

    protected String getMessage() {
        if (data == null) throw new IllegalStateException("Empty message!");
        return this.data;
    }
}
