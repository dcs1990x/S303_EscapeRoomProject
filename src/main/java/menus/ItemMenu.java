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
            opcion = UserInput.readInt("Choose an option: ");

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
                    System.out.println("Exiting the menu...");
                    break;
                default:
                    System.out.println("Non valid option. Please type a number between 0 and 4");
            }
        } while(opcion != 0);
    }

    private void createItem() {
        Item item = new Item(UserInput.readLine("Type the name: "), UserInput.readLine("Type the description: "),
                Theme.SPACE, UserInput.readInt("Type the price: "), true);
        lectura.readRooms();
        itemService.addItem(item, UserInput.readInt("Type the id of the room you want to include the item: "));
    }

    private void modifyItem() {
        lectura.readRooms();
        int id = UserInput.readInt("Type the id of the item you want to modify: ");
        Item item = new Item(UserInput.readLine("Type the new name: "), UserInput.readLine("Type the new description: "),
                Theme.SPACE, UserInput.readInt("Type the new price: "), true);
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
            itemService.deleteItemById(UserInput.readInt("Type the id of the item you want to delete: "));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
