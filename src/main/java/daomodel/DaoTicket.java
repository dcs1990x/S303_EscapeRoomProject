package daomodel;

import database.DatabaseManagerTest;
import model.Player;
import model.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoTicket implements DaoInterface<Ticket> {

    Connection connectionDB;

    public void DaoTicket() {
        this.connectionDB = DatabaseManagerTest.getConnection();
    }

    public boolean duplicate(Ticket ticket){
        try{ if(ticket.getIdPlayer() == 0){
            throw new NullPointerException("The id is empty please try with an object that has id.");
        }} catch (NullPointerException e) {
            System.err.println(e);
        }
        String sql = "SELECT * FROM tickets WHERE ticket-id= ? ,player-id = ? , player-name = ?, ticket-title = ?, date-sold = ? , price-paid = ?";
        Ticket ticketObtained = new Ticket();
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1, ticket.getIdTicket());
            pstmt.setLong(2,ticket.getIdPlayer());
            pstmt.setString(3,ticket.getPlayerName());
            pstmt.setString(4,ticket.getTicketTitle());
            pstmt.setTimestamp(5,ticket.getDateSold());
            pstmt.setDouble(6,ticket.getPricePaid());


            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                ticketObtained = new Ticket(rs.getLong("id-ticket"),rs.getLong("id-player"),
                        rs.getString("player-name"),rs.getString("ticket-title"),rs.getDouble("price-paid"),rs.getTimestamp("date-sold"));
            }

        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
        return ticketObtained.equals(ticket);
    }

    public void insertEntity(Ticket ticket, int id) throws Exception {
        try {
            String sql_Insert2 = "INSERT INTO tickets (player-id,player-name,ticket-title,date-sold,price-paid) VALUES(?,?,?,?,?);";
            PreparedStatement pstmt = connectionDB.prepareStatement(sql_Insert2, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1,ticket.getIdPlayer());
            pstmt.setString(2,ticket.getPlayerName());
            pstmt.setString(3,ticket.getTicketTitle());
            pstmt.setTimestamp(4,ticket.getDateSold());
            pstmt.setDouble(5,ticket.getPricePaid());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    ticket.setIdPlayer(rs.getInt(1));
                }
            }
        } catch (SQLException sqlExcep2) {
            sqlExcep2.getMessage();
        }
    }

    @Override
    public Ticket readEntity(long entityId) throws Exception {
        String sql = "SELECT * FROM players WHERE id-ticket = ?";
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1, entityId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Ticket(rs.getLong("id-ticket"),rs.getLong("id-player"),
                        rs.getString("player-name"),rs.getString("ticket-title"),
                        rs.getDouble("price-paid"),rs.getTimestamp("date-sold"));
            }
        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
        return null;
    }


    @Override
    public void updateEntity(long entityId, Ticket entity) throws Exception {
        String sql = "UPDATE tickets SET player-id = ? , player-name = ?, ticket-title = ?, date-sold = ? , price-paid = ?WHERE ticket-id = ?";
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1,entity.getIdPlayer());
            pstmt.setString(2,entity.getPlayerName());
            pstmt.setString(3,entity.getTicketTitle());
            pstmt.setTimestamp(4,entity.getDateSold());
            pstmt.setDouble(5,entity.getPricePaid());
            pstmt.setLong(6,entity.getIdTicket());
            pstmt.executeUpdate();
        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
    }

    @Override
    public void deleteEntity(long entityId) throws Exception {
        String sql = "DELETE FROM tickets WHERE id = ?";
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1, entityId);
            pstmt.executeUpdate();
            System.out.println("The deletion was completed successfully. \n");
        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
    }

    @Override
    public List<Ticket> readAllEntities() throws Exception {
        String sql = "SELECT * FROM players";
        List<Ticket> tickets = new ArrayList<>();
        try(PreparedStatement pstmt = connectionDB.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                tickets.add(new Ticket(rs.getLong("id-ticket"),rs.getLong("id-player"),
                        rs.getString("player-name"),rs.getString("ticket-title"),
                        rs.getDouble("price-paid"),rs.getTimestamp("date-sold")));
            }
        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
        return tickets;
    }
}