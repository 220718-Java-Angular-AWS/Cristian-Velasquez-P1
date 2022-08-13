package services;

import daos.UserTypeDAO;
import pojos.UserType;

import java.util.List;

public class UserTypeService {
    private UserTypeDAO persistence;

    public UserTypeService(){
        this.persistence = new UserTypeDAO();
    }

    public UserType getUserType(int id){
        return persistence.read(id);
    }

    public List<UserType> getAllUserType(){
        return persistence.readAll();
    }

    public UserType saveUserType(UserType userType){
        return persistence.create(userType);
    }

    public void updateUserType(UserType userType){
        persistence.update(userType);
    }

    public void deleteUserType(int id){
        persistence.delete(id);
    }


}
