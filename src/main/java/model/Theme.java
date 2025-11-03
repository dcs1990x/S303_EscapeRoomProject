package model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum Theme {

    HORROR("Horror"), SPACE("Space"), VICTORIAN("Victorian"), FANTASY("Fantasy"),SCI_FI("Fiction");

    private String description;

    Theme(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public static Optional<Theme> fromString(String input) {
        return Arrays.stream(values())
                .filter(t -> t.name().equalsIgnoreCase(input.trim()))
                .findFirst();
    }

    public static void getThemesList(){
        for (Theme theme : Theme.values()){
            System.out.println(theme.getDescription());
        }
    }
}