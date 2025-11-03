package dtomodel;

public record DecorationDTO (String name,String theme,double price,int idRoom){
    public String getName() {
        return name;
    }
}
