import menus.CreateManageDeleteERMenu;
import menus.ManageERMenu;
import model.RoomBuilder;

public class RunApp {

    private CreateManageDeleteERMenu createManageDeleteERMenu = new CreateManageDeleteERMenu();
    private ManageERMenu manageERMenu;

    public void run(){

        createManageDeleteERMenu.showWelcomeMessage();
        createManageDeleteERMenu.showMainMenu();
        createManageDeleteERMenu.executeMainMenuOption();
    }
}