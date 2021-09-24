import common.Command;
import common.CommandPackage;
import common.Response;
import common.Serializator;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Communicator {

    private static final int SERVER_PORT = 6699;
    private static DatagramChannel channel;
    private static InetSocketAddress address;
    private static final int BUFFER_SIZE = 12*1024;
    private static final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
    private Communicator(){

    }

    public static void init(){
        try{
            address = new InetSocketAddress(InetAddress.getLocalHost(),SERVER_PORT);
            channel = DatagramChannel.open();
            channel.socket().setSoTimeout(3000);

        } catch(IOException e){
            System.err.println("Не удалось настроить соединение!");
        }
    }

    private static void sendCommand(Command command) throws IOException{
        if(address == null){
            throw new IllegalStateException("Необходима инициализация коммуникатора!");
        }
        ByteBuffer sendBuffer = Serializator.serialize(CommandPackage.pack(command));
        channel.send(sendBuffer, address);
    }

    private static Response receive() throws IOException, ClassNotFoundException {
        buffer.clear();
        DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.capacity());
        channel.socket().receive(packet);
        return (Response) Serializator.deserialize(buffer);

    }

    public static Response remoteCommandExecution(Command command) {
        Response response = null;
        try {
            Communicator.sendCommand(command);
            response =  Communicator.receive();
        } catch(SocketTimeoutException e) {
            response = new Response("Сервер не доступен, попробуйте позже... :(");
        }
        catch(IOException | ClassNotFoundException exception){
            exception.printStackTrace();
        }
        return response;
    }

}
