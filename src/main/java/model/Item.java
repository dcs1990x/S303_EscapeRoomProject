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
    public Item(int id,String name, String description, Theme theme, double price, boolean isImportant){
        this.idItem = id;
        this.name = name;
        this.description = description;
        this.theme = theme;
        this.price = price;
        this.isImportant = isImportant;
    }

    public int getIdItem(){return this.idItem;}
    public String getName(){return this.name;}
    public String getDescription(){return this.description;}
    public Theme getTheme(){return this.theme;}
    public double getPrice(){return this.price;}
    public boolean getIsImportant(){return this.isImportant;}

    public void setIdItem(int id){this.idItem = id;}
    public void setName(String name){this.name = name;}
    public void setDescription(String description){this.description = description;}
    public void setTheme(Theme theme){this.theme = theme;}
    public void setPrice(double price){this.price = price;}
    public boolean isEmpty(){
        return name == null && description == null && theme == null && price == 0 && isImportant == false;
    }
}