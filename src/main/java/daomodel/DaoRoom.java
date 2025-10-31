package daomodel;

import database.DatabaseManagerTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DaoRoom implements DaoInterface{
    Connection connectionDB;

    public void DaoRoom(){
        try{
            this.connectionDB = DatabaseManagerTest.getConnection();
        } catch(SQLException e1){e1.getMessage();}
    }
    @Override
    public void insertEntity(Object entity) throws Exception {

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
    public List readAllEntities() throws Exception {
        return List.of();
    }
}
