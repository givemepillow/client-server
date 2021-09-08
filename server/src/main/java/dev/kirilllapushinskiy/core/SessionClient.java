package dev.kirilllapushinskiy.core;

import dev.kirilllapushinskiy.communication.Message;
import dev.kirilllapushinskiy.communication.Serializator;
import dev.kirilllapushinskiy.communication.Session;
import dev.kirilllapushinskiy.entities.HumanBeing;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SessionClient implements Session {

    public SessionClient(SocketAddress address) {
        this.address = address;
    }

    private final SocketAddress address;

    private Message message;

    private List<String> args = new ArrayList<>();

    private Object object;

    private String response;

    private String currentCommand = null;

    int state = 0;

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public Message getMessage() {
        return message;
    }

    @Override
    public Object getObject() {
        return object;
    }

    @Override
    public void setObject(Object o) {
        if (!(o instanceof HumanBeing)) throw new IllegalArgumentException("Requires HumanBeing!");
        this.object = o;
    }

    @Override
    public void destroyObject() {
        this.object = null;
    }



    public void sendResponse(DatagramChannel channel) throws IOException {

        if (address == null) throw new IllegalStateException("Address is null!");
        if (message == null) throw new IllegalStateException("Message is null!");

        ByteBuffer buffer = Serializator.serialize(message);
        channel.send(buffer, address);

    }

    public SocketAddress getAddress() {
        return address;
    }

    public void addHistory(String text) {
        if (history.size() == 11) {
            history.removeFirst();
        }
        history.add(text);
    }

    @Override
    public String getCurrentCommand() {
        return currentCommand;
    }

    @Override
    public List<String> getArgs() {
        return this.args;
    }

    @Override
    public void setArgs(String[] args) {
        this.args.clear();
        this.args.addAll(Arrays.asList(args));
    }
    @Override
    public void upState() {
        state += 1;
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setCurrentCommand(String commandName) {
        currentCommand = commandName;
    }

    @Override
    public void setState(int i) {
        state = i;
    }

    @Override
    public String getResponse() {
        return response;
    }

    @Override
    public void setResponse(String response) {
        this.response = response;
    }


}
