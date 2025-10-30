package model;

public enum Theme {

    HORROR("Horror"), SPACE("Space"), VICTORIAN("Victorian"), FANTASY("Fantasy");

    private String description;

    Theme(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}