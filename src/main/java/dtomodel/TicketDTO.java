package dtomodel;

public record TicketDTO (String ticketTitle, String playerName, String dateSold,double pricePaid){
    @Override
    public String toString() {
        return "TicketDTO{" +
                "ticketTitle='" + ticketTitle + '\'' +
                ", playerName='" + playerName + '\'' +
                ", dateSold='" + dateSold + '\'' +
                ", pricePaid=" + pricePaid +
                '}';
    }
}