package services;

import daos.PersistencePostgres;
import pojos.Reimbursement;

import java.util.List;

public class ReimbursementService {
    private PersistencePostgres persistence;

    public ReimbursementService(){
        this.persistence = PersistencePostgres.getInstance();
        this.persistence.setTable("reimbursement");
    }

    public Reimbursement getReimbursement(int id){
        return persistence.communicate().read(id);
    }

    public List<Reimbursement> getAllReimbursement(){
        return persistence.communicate().readAll();
    }

    public void saveReimbursement(Reimbursement reimbursement){
        persistence.communicate().create(reimbursement);
    }

    public void updateReimbursement(Reimbursement reimbursement){
        persistence.communicate().update(reimbursement);
    }

    public void deleteReimbursement(int id){
        persistence.communicate().delete(id);
    }

}
