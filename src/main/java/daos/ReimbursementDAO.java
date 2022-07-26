package daos;

import pojos.Reimbursement;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ReimbursementDAO implements DatasourceCRUD<Reimbursement> {

    Connection connection;

    public ReimbursementDAO(){
        this.connection = PersistencePostgres.getConnection();
    }

    @Override
    public Reimbursement create(Reimbursement reimbursement) {
        try{
            String sql = "INSERT INTO reimbursement (reimbursement_title, reimbursement_description, reimbursement_status_id, user_id, amount) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, reimbursement.getTitle());
            pstmt.setString(2, reimbursement.getDescription());
            pstmt.setInt(3, reimbursement.getStatusId());
            pstmt.setInt(4, reimbursement.getUserId());
            pstmt.setDouble(5, reimbursement.getAmount());

            pstmt.executeUpdate();
            ResultSet ids = pstmt.getGeneratedKeys();
            if (ids.next()) {
                Integer id = ids.getInt("reimbursement_id");
                reimbursement.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reimbursement;
    }

    @Override
    public Reimbursement read(int id) {
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
            String sql = "UPDATE reimbursement SET reimbursement_title = ?, reimbursement_description = ?, reimbursement_status_id = ?, amount = ? WHERE reimbursement_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, reimbursement.getTitle());
            pstmt.setString(2, reimbursement.getDescription());
            pstmt.setInt(3, reimbursement.getStatusId());
            pstmt.setDouble(4, reimbursement.getAmount());
            pstmt.setInt(5, reimbursement.getId());

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
        reimbursement.setStatusTitle(results.getString("reimbursement_status_name"));
        reimbursement.setAmount(results.getDouble("amount"));
    }
}
