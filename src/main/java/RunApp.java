import menus.CreateManageDeleteERMenu;

public class RunApp {

    private CreateManageDeleteERMenu createManageDeleteERMenu = new CreateManageDeleteERMenu();

    public void run(){

        createManageDeleteERMenu.showWelcomeMessage();
        createManageDeleteERMenu.showMainMenu();
        createManageDeleteERMenu.executeMainMenuOption();
    }
}