package servicelayer;

import daomodel.DaoItem;
import dtomodel.ItemDTO;
import model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemService {
    private final DaoItem daoItem = new DaoItem();

    public ItemService(){}

    public boolean searchDuplicates(Item item){
        return daoItem.duplicate(item);
    }

    public void addClue(Item item){
        try{
            if(!item.isEmpty()||!searchDuplicates(item)){
                daoItem.insertEntity(item);}
            else{System.out.println("The item was empty, therefore it could not be inserted to DB.");}
        }
        catch(Exception sqle1){
            System.err.println(sqle1.getMessage());
            System.out.println("The item was not added correctly, please try again with the correct format");
        }

    }
    public void updateClue(Item item){
        try{
            if(!item.isEmpty()||searchDuplicates(item)){
                daoItem.updateEntity(item.getIdItem(),item);}
            else{System.out.println("The item was empty, therefore it could not be inserted to DB.");}
        }
        catch(Exception sqle1){
            System.err.println(sqle1.getMessage());
            System.out.println("The item was not added correctly, please try again with the correct format");
        }
    }
    public ItemDTO readItemById(long id){
        Item readItem = null;
        try{
            daoItem.readEntity(id);
        }
        catch(Exception sqle1){
            System.err.println(sqle1.getMessage());
            System.out.println("The item was not added correctly, please try again with the correct format");
        }
        return new ItemDTO(readItem.getName(),readItem.getDescription(),readItem.getPrice());
    }

    public List<ItemDTO> getItems() throws Exception {
        final List<Item> items = daoItem.readAllEntities();
        List<ItemDTO> itemsDTO = new ArrayList<>();
        for(Item item:items){
            itemsDTO.add(new ItemDTO(item.getName(),item.getDescription(),item.getPrice()));
        }
        return itemsDTO;
    }
    public ItemDTO getItem(Long id) throws Exception {
        final Item item = daoItem.readEntity(id);

        return new ItemDTO(item.getName(),item.getDescription(),item.getPrice());
    }
}
