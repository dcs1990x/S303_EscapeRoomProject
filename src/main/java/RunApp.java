
import menus.CreateManageDeleteERMenu;
import model.RoomBuilder;

import menu.MenuCreacionLois;
import menu.MenuEliminación;


public class RunApp {

    private CreateManageDeleteERMenu createManageDeleteERMenu = new CreateManageDeleteERMenu();

    public void run(){
        RoomBuilder roomBuilder = new RoomBuilder();
        createManageDeleteERMenu.setRoomBuilder(roomBuilder);

        createManageDeleteERMenu.showWelcomeMessage();
        createManageDeleteERMenu.showMainMenu();
        createManageDeleteERMenu.executeMainMenuOption();
    }
}