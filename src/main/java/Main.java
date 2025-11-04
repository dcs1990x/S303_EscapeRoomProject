import model.EscapeRoomManager;
import model.SubscribersManager;


public class Main {

    public static void main(String[] args) {

        SubscribersManager subscribersManager = new SubscribersManager(EscapeRoomManager.getInstance());

        RunApp runApp = new RunApp();
        runApp.run();


    }
}