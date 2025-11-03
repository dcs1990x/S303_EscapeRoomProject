package daomodel;

import java.util.List;
import java.util.Optional;

public interface DaoInterface<T> {
    void insertEntity(T entity, int id) throws Exception;
    T readEntity(long entityId) throws Exception;
    void updateEntity(long entityId, T entity) throws Exception;
    void deleteEntity(long entityId) throws Exception;
    List<T> readAllEntities() throws Exception;

}