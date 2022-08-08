package daos;

import pojos.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

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
        User user = new User();
        try {
            String sql = "SELECT * FROM users u INNER JOIN user_type ut ON u.user_type_id = ut.user_type_id WHERE u.user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                setUserFields(result, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> readAll() {
        List<User> userList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM users u INNER JOIN user_type ut ON u.user_type_id = ut.user_type_id";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();
            while (results.next()){
                User user = new User();

                setUserFields(results, user);

                userList.add(user);
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }



    @Override
    public void update(User user) {
        try{
            String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ?, user_type_id = ? WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getUserTypeID());
            pstmt.setInt(6, user.getID());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setUserFields(ResultSet results, User user) throws SQLException {
        user.setId(results.getInt("user_id"));
        user.setFirstName(results.getString("first_name"));
        user.setLastName(results.getString("last_name"));
        user.setUserTypeID(results.getInt("user_type_id"));
        user.setEmail(results.getString("email"));
        user.setPassword(results.getString("password"));
        user.setUserType(results.getString("user_type_title"));
    }
}
