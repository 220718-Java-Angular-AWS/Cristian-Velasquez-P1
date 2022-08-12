package daos;

import pojos.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDAO implements DatasourceCRUD<User> {

    Connection connection;

    public UserDAO(){
        this.connection = PersistencePostgres.getConnection();
    }

    @Override
    public User create(User user) {
        try{
            String sql = "INSERT INTO users (first_name, last_name, email, password, user_type_id) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getUserTypeID());

            pstmt.executeUpdate();

            ResultSet ids = pstmt.getGeneratedKeys();
            if (ids.next()) {
                Integer id = ids.getInt("user_id");
                user.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
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
                setFields(result, user);
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

                setFields(results, user);

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
            e.printStackTrace();
        }

    }

    private void setFields(ResultSet results, User user) throws SQLException {
        user.setId(results.getInt("user_id"));
        user.setFirstName(results.getString("first_name"));
        user.setLastName(results.getString("last_name"));
        user.setUserTypeID(results.getInt("user_type_id"));
        user.setEmail(results.getString("email"));
        user.setPassword(results.getString("password"));
        user.setUserType(results.getString("user_type_title"));
    }
}
