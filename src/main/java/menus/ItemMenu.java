package menus;

import model.Clue;
import model.Item;
import model.Theme;
import servicelayer.ClueService;
import servicelayer.ItemService;
import menus.MenuLecturaLois;
import model.UserInput;

public class ItemMenu {
    ClueService clueService = new ClueService();
    ItemService itemService = new ItemService();
    MenuLecturaLois lectura = new MenuLecturaLois();


    public ItemMenu(){
    }

    public void optionSelector(){
        int opcion = -1;

        do {
            System.out.println("1. Create Item");
            System.out.println("2. Read Items");
            System.out.println("3. Update Item");
            System.out.println("4. Delete Item");
            System.out.println("0. Exit");
            opcion = UserInput.readInt("Seleccione una opción: ");

            switch(opcion) {
                case 1:
                    createItem();
                    break;
                case 2:
                    readItems();
                    break;
                case 3:
                    modifyItem();
                    break;
                case 4:
                    deleteItem();
                    break;
                case 0:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 0 al 4.");
            }
        } while(opcion != 0);
    }

    private void createItem() {
        Item item = new Item(UserInput.readLine("Ingrese nombre: "), UserInput.readLine("Ingrese descripcion: "),
                Theme.SCI_FI, UserInput.readInt("Ingrese precio: "), true);
        lectura.readRooms();
        itemService.addItem(item, UserInput.readInt("Ingrese id de la habitación donde ingresará la pista"));
    }

    private void modifyItem() {
        lectura.readRooms();
        int id = UserInput.readInt("Seleccione la id del item a modificar: ");
        Item item = new Item(UserInput.readLine("Ingrese el nuevo nombre"), UserInput.readLine("Ingrese la nueva descripción"),
                Theme.SCI_FI, UserInput.readInt("Ingrese el nuevo precio"), true);
        itemService.updateItem(item,id);
    }

    public void readItems() {
        try {
            itemService.getItems();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteItem(){
        readItems();
        try {
            itemService.deleteItemById(UserInput.readInt("Selecciona la id del item a modificar: "));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
