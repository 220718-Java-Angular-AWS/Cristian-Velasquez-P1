package pojos;

public class Reimbursement {
    private Integer id;
    private String title;
    private String description;
    private String statusTitle;
    private Integer statusId;
    private Integer userId;
    private Double amount;

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public Reimbursement() {

    }

    public Reimbursement(Integer id, String title, String description, Integer statusId, Integer userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.statusId = statusId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusTitle() {
        return statusTitle;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setStatusTitle(String statusTitle) { this.statusTitle = statusTitle; }
}
