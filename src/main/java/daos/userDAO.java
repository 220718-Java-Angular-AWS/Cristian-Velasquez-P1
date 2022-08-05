package daos;

import pojos.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class userDAO implements DatasourceCRUD<User> {

    Connection connection;

    public userDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        try{
            String sql = "INSERT INTO users (first_name, last_name, email, password, user_type_id) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getUserTypeID());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User read(int id) {
        return null;
    }

    @Override
    public List<User> readAll() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }
}
