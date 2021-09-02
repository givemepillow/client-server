package dev.kirilllapushinskiy.communication;

import java.util.ArrayDeque;
import java.util.Deque;

public interface Session {

    void setMessage(Message message);

    Deque<String> history = new ArrayDeque<>();

    default Deque<String> getHistory() {
        return history;
    }

    void addHistory(String text);

}
