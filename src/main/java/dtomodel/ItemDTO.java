package dtomodel;

import model.Theme;

public record ItemDTO(String name, String description, double price){
    @Override
    public String toString() {
        return "ItemDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
