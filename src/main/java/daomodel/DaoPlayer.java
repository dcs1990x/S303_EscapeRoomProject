package daomodel;

import database.DatabaseManagerTest;
import model.Clue;
import model.Item;
import model.Player;
import model.Theme;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DaoPlayer implements DaoInterface<Player> {
    Connection connectionDB;
    public void DaoPlayer(){

        this.connectionDB = DatabaseManagerTest.getConnection();
    }



    public boolean duplicate(Player player){
        String sql = "SELECT * FROM players WHERE id-room= ? , name = ?, made-reservation = ?, score = ? ";
        Player playerObtained = new Player();
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1, player.getIdRoom());
            pstmt.setString(2,player.getName());
            pstmt.setBoolean(3, player.hasMadeReservation());
            pstmt.setDouble(4,player.getScore());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                playerObtained = new Player(rs.getString("name"),
                        rs.getBoolean("made-reservation"),
                        rs.getInt("score"),rs.getInt("id-room"));
            }

        }catch(SQLException sqlExcep3){
            System.out.println("No entity has been found with this characteristics");
            return false;}

        return playerObtained.equals(player);
    }

    public void insertEntity(Player entity, int id) throws Exception {
        try {
            String sql_Insert2 = "INSERT INTO players (name,made-reservation,score,id-room) VALUES(?,?,?,?);";
            PreparedStatement sqlToInsert = connectionDB.prepareStatement(sql_Insert2, Statement.RETURN_GENERATED_KEYS);
            sqlToInsert.setString(1, entity.getName());
            sqlToInsert.setBoolean(2, entity.hasMadeReservation());
            sqlToInsert.setDouble(3, entity.getScore());
            sqlToInsert.setInt(3, entity.getIdRoom());
            sqlToInsert.executeUpdate();
            try (ResultSet rs = sqlToInsert.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setIdPlayer(rs.getInt(1));
                }
            }
        } catch (SQLException sqlExcep2) {
            sqlExcep2.getMessage();
        }
    }

    @Override
    public Player readEntity(long entityId) throws Exception {
        String sql = "SELECT * FROM players WHERE id = ?";
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1, entityId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Player(rs.getString("name"),
                        rs.getBoolean("made-reservation"),
                        rs.getInt("score"),rs.getInt("id-room"));
            }
        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
        return null;
    }

    @Override
    public void updateEntity(long entityId, Player entity) throws Exception {
        String sql = "UPDATE players SET name = ?, made-reservation = ?, score = ?, id-room = ? WHERE id = ?";
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setString(1, entity.getName());
            pstmt.setBoolean(2, entity.hasMadeReservation());
            pstmt.setInt(3, entity.getScore());
            pstmt.setInt(4,entity.getIdRoom());
            pstmt.setInt(4,entity.getIdPlayer());

            pstmt.executeUpdate();
        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
    }

    @Override
    public void deleteEntity(long entityId) throws Exception {
        String sql = "DELETE FROM players WHERE id = ?";
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1, entityId);
            pstmt.executeUpdate();
            System.out.println("The deletion was completed successfully. \n");
        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
    }

    @Override
    public List<Player> readAllEntities() throws Exception {
        String sql = "SELECT * FROM players";
        List<Player> players = new ArrayList<>();
        try(PreparedStatement pstmt = connectionDB.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                players.add(new Player(rs.getString("name"),
                        rs.getBoolean("made-reservation"),
                        rs.getInt("score"),rs.getInt("id-room")));
            }
        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
        return players;
    }
}