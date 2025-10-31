package model;

public enum Difficulty {

    VERY_EASY("Very Easy"), EASY("Easy"), NORMAL("Normal"), HARD("Hard"), VERY_HARD("Very Hard");

    private String description;

    Difficulty(String description) {
        this.description = description;
    }
}