package pojos;

public class ReimbursementStatus {
    private Integer statusId;
    private String statusTitle;

    public ReimbursementStatus(){

    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public String getStatusTitle() {
        return statusTitle;
    }
}
