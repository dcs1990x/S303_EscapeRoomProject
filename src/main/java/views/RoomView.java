package views;

import menus.InventoryMenu;
import servicelayer.RoomService;

import java.util.List;


public class RoomView {
    private RoomService service = new RoomService();
    public void showRooms() throws Exception{
        System.out.println("<======== Rooms ========>");
        System.out.println(service.getRooms().toString())

    }

}
