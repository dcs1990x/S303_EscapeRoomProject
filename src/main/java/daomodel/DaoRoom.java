package daomodel;

import database.DatabaseManagerTest;
import model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoRoom implements DaoInterface {
    Connection connectionDB;

    public DaoRoom() {
        this.connectionDB = DatabaseManagerTest.getConnection();
    }

    public void insertRoom(Object entity) throws Exception {

    }


    @Override
    public void insertEntity(Object entity, int id) throws Exception {

    }

    @Override
    public Object readEntity(long entityId) throws Exception {
        return null;
    }

    @Override
    public void updateEntity(long entityId, Object entity) throws Exception {

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
                        "r.ID_ROOM AS ID_ROOM, " +
                        "r.NAME AS ROOM_NAME, " +
                        "r.DIFFICULTY, " +
                        "r.PRICE, " +
                        "item.ID_ITEM AS ID_ITEM, " +        // ← AÑADIR ESTO
                        "item.NAME AS ITEM_NAME, " +
                        "clue.ID AS CLUE_ID, " +        // ← AÑADIR ESTO
                        "clue.NAME AS CLUE_NAME " +
                        "FROM \"room\" r " +
                        "LEFT JOIN \"item\" item ON r.ID_ROOM = item.ID_ROOM " +
                        "LEFT JOIN \"clue\" clue ON r.ID_ROOM = clue.ID_ROOM " +
                        "ORDER BY r.NAME";

        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int roomId = rs.getInt("ID_ROOM");
                System.out.println("[Room ID]: " + roomId);

                String roomName = rs.getString("ROOM_NAME");
                System.out.println("[Room Name]: " + roomName);

                String difficulty = rs.getString("DIFFICULTY");
                System.out.println("[Difficulty]: " + difficulty);

                double price = rs.getDouble("PRICE");
                System.out.println("[Price]: $" + price);

                int itemId = rs.getInt("ID_ITEM");
                System.out.println("[Item ID]: " + itemId);

                String itemName = rs.getString("ITEM_NAME");
                System.out.println("[Item Name]: " + itemName);

                int clueId = rs.getInt("CLUE_ID");
                System.out.println("[Clue ID]: " + clueId);

                String clueName = rs.getString("CLUE_NAME");
                System.out.println("[Clue Name]: " + clueName);

                System.out.println("-----------------------------------"); // Separador entre registros
            }

        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return List.of();
    }
}
