package repository;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudRepository<T> {
    void save(T model);
    void create(T model) throws ClassNotFoundException, SQLException;
    T findByID(int ID) throws SQLException,ClassNotFoundException;
    void delete(T model);
    ArrayList<T> findAll() throws SQLException, ClassNotFoundException;
    void update(T model) throws ClassNotFoundException, SQLException;

}
