package common;

import java.io.Serializable;

public class Response implements Serializable {
    private final String data;

    public Response(String message){
        data = message;
    }

    @Override
    public String toString() {
        return data + "\n";
    }

    public String getData(){
        return data;
    }
}
