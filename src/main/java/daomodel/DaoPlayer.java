/*package daomodel;

import model.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DaoPlayer implements DaoInterface<Player> {
    Connection connectionDB;
    public void DaoItem(){
        try{
            this.connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/nombre_base_de_datos", "Pol_DB", "Aleluia");
        } catch(SQLException e1){e1.getMessage();}}
    @Override
    public void insertEntity(Player entity) throws Exception {
        try {
            String sql_Insert2 = "INSERT INTO players (name,made-reservation,player-inventory-id) VALUES(?,?,?);";
            PreparedStatement sqlToInsert = connectionDB.prepareStatement(sql_Insert2);
            sqlToInsert.setString(1, entity.getName());
            sqlToInsert.setBoolean(2, entity.hasMadeReservation());
            sqlToInsert.setDouble(3, entity.getInventoryID());
            sqlToInsert.executeUpdate();
            //Me falta poner el inventarioID que creo que voy a llamar a otro DAO para resolver el problema y que accepte una lista
            while(){
            String sql_Insert2_bis = "INSERT INTO player-inventory(item-id) VALUE (?);";
            PreparedStatement sqlToInsert2 = connectionDB.prepareStatement(sql_Insert2_bis);
            sqlToInsert2.setInt(1, entity.getPlayerInventory().getID()));
            }
        } catch (SQLException sqlExcep2) {
            sqlExcep2.getMessage();
        }
    }

    @Override
    public Player readEntity(long entityId) throws Exception {
        return null;
    }

    @Override
    public void updateEntity(long entityId, Player entity) throws Exception {

    }

    @Override
    public void deleteEntity(long entityId) throws Exception {

    }

    @Override
    public List<Player> readAllEntities() throws Exception {
        return List.of();
    }
}*/