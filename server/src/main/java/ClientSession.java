import common.Response;
import common.Serializator;
import common.Session;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Objects;

public class ClientSession implements Session {

    private final int id;

    private Response response;

    public final SocketAddress address;

    private boolean ready = false;

    ClientSession(SocketAddress address) {
        this.id = address.hashCode();
        this.address = address;
    }

    public void setResponse(Response response) {
        if (response != null) {
            ready = true;
            this.response = response;
        } else {
            this.response = new Response("Сервер не дал ответа :(");
        }
    }

    public void sendResponse(DatagramChannel channel) throws IOException {
        if (isReady()) {
            ByteBuffer sendBuffer = Serializator.serialize(response);
            channel.send(sendBuffer, address);
            ready = false;
        } else {
            throw new IllegalStateException("Ответ не готов!");
        }
    }

    public boolean isReady() {
        return ready;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Integer) {
            return this.id == (Integer) o;
        }
        if (o == null || getClass() != o.getClass()) return false;
        ClientSession clientSession = (ClientSession) o;
        return id == clientSession.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
