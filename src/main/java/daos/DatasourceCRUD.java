package daos;

import pojos.User;

import java.util.List;

public interface DatasourceCRUD<T> {

    void create(T t);
    User read(int id);
    List<T> readAll();
    void update(T t);
    void delete(int id);
}
