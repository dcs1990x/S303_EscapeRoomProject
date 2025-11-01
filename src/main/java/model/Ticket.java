package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ticket {
    private long idTicket;
    private long idPlayer;
    private String playerName;
    private String ticketTitle;
    private Timestamp dateSold;
    private double pricePaid;

    public Ticket(){}
    public Ticket(long idPlayer,String name,String title,double pricePaid,Timestamp dateSold){
        this.idPlayer = idPlayer;
        this.playerName = name;
        this.ticketTitle = title;
        this.pricePaid = pricePaid;
        this.dateSold = dateSold;
    }
    public Ticket(long idTicket, long idPlayer, String name, String title, double pricePaid, Timestamp dateSold){
        this.idTicket = idTicket;
        this.idPlayer = idPlayer;
        this.playerName = name;
        this.pricePaid = pricePaid;
        this.ticketTitle = title;
        this.dateSold = dateSold;
    }

    public long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(long idTicket) {
        this.idTicket = idTicket;
    }

    public long getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(long idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getTicketTitle() {
        return ticketTitle;
    }

    public void setTicketTitle(String ticketTitle) {
        this.ticketTitle = ticketTitle;
    }

    public Timestamp getDateSold() {
        return dateSold;
    }

    public void setDateSold(Timestamp dateSold) {
        this.dateSold = dateSold;
    }

    public double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }
    public boolean isEmpty(){
        return idTicket == 0 && idPlayer == 0 && ticketTitle == null && playerName == null && pricePaid ==0 && dateSold == null;
    }

}