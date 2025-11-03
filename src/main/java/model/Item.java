package model;

public class Item {

    private int idItem;
    private String name, description;
    private Theme theme;
    private double price;
    private boolean isImportant;

    public Item(){}

    public Item(String name, String description, Theme theme, double price, boolean isImportant){
        this.name = name;
        this.description = description;
        this.theme = theme;
        this.price = price;
        this.isImportant = isImportant;
    }

    public String getName(){return this.name;}
    public String getDescription(){return this.description;}
    public Theme getTheme(){return this.theme;}
    public double getPrice(){return this.price;}
    public boolean getIsImportant(){return this.isImportant;}

    public void setName(String name){this.name = name;}
    public void setDescription(String description){this.description = description;}
    public void setTheme(Theme theme){this.theme = theme;}
    public void setPrice(double price){this.price = price;}
    public void setIdItem(int id){this.idItem = id;}


    public boolean isEmpty() {
        if (this.name == null || this.description == null || this.theme == null){
            return true;
        }
        return false;
    }

    public long getIdItem() {
        return 0;
    }

    @Override
    public String toString() {
        return "Item{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", theme=" + theme +
                ", price=" + price +
                ", isImportant=" + isImportant +
                '}';
    }
}
