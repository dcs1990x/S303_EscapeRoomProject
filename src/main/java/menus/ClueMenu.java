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
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 0 al 4.");
            }
        } while(opcion != 0);
    }

    private void createClue() {

        Clue clue = new Clue(UserInput.readLine("Ingrese nombre"), UserInput.readLine("Ingrese descripcion"),
                Theme.SPACE, UserInput.readInt("Ingrese puntos de dificultad"), true, false);
        lectura.readRooms();
        clueService.addClue(clue, UserInput.readInt("Ingrese id de la habitación donde ingresará la pista"));
    }

    private void modifyClue() {
        lectura.readRooms();
        int id = UserInput.readInt("Seleccione la id de la clue a modificar: ");
        Clue clue = new Clue(UserInput.readLine("Ingrese el nuevo nombre"), UserInput.readLine("Ingrese la nueva descripción"),
                Theme.SPACE, UserInput.readInt("Ingrese la nueva dificultad") , true, false);
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
            clueService.deleteClueById(UserInput.readInt("Selecciona la id de la clue a modificar: "));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
