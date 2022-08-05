import daos.PersistencePostgres;
import pojos.User;

public class Main {
    public static void main(String[] args) {
        User user = new User("Jose", "Martinez", "jose@gmail.com", "123451", 2);
        System.out.println("connecting...");
        PersistencePostgres persistence = PersistencePostgres.getInstance();
        persistence.setTable("user");
        persistence.communicate().create(user);
        System.out.println("done!");

    }

}
