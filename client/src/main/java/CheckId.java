public class CheckId extends RemoteCommand{

    protected CheckId() {
        super("checkid", true, false);
    }

    @Override
    public boolean isInternal() {
        return true;
    }
}
