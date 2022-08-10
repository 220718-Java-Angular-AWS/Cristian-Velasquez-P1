import daos.PersistencePostgres;
import pojos.Reimbursement;
import pojos.User;
import services.ReimbursementService;
import services.UserService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("connecting...");

        UserService newUser = new UserService();
        User u = newUser.getUser(1);
        System.out.println(u.getLastName());

        ReimbursementService newR = new ReimbursementService();
        Reimbursement r = newR.getReimbursement(1);
        System.out.println(r.getTitle());

        System.out.println("done!");


    }

}
