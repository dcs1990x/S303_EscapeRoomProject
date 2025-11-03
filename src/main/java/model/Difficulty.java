package model;

import java.util.Arrays;
import java.util.Optional;

public enum Difficulty {

    VERY_EASY("Very Easy"), EASY("Easy"), NORMAL("Normal"), HARD("Hard"), VERY_HARD("Very Hard");

    private String description;

    Difficulty(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public static Optional<Difficulty> fromString(String input) {
        return Arrays.stream(values())
                .filter(t -> t.name().equalsIgnoreCase(input.trim()))
                .findFirst();
    }

    public static void getDifficultiesList(){
        for (Difficulty difficulty : Difficulty.values()){
            System.out.println(difficulty.getDescription());
        }
    }
}