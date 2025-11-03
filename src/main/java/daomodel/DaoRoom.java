package daomodel;

import database.DatabaseManagerTest;
import model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoRoom implements DaoInterface<Room>{
    Connection connectionDB;

    public DaoRoom(){
            this.connectionDB = DatabaseManagerTest.getConnection();
    }

    @Override
    public void insertEntity(Room entity) throws Exception {

    }

    @Override
    public Room readEntity(long entityId) throws Exception {
        return null;
    }

    @Override
    public void updateEntity(long entityId, Room entity) throws Exception {

    }

    @Override
    public void deleteEntity(long entityId) throws Exception {

    }

    @Override
    public List<Room> readAllEntities() throws Exception {
        this.connectionDB = DatabaseManagerTest.getConnection();
        if (connectionDB == null) {
            throw new SQLException("❌ Connection is null");
        }

        String sql =
                "SELECT " +
                        "  r.NAME AS ROOM_NAME, " +
                        "  r.DIFFICULTY, " +
                        "  r.PRICE, " +
                        "  item.NAME AS ITEM_NAME, " +
                        "  clue.NAME AS CLUE_NAME " +
                        "FROM \"room\" r " +
                        "LEFT JOIN \"item\" item ON r.ID_ROOM = item.ID_ROOM " +
                        "LEFT JOIN \"clue\" clue ON r.ID_ROOM = clue.ID_ROOM " +
                        "ORDER BY r.NAME";

        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
            //    int roomId = rs.getInt("ID_ROOM");
                String roomName = rs.getString("ROOM_NAME");
                System.out.println(roomName);
                String difficulty = rs.getString("DIFFICULTY");
                double price = rs.getDouble("PRICE");
               // int itemName = rs.getInt("ID_ITEM");
                String itemId = rs.getString("ITEM_NAME");
               // int clueId = rs.getInt("CLUE_ID");
                String clueName = rs.getString("CLUE_NAME");
                System.out.println("[Clue name]: " + clueName);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return List.of();
    }
}