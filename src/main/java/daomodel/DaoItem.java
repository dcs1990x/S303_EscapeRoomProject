package daomodel;

import model.Item;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoItem implements DaoInterface<Item>{
    Connection connectionDB;

    public void DaoItem(){
       try{
           this.connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/nombre_base_de_datos", "Pol_DB", "Aleluia");
       } catch(SQLException e1){e1.getMessage();}}

    @Override
    public void insertEntity(Item entity) throws Exception {
        //Forma 1
        try {
            String sql_Insert = "INSERT INTO items (name, description, theme, price, is-important) VALUES(" + entity.getName() + ", " + entity.getDescription() + ", " + entity.getTheme() + ", " + entity.getPrice() + ", " + entity.getIsImportant() + ");";
            PreparedStatement sqlToInsert = connectionDB.prepareStatement(sql_Insert);
            sqlToInsert.executeUpdate();
        } catch (SQLDataException sqlExcep1) {
            sqlExcep1.getMessage();
        }
        // Forma 2
        try {
            String sql_Insert2 = "INSERT INTO items (name, description, theme, price, is-important) VALUES(?,?,?,?,?);";
            PreparedStatement sqlToInsert = connectionDB.prepareStatement(sql_Insert2);
            sqlToInsert.setString(1, entity.getName());
            sqlToInsert.setString(2, entity.getDescription());
            sqlToInsert.setString(3, entity.getTheme());
            sqlToInsert.setDouble(4, entity.getPrice());
            sqlToInsert.setBoolean(5, entity.getIsImportant());
            sqlToInsert.executeUpdate();
        } catch (SQLException sqlExcep2) {
            sqlExcep2.getMessage();
        }
    }

    @Override
    public Item readEntity(long entityId) throws Exception {
        String sql = "SELECT * FROM items WHERE id = ?";
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1, entityId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Item(rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("theme"),
                        rs.getDouble("price"),
                        rs.getBoolean("is-important"));
            }
        } catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
        return null;
    }

    @Override
    public void updateEntity(long entityId, Item entity) throws Exception {
        String sql = "UPDATE items SET name = ?, description = ?, theme = ?, price = ?, is-important = ? WHERE id = ?";
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setString(1, entity.getName());
            pstmt.setString(2, entity.getDescription());
            pstmt.setString(3, entity.getTheme());
            pstmt.setDouble(4, entity.getPrice());
            pstmt.setBoolean(5, entity.getIsImportant());
            pstmt.setLong(6, entityId);
            pstmt.executeUpdate();
        } catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
    }

    @Override
    public void deleteEntity(long entityId) throws Exception {
        String sql = "DELETE FROM items WHERE id = ?";
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1, entityId);
            pstmt.executeUpdate();
            System.out.println("The deletion was completed successfully. \n");
        } catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
    }

    @Override
    public List readAllEntities() throws Exception {
        String sql = "SELECT * FROM items";
        List<Item> items = new ArrayList<>();
        try (
             PreparedStatement pstmt = connectionDB.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             while (rs.next()) {
                 items.add(new Item(rs.getString("name"),
                         rs.getString("description"),
                         rs.getString("theme"),
                         rs.getDouble("price"),
                         rs.getBoolean("is-important")));
            }
        }
        return items;
    }
}
