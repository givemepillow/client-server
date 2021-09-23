package common;

public interface Session {
    void setResponse(Response response);
    HumanBeing getHumanBeing();
    void setHumanBeing(HumanBeing hb);
    String getHistory();
    void setHistory(Command command);
}
