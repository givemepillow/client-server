import common.*;

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

    private HumanBeing humanBeing;

    private Historian historian = new Historian();

    ClientSession(SocketAddress address) {
        this.id = address.hashCode();
        this.address = address;
    }

    public void setHumanBeing(HumanBeing hb) {
        if (hb == null)
            throw new IllegalStateException("Сущность == null!");
        this.humanBeing = hb;
    }

    @Override
    public String getHistory() {
        return historian.getHistory();
    }

    @Override
    public void setHistory(Command command) {
        historian.addHistory(command.getCommandName());
    }

    public HumanBeing getHumanBeing() {
        if (humanBeing == null)
            throw new IllegalStateException("Сущность не установлена!");
        return this.humanBeing;
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
