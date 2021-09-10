import java.net.SocketAddress;
import java.util.HashSet;

public class SessionCollection extends HashSet<ClientSession> {

    private static SessionCollection sessions = new SessionCollection();

    public static SessionCollection getSessions() {
        return sessions;
    }

    @Override
    public boolean contains(Object o) {
        Integer id = null;
        if (o instanceof SocketAddress) {
            id = o.hashCode();
        } else if (o instanceof Integer) {
            id = (Integer) o;
        }
        for (ClientSession s : this) {
            if (s.equals(id)) return true;
        }
        return false;
    }

    public ClientSession get(Object o) {
        Integer id = null;
        if (o instanceof Integer)
            id = (Integer) o;
        if (o instanceof SocketAddress)
            id = o.hashCode();
        ClientSession clientSession = null;
        for (ClientSession s : this) {
            if (s.equals(id)) clientSession = s;
        }
        return clientSession;
    }
}
