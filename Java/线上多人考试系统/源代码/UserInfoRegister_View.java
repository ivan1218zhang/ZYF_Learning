import javax.swing.*;
import java.awt.*;

public class UserInfoRegister_View extends JPanel {
    JLabel userID;
    JTextField userIDText;
    JLabel userName;
    JTextField userNameText;
    JLabel userPassword;
    JPasswordField userPasswordText;
    JButton register;
    JLabel userType;
    ButtonGroup bg;
    JRadioButton user0;
    JRadioButton user1;
    Box box0;
    Box box1;
    Box box2;
    Box box3;
    Box box;
    UserInfoRegister_Handle userInfoRegister_handle;

    public UserInfoRegister_View() {
        init();
        registerHandle();
        setVisible(true);
    }

    private void registerHandle() {
        userInfoRegister_handle=new UserInfoRegister_Handle(this);
        register.addActionListener(userInfoRegister_handle);
    }

    private void init() {
        setLayout(new FlowLayout());
        userID=new JLabel("  ID  :");
        userIDText=new JTextField(10);
        userName=new JLabel("名字:");
        userNameText=new JTextField(10);
        userPassword=new JLabel("密码:");
        userPasswordText=new JPasswordField(10);
        register=new JButton("注册");
        userType=new JLabel("账户类型:");
        bg=new ButtonGroup();
        user0=new JRadioButton("学生");
        user1=new JRadioButton("管理员");
        box=Box.createVerticalBox();
        box0=Box.createHorizontalBox();
        box1=Box.createHorizontalBox();
        box2=Box.createHorizontalBox();
        box3=Box.createHorizontalBox();
        box0.add(userID);
        box0.add(userIDText);
        box1.add(userName);
        box1.add(userNameText);
        box2.add(userPassword);
        box2.add(userPasswordText);
        bg.add(user0);
        bg.add(user1);
        box3.add(userType);
        box3.add(user0);
        box3.add(user1);
        box.add(box0);
        box.add(box1);
        box.add(box2);
        box.add(box3);
        box.add(register);
        add(box);
    }
}
