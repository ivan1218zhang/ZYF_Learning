public class UserInfo extends Command {
    int id=-1;
    String userName="";
    String userPassword="";
    String userType="";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void copy(UserInfo userInfo){
        this.setId(userInfo.getId());
        this.setUserType(userInfo.getUserType());
        this.setUserName(userInfo.getUserName());
        this.setUserPassword(userInfo.getUserPassword());
    }
}
