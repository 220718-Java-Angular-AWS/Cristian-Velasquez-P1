package services;

import daos.ReimbursementStatusDAO;
import pojos.ReimbursementStatus;

import java.util.List;

public class ReimbursementStatusService {
    private ReimbursementStatusDAO persistence;

    public ReimbursementStatusService() {
        this.persistence = new ReimbursementStatusDAO();
    }

    public ReimbursementStatus getReimbursementStatus(int id){
        return persistence.read(id);
    }

    public List<ReimbursementStatus> getAllReimbursementStatus(){
        return persistence.readAll();
    }

    public ReimbursementStatus saveReimbursementStatus(ReimbursementStatus reimbursementStatus){
        return persistence.create(reimbursementStatus);
    }

    public void updateReimbursementStatus(ReimbursementStatus reimbursementStatus){
        persistence.update(reimbursementStatus);
    }

    public void deleteReimbursementStatus(int id){
        persistence.delete(id);
    }
}
