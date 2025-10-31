package dtomodel;

public record PlayerDTO (String name,boolean madeReservation,int score) {
    @Override
    public String toString() {
        return "PlayerDTO{" +
                "name='" + name + '\'' +
                ", madeReservation=" + madeReservation +
                ", score=" + score +
                '}';
    }
}
