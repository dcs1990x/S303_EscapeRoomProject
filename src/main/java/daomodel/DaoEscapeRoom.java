package daomodel;

import database.DatabaseManagerTest;
import model.EscapeRoom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoEscapeRoom implements DaoInterface<EscapeRoom> {
    Connection connectionDB;
    EscapeRoom escapeRoom;

    public DaoEscapeRoom() {
        System.out.println("Inicializando DaoEscapeRoom...");
        try {
            this.connectionDB = DatabaseManagerTest.getConnection();
            if (this.connectionDB != null) {
                System.out.println("✅ Conexión establecida en DaoEscapeRoom");
            } else {
                System.err.println("❌ La conexión es NULL en DaoEscapeRoom");
            }
        } catch (Exception e) {
            System.err.println("❌ Error al obtener conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean searchDuplicates(EscapeRoom entity) throws Exception {
        String sql = "SELECT * FROM ESCAPE_ROOM WHERE ID_ESCAPE_ROOM = ? , NAME = ?";
        EscapeRoom escapeRoomObtained = new EscapeRoom();
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1, entity.getIdEscapeRoom());
            pstmt.setString(2, entity.getEscapeRoomName());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                escapeRoomObtained = new EscapeRoom(rs.getInt("ID_ESCAPE_ROOM"), rs.getString("NAME"));
            }
        } catch (Exception e1) {
            System.out.println("The Escape Room was not found, therefore is not duplicated");
            return false;
        }
        return escapeRoomObtained.equals(entity);
    }

    public void insertEntity(EscapeRoom entity) throws Exception {
        if (connectionDB == null) {
            throw new SQLException("❌ Connection is null in insertEntity");
        }

        if (!escapeRoom.isEmpty() || searchDuplicates(entity)) {
            String sql = "INSERT INTO \"ESCAPE_ROOM\" (NAME) VALUES(?)";
            EscapeRoom escapeRoomObtained = new EscapeRoom();
            try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
                pstmt.setString(1, entity.getEscapeRoomName());
                int affectedRows = pstmt.executeUpdate();
                System.out.println("✅ Filas insertadas: " + affectedRows);

                if (affectedRows == 0) {
                    throw new SQLException("❌ Inserting clue failed, no rows affected.");
                }

                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        entity.setIdEscapeRoom(rs.getInt(1));
                        System.out.println("✅ ID generado: " + entity.getIdEscapeRoom());
                    } else {
                        throw new SQLException("❌ Inserting clue failed, no ID obtained.");
                    }
                }
            }
        }else{
            System.out.println("The EscapeRoom was empty/duplicated, therefore it could not be inserted to DB.");
        }
        }

        @Override
        public EscapeRoom readEntity ( long entityId) throws Exception {
            String sql = "SELECT * FROM ESCAPE_ROOM WHERE ID_ESCAPE_ROOM = ?";
            try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
                pstmt.setLong(1, entityId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return new EscapeRoom(rs.getInt("ID_ESCAPE_ROOM"), rs.getString("NAME"));
                }
            } catch (SQLException sqlExcep3) {
                sqlExcep3.getMessage();
            }
            return null;
        }

        @Override
        public void updateEntity ( long entityId, EscapeRoom entity) throws Exception {
            if (!escapeRoom.isEmpty() || searchDuplicates(entity)) {
                String sql = "UPDATE \"ESCAPE_ROOM\"  NAME = ? WHERE ID_ESCAPE_ROOM = ?";
                try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {

                    pstmt.setString(1, entity.getEscapeRoomName());
                    pstmt.setInt(2, entity.getIdEscapeRoom());

                    int rows = pstmt.executeUpdate();
                    System.out.println("Filas actualizadas: " + rows);

                } catch (SQLException sqlExcep3) {
                    sqlExcep3.printStackTrace();
                    System.err.println("❌ Error en updateEntity: " + sqlExcep3.getMessage());
                }
            }else{System.out.println("The EscapeRoom was empty/duplicated, therefore it could not be inserted to DB.");}
        }

        @Override
        public void deleteEntity ( long entityId) throws Exception {
            String sql = "DELETE FROM \"ESCAPE_ROOM\" WHERE ID_ESCAPE_ROOM = ?";
            try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
                pstmt.setLong(1, entityId);
                pstmt.executeUpdate();
                System.out.println("The deletion was completed successfully. \n");

            } catch (SQLException sqlExcep3) {
                sqlExcep3.getMessage();
            }

        }

        @Override
        public List<EscapeRoom> readAllEntities () throws Exception {
            String sql = "SELECT * FROM \"ESCAPE_ROOM\"";
            List<EscapeRoom> escapeRooms = new ArrayList<>();

            int id;
            try (PreparedStatement pstmt = connectionDB.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    escapeRoom = new EscapeRoom(
                            rs.getInt("ID_ESCAPE_ROOM"),
                            rs.getString("NAME")
                    );
                    escapeRooms.add(escapeRoom);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return escapeRooms;
        }
    }
