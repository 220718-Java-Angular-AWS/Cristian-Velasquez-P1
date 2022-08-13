package daos;

import pojos.ReimbursementStatus;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ReimbursementStatusDAO implements DatasourceCRUD<ReimbursementStatus> {
    Connection connection;

    public ReimbursementStatusDAO() {
        this.connection = PersistencePostgres.getConnection();
    }

    @Override
    public ReimbursementStatus create(ReimbursementStatus reimbursementStatus) {
        try{
            String sql = "INSERT INTO reimbursement_status (reimbursement_status_name) VALUES (?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, reimbursementStatus.getStatusTitle());

            pstmt.executeUpdate();
            ResultSet ids = pstmt.getGeneratedKeys();
            if (ids.next()) {
                Integer id = ids.getInt("reimbursement_status_id");
                reimbursementStatus.setStatusId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reimbursementStatus;
    }

    @Override
    public ReimbursementStatus read(int id) {
        ReimbursementStatus reimbursementStatus = new ReimbursementStatus();
        try {
            String sql = "SELECT * FROM reimbursement_status WHERE reimbursement_status_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                reimbursementStatus.setStatusId(result.getInt("reimbursement_status_id"));
                reimbursementStatus.setStatusTitle(result.getString("reimbursement_status_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursementStatus;
    }

    @Override
    public List<ReimbursementStatus> readAll() {
        List<ReimbursementStatus> reimbursementStatusList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM reimbursement_status";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();
            while (results.next()){
                ReimbursementStatus reimbursementStatus = new ReimbursementStatus();

                reimbursementStatus.setStatusId(results.getInt("reimbursement_status_id"));
                reimbursementStatus.setStatusTitle(results.getString("reimbursement_status_name"));

                reimbursementStatusList.add(reimbursementStatus);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursementStatusList;
    }

    @Override
    public void update(ReimbursementStatus reimbursementStatus) {
        try{
            String sql = "UPDATE reimbursement_status SET reimbursement_status_name = ? WHERE reimbursement_status_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, reimbursementStatus.getStatusTitle());
            pstmt.setInt(2, reimbursementStatus.getStatusId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM reimbursement_status WHERE reimbursement_status_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
