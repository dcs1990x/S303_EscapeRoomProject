package dtomodel;

public record ClueDTO(String name, String description, int difficultyPoints, boolean isSolved){

    @Override
    public String toString() {
        return "ClueDTO{\n" +
                "name='" + name + ",\n" +
                ", description='" + description + ",\n" +
                ", difficultyPoins=" + difficultyPoints +",\n"+
                "isSolved=" + isSolved +",\n"+
                '}';
    }
}
