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

    public User getUserByEmail(String email) {
        List<User> userList = this.getAllUser();
        User user = null;
        System.out.println(email);
        for (User u : userList ) {
            if (u.getEmail().equals(email)) {
                user = u;
            }
        }
        return user;
    }

    public List<User> getAllUser(){
        return persistence.readAll();
    }

    public User saveUser(User user){
        return persistence.create(user);
    }

    public void updateUser(User user){
        persistence.update(user);
    }

    public void deleteUser(int id){
        persistence.delete(id);
    }

}
