package daomodel;

import java.util.List;

public interface DaoInterface<T> {
    void insertEntity(T entity) throws Exception;
    T readEntity(long entityId) throws Exception;
    void updateEntity(long entityId, T entity) throws Exception;
    void deleteEntity(long entityId) throws Exception;
    List<T> readAllEntities() throws Exception;

}
