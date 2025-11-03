package model;

import java.util.Arrays;
import java.util.Optional;

import static model.Theme.*;

public enum Decoration {

    COBWEB("Cobweb", 0.50f, Theme.HORROR), HAND_PROP("Hand prop", 2.45f, Theme.HORROR), FAKE_BLOOD("Fake blood", 1.50f, Theme.HORROR),
    SPIDER("Spider", 1.65f, Theme.HORROR), CANDLE("Candle", 1.23f, VICTORIAN), CHAIR("Chair", 25f, VICTORIAN),
    PORTRAIT("Portrait", 30f, VICTORIAN), TABLE("Table", 50.50f, VICTORIAN), EXPERIMENTATION_POTS("Experimentation pots", 5.21f, SCI_FI),
    ALIEN_PICTURES("Alien pictures", 3.23f, SCI_FI), PERIODIC_TABLE("Periodic table", 3.10f, SCI_FI), PLANET_POSTER("Planet poster", 2.50f, SCI_FI),
    CASTLE_POSTER("Castle Poster", 2.55f, FANTASY), DRAGON_PROP("Dragon prop", 8.90f, FANTASY), PLASTIC_ARMOR("Plastic Armor", 5.60f, FANTASY),
    UNICORN_PROP("Unicorn Prop", 7.56f, FANTASY);

    private String name;
    private float price;
    private Theme theme;

    Decoration(String name, float price, Theme theme){
        this.name = name;
        this.price = price;
        this.theme = theme;
    }

    public String getName(){return this.name;}
    public float getPrice(){return this.price;}
    public Theme getTheme(){return this.theme;}

    public void setName(String name){this.name = name;}
    public void setPrice(float price){this.price = price;}
    public void setTheme(Theme theme){this.theme = theme;}

    public static Optional<Decoration> fromString(String input) {
        return Arrays.stream(values())
                .filter(t -> t.name().equalsIgnoreCase(input.trim()))
                .findFirst();
    }

    public static void getDecorationsList(){
        for (Decoration decoration : Decoration.values()){
            System.out.println(decoration.getName());
        }
    }
}