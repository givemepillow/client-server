import java.util.ArrayDeque;
import java.util.Deque;

public class Historian {

    private Deque<String> history = new ArrayDeque<>();

    public void addHistory(String text) {
        if (history.size() == 11) {
            history.removeFirst();
        }
        history.add(text);
    }

    public String getHistory() {
        StringBuilder s = new StringBuilder();
        for (String h: history) {
            s.append("\n");
            s.append(h);
        }
        return s.toString();
    }
}