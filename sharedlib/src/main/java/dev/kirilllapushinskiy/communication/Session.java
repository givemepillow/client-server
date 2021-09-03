package dev.kirilllapushinskiy.communication;

import java.util.ArrayDeque;
import java.util.Deque;

public interface Session {

    void setMessage(Message message);

    Message getMessage();

    Deque<String> history = new ArrayDeque<>();

    default Deque<String> getHistory() {
        return history;
    }

    void addHistory(String text);

    String getCurrentCommand();

    String[] getArgs();

    void setArgs(String[] args);

    void upState();

    int getState();

    void setCurrentCommand(String commandName);

    void setState(int i);

    String getResponse();

}
