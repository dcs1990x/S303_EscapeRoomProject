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
    ItemService itemService = new ItemService();
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

        Clue clue = new Clue("Pista_1", "Pista sobre alienígena", Theme.SPACE, 5, true, false);
        //Falta añadir la selección de sala por parte del user y la selección de los elementos.
        clueService.addClue(clue);
        //pasar al DAO
    }

    private void modifyClue() {
        int id = 4;
        //Le pedimos al usuario que introduzaca el id de la pista a actualizar
        Clue clue = new Clue("Pista_nueva", "Pista nueva para test", Theme.SPACE, 6543, true, false);
        clueService.updateClue(clue, id);
    }

    public void readClue() {
        try {
            clueService.getClues();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //Conexión a service para leer todas las pistas.
    }

    private void deleteClue()  {
        //Mostramos todas las clues
        lectura.readClue();

        int id = 1; //aquí iría el scanner pidiendo que se elija la clave que quiere borrar.
        try {
            clueService.deleteClueById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
