public class UserInfoRegister extends UserInfo{
    int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void register(){
        setCommand(1);
        command();
    }

    public void copy(UserInfoRegister userInfo) {
        super.copy(userInfo);
        setResult(userInfo.getResult());
    }
}
