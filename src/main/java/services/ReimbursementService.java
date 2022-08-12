package services;

import daos.PersistencePostgres;
import daos.ReimbursementDAO;
import pojos.Reimbursement;

import java.util.List;

public class ReimbursementService {
    private ReimbursementDAO persistence;

    public ReimbursementService(){
        this.persistence = new ReimbursementDAO();
    }

    public Reimbursement getReimbursement(int id){
        return persistence.read(id);
    }

    public List<Reimbursement> getAllReimbursement(){
        return persistence.readAll();
    }

    public Reimbursement saveReimbursement(Reimbursement reimbursement){
        return persistence.create(reimbursement);
    }

    public void updateReimbursement(Reimbursement reimbursement){
        persistence.update(reimbursement);
    }

    public void deleteReimbursement(int id){
        persistence.delete(id);
    }

}
