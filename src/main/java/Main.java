import model.EscapeRoomManager;
import model.SubscribersManager;

public class Main {

    public static void main(String[] args) {
        EscapeRoomManager escapeRoomManager = EscapeRoomManager.getInstance();
        SubscribersManager subscribersManager = new SubscribersManager(escapeRoomManager);
        RunApp.run();
    }
}