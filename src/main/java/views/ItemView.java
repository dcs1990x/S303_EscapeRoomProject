package views;

import servicelayer.ClueService;
import servicelayer.ItemService;

public class ItemView {

    ItemService service = new ItemService();
    public ItemView(){}

    public void showItems() throws Exception{
        System.out.println("<======== Items ========>");
        System.out.println(service.getItems().toString());

    }

}
