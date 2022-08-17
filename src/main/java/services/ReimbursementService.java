package services;

import daos.ReimbursementDAO;
import pojos.Reimbursement;

import java.util.LinkedList;
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

    public List<Reimbursement> getReimbursementByUser(Integer id) {
        List<Reimbursement> reimbursementList = this.getAllReimbursement();
        List<Reimbursement> newReimbursementList = new LinkedList<>();
        for (Reimbursement reimbursement: reimbursementList) {
            if (reimbursement.getUserId() == id) {
                newReimbursementList.add(reimbursement);
            }
        }

        return newReimbursementList;
    }

    public List<Reimbursement> getReimbursementByStatusId(Integer id) {
        List<Reimbursement> reimbursementList = this.getAllReimbursement();
        List<Reimbursement> newReimbursementList = new LinkedList<>();
        for (Reimbursement reimbursement: reimbursementList) {
            if (reimbursement.getStatusId() == id) {
                newReimbursementList.add(reimbursement);
            }
        }

        return newReimbursementList;
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
