package services;

import daos.PersistencePostgres;
import pojos.User;

import java.util.List;

public class UserService {
    private PersistencePostgres persistence;

    public UserService(){
        this.persistence = PersistencePostgres.getInstance();
        this.persistence.setTable("user");
    }

    public User getUser(int id){
        return persistence.communicate().read(id);
    }

    public List<User> getAllUser(){
        return persistence.communicate().readAll();
    }

    public void saveUser(User user){
        persistence.communicate().create(user);
    }

    public void updateUser(User user){
        persistence.communicate().update(user);
    }

    public void deleteUser(int id){
        persistence.communicate().delete(id);
    }

}
