import database.DatabaseManagerTest;
import model.Menu;
import model.MenuCreacionLois;
import model.MenuEliminación;
import model.MenuLecturaLois;

public class RunApp {

    public static void run(){

      //  DatabaseManagerTest.getConnection();
    MenuCreacionLois menuCreacionLois = new MenuCreacionLois();
    menuCreacionLois.logicaMenuCreacion();


        //MenuLecturaLois menuLecturaLois = new MenuLecturaLois();
        //menuLecturaLois.logicaMenuLectura();



        MenuEliminación menuEliminación = new MenuEliminación();
        menuEliminación.logicaMenuEliminacion();






        /*Menu.showInitialScreen();
        Menu.showMainMenu();
        Menu.executeMenuOption();
         */
    }

}