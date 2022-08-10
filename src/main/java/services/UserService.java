package services;

import daos.UserDAO;
import pojos.User;

import java.util.List;

public class UserService {
    private UserDAO persistence;

    public UserService(){
        this.persistence = new UserDAO();
    }

    public User getUser(int id){
        return persistence.read(id);
    }

    public List<User> getAllUser(){
        return persistence.readAll();
    }

    public void saveUser(User user){
        persistence.create(user);
    }

    public void updateUser(User user){
        persistence.update(user);
    }

    public void deleteUser(int id){
        persistence.delete(id);
    }

}
