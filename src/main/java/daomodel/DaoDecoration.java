package daomodel;

import database.DatabaseManagerTest;
import model.Clue;
import model.Decoration;
import model.Theme;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoDecoration implements DaoInterface<Decoration> {
    Connection connectionDB;

    public DaoDecoration(){
        System.out.println("Inicializando DaoDecoration..");
        try {
            this.connectionDB = DatabaseManagerTest.getConnection();
            if (this.connectionDB != null) {
                System.out.println("✅ Conexión establecida en DaoDecoration");
            } else {
                System.err.println("❌ La conexión es NULL en DaoDecoration");
            }
        } catch (Exception e) {
            System.err.println("❌ Error al obtener conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public boolean duplicate(Decoration decoration){
        String sql = "SELECT * FROM decoration WHERE name = ?, theme =?, price = ?, id_room = ?";
        Decoration decorationObtained = new Decoration();
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setString(1,decoration.getName());
            pstmt.setString(2,decoration.getTheme().getDescription());
            pstmt.setDouble(3,decoration.getPrice());
            pstmt.setInt(4,decoration.getIdRoom());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                decorationObtained = new Decoration(rs.getString("name"),
                        rs.getDouble("price"),
                        Theme.valueOf(rs.getString("theme").toLowerCase()),
                        rs.getInt("id-room"));                        ;
            }

        }catch(SQLException sqlExcep3){
            return false;}
        return decorationObtained.equals(decoration);
    }

    @Override
    public void insertEntity(Decoration decoration, int id_room) throws Exception {
        // Verificar conexión
        if (connectionDB == null) {
            throw new SQLException("❌ Connection is null in insertEntity");
        }

        // CORRECCIÓN: Usar los nombres correctos de columnas
        String sql_Insert2 = "INSERT INTO \"decoration\" ( name,theme,price,id_room) VALUES(?,?,?,?);";

        try (PreparedStatement pstmt= connectionDB.prepareStatement(sql_Insert2, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1,decoration.getName());
            pstmt.setString(2,decoration.getTheme().getDescription());
            pstmt.setDouble(3,decoration.getPrice());
            pstmt.setInt(4,decoration.getIdRoom());

            int affectedRows = pstmt.executeUpdate();
            System.out.println("✅ Filas insertadas: " + affectedRows);

            if (affectedRows == 0) {
                throw new SQLException("❌ Inserting decoration failed, no rows affected.");
            }

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    decoration.setIdDecoration(rs.getInt(1));
                    System.out.println("✅ ID generado: " + decoration.getIdDecoration());
                } else {
                    throw new SQLException("❌ Inserting clue failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            // CORRECCIÓN: Mostrar el error completo
            System.err.println("❌ Error en insertEntity: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-lanzar para que el servicio lo capture
        }
    }

    @Override
    public Decoration readEntity(long entityId){
        String sql = "SELECT * FROM decoration WHERE id = ?";
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1, entityId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Decoration(rs.getString("name"),
                        rs.getDouble("price"),
                        Theme.valueOf(rs.getString("theme").toLowerCase()),
                        rs.getInt("id-room"));
            }
        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
        return null;
    }

    @Override
    public void updateEntity(long entityId, Decoration decoration) throws Exception {
        String sql = "UPDATE \"clue\" SET name = ?, price = ?, theme = ?, id-room = ? WHERE id = ?";
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setString(1,decoration.getName());
            pstmt.setString(2,decoration.getTheme().getDescription());
            pstmt.setDouble(3,decoration.getPrice());
            pstmt.setInt(4,decoration.getIdRoom());
            pstmt.setLong(5, entityId);
            int rows = pstmt.executeUpdate();
            System.out.println("Filas actualizadas: " + rows);

        }catch (SQLException sqlExcep3) {
            sqlExcep3.printStackTrace();
            System.err.println("❌ Error en updateEntity: " + sqlExcep3.getMessage());
        }
    }

    @Override
    public void deleteEntity(long entityId) {
        String sql = "DELETE FROM \"clue\" WHERE id = ?";
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1, entityId);
            pstmt.executeUpdate();
            System.out.println("The deletion was completed successfully. \n");

        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
    }

    @Override
    public List<Decoration> readAllEntities() throws Exception {
        String sql = "SELECT * FROM \"decoration\"";
        List<Decoration> decorations = new ArrayList<>();

        int id;
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                id = rs.getInt("ID");
                Decoration decoration = new Decoration(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        Theme.valueOf(rs.getString("theme").toLowerCase()),
                        rs.getInt("id-room")
                );
                decoration.setIdDecoration(id);
                decorations.add(decoration);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return decorations;
    }
}
