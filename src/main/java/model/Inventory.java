package model;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> inventory;

    public void addItem(Item item){
        inventory.add(item);
    }

    public void removeItem(Item item){
        inventory.remove(item);
    }

    public void getInventory(){
        for (var element : inventory){
            System.out.println(element);
        }
    }

    public double calculateInventoryCost(){
        double total = 0;
        for (Item item : inventory){
            total += item.getPrice();
        }
        return total;
    }
}