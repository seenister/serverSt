package chat;

import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;

public class ParseData {
    private String data;
    private String sender;
    private String command;
    private String commandParam;
    private Session session;

    public ParseData(String data) {
        this.data = data;
    }

    public void parseData(String data) {
        String[] subStr = data.split(":", 2);
        sender = subStr[0];

        String[] subSubStr = subStr[1].split(" ", 2);
        if (subStr.length != 2){
            try {
                session.getRemote().sendString("Wrong command");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        command = subSubStr[0];
        commandParam = subSubStr[1];

    }

    public String getData() {
        return data;
    }

    public String getSender() {
        return sender;
    }

    public String getCommand() {
        return command;
    }

    public String getCommandParam() {
        return commandParam;
    }
}
