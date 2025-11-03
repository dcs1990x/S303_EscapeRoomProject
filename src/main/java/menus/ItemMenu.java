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
        Item item = new Item("Item_1", "A key", Theme.SPACE, 23, true);
        //Falta añadir la selección de sala por parte del user.
        itemService.addItem(item);


    }

    private void modifyItem() {
        lectura.readRooms();// vemos que habitaciones tenemos para saber que queremos modificar

        int id = 4;
        Item item = new Item("los", "sdfsdf", Theme.SPACE, 123, true);
        itemService.updateItem(item,id);
    }

    public void readItems() {
        //Conexión a service para leer todos los Items
        try {
            itemService.getItems();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteItem(){
        //Mostramos todas los
        lectura.readItems();

        int id = 1; //aquí iría el scanner pidiendo que se elija la clave que quiere borrar.
        try {
            itemService.deleteItemById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
