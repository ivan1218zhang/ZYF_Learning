import javax.swing.*;

public class UserInfoLogin_View extends JPanel{
    JLabel userID;
    JTextField userIDText;
    JLabel userPassword;
    JPasswordField userPasswordText;
    JButton login;
    Box box1;
    Box box2;
    UserInfoLogin_Handle userInfoLogin_handle;

    public UserInfoLogin_View() {
        init();
        registerHandle();
        setVisible(true);
    }

    private void registerHandle() {
        userInfoLogin_handle=new UserInfoLogin_Handle(this);
        login.addActionListener(userInfoLogin_handle);
    }

    private void init() {
        userID=new JLabel("  ID  :");
        userIDText=new JTextField(10);
        userPassword=new JLabel("密码:");
        userPasswordText=new JPasswordField(10);
        login=new JButton("登录");
        box1=Box.createHorizontalBox();
        box2=Box.createHorizontalBox();
        box1.add(userID);
        box1.add(userIDText);
        box2.add(userPassword);
        box2.add(userPasswordText);
        add(box1);
        add(box2);
        add(login);
    }
}
