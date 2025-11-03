package model;

import servicelayer.ClueService;
import servicelayer.ItemService;
import servicelayer.RoomService;

import java.sql.SQLException;
import java.util.ArrayList;

public class Inventory {
    private final ClueService clueService = new ClueService();
    private final ItemService itemService = new ItemService();
    private final RoomService roomService = new RoomService();

    private ArrayList<Item> inventory;

    public void addItem(Item item){
        inventory.add(item);
    }

    public void removeItem(Item item){
        inventory.remove(item);
    }

    public void getInventory(){

    }

    public double calculateInventoryCost(){


    }
}