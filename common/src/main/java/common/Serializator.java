package common;

import java.io.*;
import java.nio.ByteBuffer;

public class Serializator {
    private Serializator(){

    }

    static public ByteBuffer serialize(Serializable object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(object);
        out.flush();
        return ByteBuffer.wrap(bos.toByteArray());
    }

    static public Object deserialize(ByteBuffer buffer) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(buffer.array());
        ObjectInputStream in = new ObjectInputStream(bis);
        return in.readObject();
    }
}
