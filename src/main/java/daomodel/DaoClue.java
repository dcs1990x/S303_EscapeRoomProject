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

    public void DaoClue(){
        try{
            this.connectionDB = DatabaseManagerTest.getConnection();
        } catch(SQLException e1){e1.getMessage();}}

    @Override
    public void insertEntity(Clue entity) throws Exception {
        try {
            String sql_Insert2 = "INSERT INTO clues (name, description, theme, difficultyPoints, isImportant, isSolved) VALUES(?,?,?,?,?,?);";
            PreparedStatement sqlToInsert = connectionDB.prepareStatement(sql_Insert2,Statement.RETURN_GENERATED_KEYS);
            sqlToInsert.setString(1, entity.getName());
            sqlToInsert.setString(2, entity.getDescription());
            sqlToInsert.setString(3, entity.getTheme().getDescription());
            sqlToInsert.setInt(4, entity.getDifficultyPoints());
            sqlToInsert.setBoolean(5, entity.getIsImportant());
            sqlToInsert.setBoolean(6, entity.getIsSolved());
            sqlToInsert.executeUpdate();
            try (ResultSet rs = sqlToInsert.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setIdClue(rs.getInt(1));
                }
            }
        } catch (SQLException sqlExcep2) {
            sqlExcep2.getMessage();
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
        String sql = "DELETE FROM clues WHERE id = ?";
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1, entityId);
            pstmt.executeUpdate();
            System.out.println("The deletion was completed successfully. \n");

        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
    }

    @Override
    public List<Clue> readAllEntities() throws Exception {
        String sql = "SELECT * FROM clues";
        List<Clue> clues = new ArrayList<>();
        try(PreparedStatement pstmt = connectionDB.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                clues.add(new Clue(rs.getString("name"),
                        rs.getString("description"),
                        Theme.valueOf(rs.getString("theme").toLowerCase()),
                        rs.getInt("difficulty-points"),
                        rs.getBoolean("is-important"),
                        rs.getBoolean("is-solved")));
            }
        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
        return clues;
    }
}