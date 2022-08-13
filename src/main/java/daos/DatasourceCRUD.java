package daos;

import pojos.User;

import java.sql.SQLException;
import java.util.List;

public interface DatasourceCRUD<T> {

    T create(T t) throws SQLException;
    T read(int id);
    List<T> readAll();
    void update(T t);
    void delete(int id);
}
