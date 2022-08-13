package daos;

import pojos.User;
import pojos.UserType;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserTypeDAO implements DatasourceCRUD<UserType>{

    Connection connection;

    public UserTypeDAO(){
        this.connection = PersistencePostgres.getConnection();
    }

    @Override
    public UserType create(UserType userType) {
        try {
            String sql = "INSERT INTO user_type (user_type_title) VALUES (?)";

            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, userType.getUserType() );

            pstmt.executeUpdate();
            ResultSet ids = pstmt.getGeneratedKeys();

            if (ids.next()) {
                Integer id = ids.getInt("user_type_id");
                userType.setUserTypeID(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return userType;
    }

    @Override
    public UserType read(int id) {
        UserType userType = new UserType();
        try {
            String sql = "SELECT * FROM user_type WHERE user_type_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                userType.setUserTypeID(result.getInt("user_type_id"));
                userType.setUserType(result.getString("user_type_title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userType;
    }

    @Override
    public List<UserType> readAll() {
        List<UserType> userTypeList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM user_type";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();
            while (results.next()){
                UserType userType = new UserType();

                userType.setUserTypeID(results.getInt("user_type_id"));
                userType.setUserType(results.getString("user_type_title"));

                userTypeList.add(userType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userTypeList;
    }

    @Override
    public void update(UserType userType) {
        try{
            String sql = "UPDATE user_type SET user_type_title = ? WHERE user_type_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, userType.getUserType());
            pstmt.setInt(2, userType.getUserTypeId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM user_type WHERE user_type_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
