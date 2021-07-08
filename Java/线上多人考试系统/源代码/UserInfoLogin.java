public class UserInfoLogin extends UserInfo{
    public void login(){
        setCommand(0);
        command();
        this.copy((UserInfo) ThreadRead.obj);
    }
}
