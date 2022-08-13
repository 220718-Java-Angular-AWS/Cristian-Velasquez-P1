package pojos;

public class UserType {
    private int userTypeId;
    private String userType;

    public UserType() {

    }

    public UserType(int id, String title) {
        this.userTypeId = id;
        this.userType = title;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserTypeID(int userTypeID) {
        this.userTypeId = userTypeID;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
