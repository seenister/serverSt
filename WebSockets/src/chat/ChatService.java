package chat;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatService {
    private Set<ChatWebSocket> webSockets;
    Set<String> banList = new HashSet<>();


    public ChatService() {
        this.webSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void sendMessage(String data) {
        String[] subStr = data.split(":", 2);
        String metBan = "/ban";
        for (ChatWebSocket user : webSockets) {
            try {
                if (banList.contains(subStr[0])) {
                    System.out.println("you are banned!"+ banList + "-" + subStr[0]);
                }else {  user.sendString(data);}
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (subStr[1].startsWith(metBan)) {
            banList.add(subStr[1].substring(metBan.length()));
        }

    }

    public void add(ChatWebSocket webSocket) {
        webSockets.add(webSocket);
    }

    public void remove(ChatWebSocket webSocket) {
        webSockets.remove(webSocket);
    }

}
