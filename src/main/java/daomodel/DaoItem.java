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

    public DaoItem(){
            this.connectionDB = DatabaseManagerTest.getConnection();
}

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
                itemObtained = new Item(rs.getString("name"),
                        rs.getString("description"),
                        Theme.valueOf(rs.getString("theme").toLowerCase()),
                        rs.getDouble("price"),
                        rs.getBoolean("is-important"));
            }

        }catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
        return itemObtained.equals(item);
    }

    @Override
    public void insertEntity(Item entity, int id) throws Exception {
        // Verificar conexión
        if (connectionDB == null) {
            throw new SQLException("❌ Connection is null in insertEntity");
        }
        try {

            String sql_Insert2 = "INSERT INTO \"item\" (id_room, name, description, theme, price, isimportant) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement sqlToInsert = connectionDB.prepareStatement(sql_Insert2, Statement.RETURN_GENERATED_KEYS);
            sqlToInsert.setInt(1, id);
            sqlToInsert.setString(2, entity.getName());
            sqlToInsert.setString(3, entity.getDescription());
            sqlToInsert.setString(4, entity.getTheme().getDescription());
            sqlToInsert.setDouble(5, entity.getPrice());
            sqlToInsert.setBoolean(6, entity.getIsImportant());
            sqlToInsert.executeUpdate();
            try (ResultSet rs = sqlToInsert.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setIdItem(rs.getInt(1));//Comprobar que la id_item se añade correctamente
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  // muestra la excepción completa
            System.err.println("❌ Error al insertar item: " + e.getMessage());
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
                        Theme.fromDescription(rs.getString("theme").toLowerCase()),
                        rs.getDouble("price"),
                        rs.getBoolean("is-important"));
            }
        } catch(SQLException sqlExcep3){sqlExcep3.getMessage();}
        return null;
    }

    @Override
    public void updateEntity(long entityId, Item entity) throws Exception {
        String sql = "UPDATE \"item\" SET name = ?, description = ?, theme = ?, price = ?, isimportant = ? WHERE id_item = ?";
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
        String sql = "DELETE FROM \"item\" WHERE id_item = ?";
        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
            pstmt.setLong(1, entityId);
            pstmt.executeUpdate();
            System.out.println("The deletion was completed successfully. \n");
        } catch(SQLException sqlExcep3){
            System.out.println(sqlExcep3.getMessage());}
    }

    @Override
    public List readAllEntities() throws Exception {
        String sql = "SELECT * FROM \"item\"";
        List<Item> items = new ArrayList<>();
        List<Integer> id_item = new ArrayList<>();
        try (
                PreparedStatement pstmt = connectionDB.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                 id_item.add(rs.getInt("id_item"));
                items.add(new Item(rs.getString("name"),
                        rs.getString("description"),
                        Theme.valueOf(rs.getString("theme").toUpperCase()),
                        rs.getDouble("price"),
                        rs.getBoolean("isimportant")));
            }
            //Añadir función externa para mostrar datos.
            for (int i = 0; i < items.size(); i++) {
                System.out.println("┌─────────────────────────────────────────────────────────");
                System.out.println("│ ID: " + id_item.get(i));
                System.out.println("│ " + items.get(i));
                System.out.println("└─────────────────────────────────────────────────────────");
            }
        }
        return items;
    }
}