package daos;

import pojos.Reimbursement;
import pojos.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ReimbursementDAO implements DatasourceCRUD<Reimbursement> {

    Connection connection;

    public ReimbursementDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public void create(Reimbursement reimbursement) {
        try{
            String sql = "INSERT INTO reimbursement (reimbursement_title, reimbursement_description, reimbursement_status_id, user_id) VALUES (?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, reimbursement.getTitle());
            pstmt.setString(2, reimbursement.getDescription());
            pstmt.setInt(3, reimbursement.getStatusId());
            pstmt.setInt(4, reimbursement.getUserId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User read(int id) {
        Reimbursement reimbursement = new Reimbursement();
        try {
            String sql = "SELECT * FROM reimbursement r INNER JOIN reimbursement_status rs ON r.reimbursement_status_id = rs.reimbursement_status_id WHERE r.reimbursement_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                setFields(result, reimbursement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursement;
    }

    @Override
    public List<Reimbursement> readAll() {
        List<Reimbursement> reimbursementsList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM reimbursement r INNER JOIN reimbursement_status rs ON r.reimbursement_status_id = rs.reimbursement_status_id";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();
            while (results.next()){
                Reimbursement reimbursement = new Reimbursement();

                setFields(results, reimbursement);

                reimbursementsList.add(reimbursement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursementsList;
    }

    @Override
    public void update(Reimbursement reimbursement) {
        try{
            String sql = "UPDATE reimbursement SET reimbursement_title = ?, reimbursement_description = ?, reimbursement_status_id = ? WHERE reimbursement_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, reimbursement.getTitle());
            pstmt.setString(2, reimbursement.getDescription());
            pstmt.setInt(3, reimbursement.getStatusId());
            pstmt.setInt(4, reimbursement.getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM reimbursement WHERE reimbursement_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setFields(ResultSet results, Reimbursement reimbursement) throws SQLException {
        reimbursement.setId(results.getInt("reimbursement_id"));
        reimbursement.setTitle(results.getString("reimbursement_title"));
        reimbursement.setDescription(results.getString("reimbursement_description"));
        reimbursement.setStatusId(results.getInt("reimbursement_status_id"));
        reimbursement.setUserId(results.getInt("user_id"));
    }
}
