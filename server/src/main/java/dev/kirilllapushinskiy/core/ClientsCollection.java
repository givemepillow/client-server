package dev.kirilllapushinskiy.core;

import java.net.SocketAddress;
import java.util.HashSet;
import java.util.Set;

public class ClientsCollection extends HashSet<SessionClient> {

    public ClientsCollection(int size) {
        super(size);
        this.addresses = new HashSet<>(size);
    }

    @Override
    public boolean add(SessionClient serverClient) {
        addresses.add(serverClient.getAddress().toString());
        return super.add(serverClient);
    }

    private final Set<String> addresses;

    public SessionClient getClient(SocketAddress addr) {
        String str = addr.toString();
        for (SessionClient c : this) {
            if (c.getAddress().toString().equalsIgnoreCase(str))
                return c;
        }
        return null;
    }

    public boolean conclude(SocketAddress addr) {
        String str = addr.toString();
        for (String address : addresses) {
            if (address.equalsIgnoreCase(str))
                return true;
        }
        return false;
    }
}
