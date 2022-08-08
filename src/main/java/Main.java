import daos.PersistencePostgres;
import pojos.User;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("connecting...");
        PersistencePostgres persistence = PersistencePostgres.getInstance();
        persistence.setTable("user");
        List<User> users = persistence.communicate().readAll();
        for (User u: users){
            System.out.println(u.getEmail());
        }
        persistence.communicate().delete(5);
        System.out.println("done!");

    }

}
