import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfoLogin_Handle implements ActionListener {
    UserInfoLogin_View view;
    UserInfoLogin userInfo_login;

    public UserInfoLogin_Handle(UserInfoLogin_View view) {
        this.view=view;
        userInfo_login=new UserInfoLogin();
        userInfo_login.copy(Handle.userInfo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id;
        try {
            id=Integer.valueOf(view.userIDText.getText());
            userInfo_login.setId(id);
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, "ID必须为数字", "登录出错",JOptionPane.WARNING_MESSAGE);
            return;
        }
        userInfo_login.setUserPassword(view.userPasswordText.getText());
        userInfo_login.login();
        if (userInfo_login.getUserType().equals("")){
            JOptionPane.showMessageDialog(null, "ID或者密码错误", "登录出错",JOptionPane.WARNING_MESSAGE);
            return;
        }
        Handle.userInfo.copy(userInfo_login);
        JOptionPane.showMessageDialog(null, "登录成功", "",JOptionPane.WARNING_MESSAGE);
    }
}
