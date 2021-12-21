package chat;

public enum Commands {
    BAN ("/ban"),
    UNBAN("/unban"),
    CLEAR("/clear");

    private String command;

    Commands(String s) {
    }

    public String getCommand() {
        return command;
    }
}
