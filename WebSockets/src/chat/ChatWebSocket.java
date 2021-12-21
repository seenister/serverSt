package chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author v.chibrikov
 * <p/>
 * Пример кода для курса на https://stepic.org/
 * <p/>
 * Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class ChatWebSocket {
    private ChatService chatService;
    private Session session;
    static Set<String> banList = new HashSet<>();

    public ChatWebSocket(ChatService chatService) {
        this.chatService = chatService;
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        chatService.add(this);
        this.session = session;
    }

    public String[] parseData(String data) {
        String[] subStr = data.split(":", 2);
        String sender = subStr[0];

        String[] subSubStr = subStr[1].split(" ", 2);
        String command = subSubStr[0];
        String commandParam = subSubStr[1];

        String[] arrayData = {sender, command, commandParam};
        return arrayData;
    }

    public boolean chooseCommand(String data) {

        if (parseData(data)[1].startsWith("/")) {
            switch (parseData(data)[1]) {
                case "/ban":
                    ban(data);
                case "/unban":
                    unban(data);
                case "/clear":


                default:
                    try {
                        session.getRemote().sendString("Wrong command");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            return true;
        }
        return false;
    }

    public void ban(String data) {
        banList.add(parseData(data)[2]);
    }

    public void unban(String data) {
        banList.remove(parseData(data)[2]);
    }

    public void clear(String data) {

    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        try {
            if (banList.contains(parseData(data)[1])) {
                session.getRemote().sendString("you are banned!");
            } else {
                if (!chooseCommand(data)) {
                    chatService.sendMessage(data);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        chatService.remove(this);
    }

    public void sendString(String data) {
        try {
            session.getRemote().sendString(data);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}