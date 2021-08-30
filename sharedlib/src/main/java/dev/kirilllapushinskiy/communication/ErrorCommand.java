package dev.kirilllapushinskiy.communication;

public class ErrorCommand extends Message {

    public ErrorCommand(String error) {
        super(error);
    }

}
