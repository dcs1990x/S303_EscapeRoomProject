package daomodel;

import database.DatabaseManagerTest;
import model.Clue;
import model.Theme;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoClue implements DaoInterface<Clue>{
    Connection connectionDB;

    public DaoClue(){
        System.out.println("Inicializando DaoClue...");
        try {
            this.connectionDB = DatabaseManagerTest.getConnection();
            if (this.connectionDB != null) {
                System.out.println("✅ Conexión establecida en DaoClue");
            } else {
                System.err.println("❌ La conexión es NULL en DaoClue");
            }
        } catch (Exception e) {
            System.err.println("❌ Error al obtener conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public boolean duplicate(Clue clue){
        String sql = "SELECT * FROM clues WHERE id= ? , name = ?, description = ?, theme =?, difficulty-points = ?, is-important = ?, is-solved = ?";
        Clue clueObtained = new Clue();
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1, clue.getIdClue());
            pstmt.setString(2,clue.getName());
            pstmt.setString(3,clue.getDescription());
            pstmt.setString(4,clue.getTheme().getDescription());
            pstmt.setInt(5,clue.getDifficultyPoints());
            pstmt.setBoolean(6, clue.getIsImportant());
            pstmt.setBoolean(7, clue.getIsSolved());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                clueObtained = new Clue(rs.getString("name"),
                        rs.getString("description"),
                        Theme.valueOf(rs.getString("theme").toLowerCase()),
                        rs.getInt("difficulty-points"),
                        rs.getBoolean("is-important"),
                        rs.getBoolean("is-solved"));
            }

        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
        return clueObtained.equals(clue);
    }

    @Override
    public void insertEntity(Clue entity) throws Exception {
        // Verificar conexión
        if (connectionDB == null) {
            throw new SQLException("❌ Connection is null in insertEntity");
        }

        // CORRECCIÓN: Usar los nombres correctos de columnas
        String sql_Insert2 = "INSERT INTO \"clue\" (name, description, theme, difficultyPoints, isImportant, isSolved) VALUES(?,?,?,?,?,?);";

        try (PreparedStatement sqlToInsert = connectionDB.prepareStatement(sql_Insert2, Statement.RETURN_GENERATED_KEYS)) {
            sqlToInsert.setString(1, entity.getName());
            sqlToInsert.setString(2, entity.getDescription());
            sqlToInsert.setString(3, entity.getTheme().getDescription());
            sqlToInsert.setInt(4, entity.getDifficultyPoints());
            sqlToInsert.setBoolean(5, entity.getIsImportant());
            sqlToInsert.setBoolean(6, entity.getIsSolved());

            int affectedRows = sqlToInsert.executeUpdate();
            System.out.println("✅ Filas insertadas: " + affectedRows);

            if (affectedRows == 0) {
                throw new SQLException("❌ Inserting clue failed, no rows affected.");
            }

            try (ResultSet rs = sqlToInsert.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setIdClue(rs.getInt(1));
                    System.out.println("✅ ID generado: " + entity.getIdClue());
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
    public Clue readEntity(long entityId) throws Exception {
        String sql = "SELECT * FROM clues WHERE id = ?";
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1, entityId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Clue(rs.getString("name"),
                        rs.getString("description"),
                        Theme.valueOf(rs.getString("theme").toLowerCase()),
                        rs.getInt("difficulty-points"),
                        rs.getBoolean("is-important"),
                        rs.getBoolean("is-solved"));
            }
        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
        return null;
    }

    @Override
    public void updateEntity(long entityId, Clue entity) throws Exception {
        String sql = "UPDATE clues SET name = ?, description = ?, theme = ?, difficulty-points = ?, is-important = ?, is-solved = ? WHERE id = ?";
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setString(1, entity.getName());
            pstmt.setString(2, entity.getDescription());
            pstmt.setString(3, entity.getTheme().getDescription());
            pstmt.setInt(4, entity.getDifficultyPoints());
            pstmt.setBoolean(5, entity.getIsImportant());
            pstmt.setBoolean(6, entity.getIsSolved());
            pstmt.setLong(7, entityId);
            pstmt.executeUpdate();

        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
    }

    @Override
    public void deleteEntity(long entityId) throws Exception {
        String sql = "DELETE FROM \"clue\" WHERE id = ?";
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1, entityId);
            pstmt.executeUpdate();
            System.out.println("The deletion was completed successfully. \n");

        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
    }

    @Override
    public List<Clue> readAllEntities() throws Exception {
        String sql = "SELECT * FROM \"clue\"";
        List<Clue> clues = new ArrayList<>();

        int id = 0;
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                id = rs.getInt("ID");
                Clue clue = new Clue(
                        rs.getString("NAME"),
                        rs.getString("DESCRIPTION"),
                        Theme.valueOf(rs.getString("THEME").toUpperCase()),
                        rs.getInt("DIFFICULTYPOINTS"),
                        rs.getBoolean("ISIMPORTANT"),
                        rs.getBoolean("ISSOLVED")
                );
                clue.setIdClue(id);
                clues.add(clue);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Clue clue : clues) {
            System.out.println(clue);
        }

        return clues;
    }
}