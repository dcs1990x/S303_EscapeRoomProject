package daomodel;

import database.DatabaseManagerTest;
import model.Clue;
import model.Item;
import model.Theme;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoItem implements DaoInterface<Item>{
    Connection connectionDB;

    public void DaoItem(){
        try{
            this.connectionDB = DatabaseManagerTest.getConnection();
        } catch(SQLException e1){e1.getMessage();}}

    public boolean duplicate(Item item){
        String sql = "SELECT * FROM items WHERE id= ? , name = ?, description = ?, theme =?, price = ?, is-important = ?";
        Item itemObtained = new Item();
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1, item.getIdItem());
            pstmt.setString(2,item.getName());
            pstmt.setString(3,item.getDescription());
            pstmt.setString(4,item.getTheme().getDescription());
            pstmt.setDouble(5,item.getPrice());
            pstmt.setBoolean(6, item.getIsImportant());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                itemObtained = new Item(rs.getInt("id"),rs.getString("name"),
                        rs.getString("description"),
                        Theme.valueOf(rs.getString("theme").toLowerCase()),
                        rs.getDouble("price"),
                        rs.getBoolean("is-important"));
            }

        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
        return itemObtained.equals(item);
    }

    @Override
    public void insertEntity(Item entity) throws Exception {
        try {
            String sql_Insert2 = "INSERT INTO items (name, description, theme, price, is-important) VALUES(?,?,?,?,?);";
            PreparedStatement sqlToInsert = connectionDB.prepareStatement(sql_Insert2, Statement.RETURN_GENERATED_KEYS);
            sqlToInsert.setString(1, entity.getName());
            sqlToInsert.setString(2, entity.getDescription());
            sqlToInsert.setString(3, entity.getTheme().getDescription());
            sqlToInsert.setDouble(4, entity.getPrice());
            sqlToInsert.setBoolean(5, entity.getIsImportant());
            sqlToInsert.executeUpdate();
            try (ResultSet rs = sqlToInsert.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setIdItem(rs.getInt(1));
                }
            }
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
                        Theme.valueOf(rs.getString("theme").toLowerCase()),
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
            pstmt.setString(3, entity.getTheme().getDescription());
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
                         Theme.valueOf(rs.getString("theme").toLowerCase()),
                         rs.getDouble("price"),
                         rs.getBoolean("is-important")));
            }
        }
        return items;
    }
}
