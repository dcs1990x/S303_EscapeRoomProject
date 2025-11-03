package daomodel;

import database.DatabaseManagerTest;
import model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoRoom implements DaoInterface<Room> {
    Connection connectionDB;

    public DaoRoom() {
        this.connectionDB = DatabaseManagerTest.getConnection();
    }




    @Override
    public void insertEntity(Room entity, int id) throws Exception {
        //El Id será el ESCAPEROOM en que quiere meter el room.

        // Verificar conexión
        if (connectionDB == null) {
            throw new SQLException("❌ Connection is null in insertEntity");
        }
        try {

            String sql_Insert2 = "INSERT INTO \"room\" (id_escape_room, name, difficulty, price) VALUES (?, ?, ?, ?)";
            PreparedStatement sqlToInsert = connectionDB.prepareStatement(sql_Insert2, Statement.RETURN_GENERATED_KEYS);
            sqlToInsert.setInt(1, id);
            sqlToInsert.setString(2, entity.getName());
            sqlToInsert.setString(3, entity.getDifficulty().getDescription());
            sqlToInsert.setDouble(4, entity.getPrice());
            sqlToInsert.executeUpdate();

            try (ResultSet rs = sqlToInsert.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setIdRoom(rs.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  // muestra la excepción completa
            System.err.println("❌ Error al insertar item: " + e.getMessage());
        }


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
