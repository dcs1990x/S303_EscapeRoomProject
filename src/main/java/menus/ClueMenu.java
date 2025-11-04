package menus;

import model.Clue;
import model.Item;
import model.Theme;
import servicelayer.ClueService;
import servicelayer.ItemService;
import menus.MenuLecturaLois;
import model.UserInput;

public class ClueMenu {
    ClueService clueService = new ClueService();
    MenuLecturaLois lectura = new MenuLecturaLois();


    public ClueMenu(){
    }

    public void optionSelector(){
        int opcion = -1;

        do {
            System.out.println("1. Create Clue");
            System.out.println("2. Read Clue");
            System.out.println("3. Update Clue");
            System.out.println("4. Delete Clue");
            System.out.println("0. Exit");
            opcion = UserInput.readInt("Choose a option: ");

            switch(opcion) {
                case 1:
                    createClue();
                    break;
                case 2:
                    readClue();
                    break;
                case 3:
                    modifyClue();
                    break;
                case 4:
                    deleteClue();
                    break;
                case 0:
                    System.out.println("Exiting the menu...");
                    break;
                default:
                    System.out.println("Non velid option. Please choose an option between 0 and 4");
            }
        } while(opcion != 0);
    }

    private void createClue() {

        Clue clue = new Clue(UserInput.readLine("Type name: "), UserInput.readLine("Type description: "),
                Theme.SPACE, UserInput.readInt("Type dificulty points: "), true, false);
        lectura.readRooms();
        clueService.addClue(clue, UserInput.readInt("Type the id of the room you would like to put your clue: "));
    }

    private void modifyClue() {
        lectura.readRooms();//Llamar desde el service de DaoRoom
        int id = UserInput.readInt("Type the id of the clue you want to modify: ");
        Clue clue = new Clue(UserInput.readLine("Tyoe the new name"), UserInput.readLine("Type the new description: "),
                Theme.SPACE, UserInput.readInt("Type the new difficulty: ") , true, false);
        clueService.updateClue(clue, id);
    }

    public void readClue() {
        try {
            clueService.getClues();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteClue()  {
        readClue();
        try {
            clueService.deleteClueById(UserInput.readInt("Type the id of the clue you want to delete: "));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
