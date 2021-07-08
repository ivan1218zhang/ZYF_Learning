import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfoRegister_Handle implements ActionListener {
    UserInfoRegister_View view;
    UserInfoRegister userInfo_register;

    public UserInfoRegister_Handle(UserInfoRegister_View view) {
        this.view=view;
        userInfo_register=new UserInfoRegister();
        userInfo_register.copy(Handle.userInfo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id;
        try {
            id=Integer.valueOf(view.userIDText.getText());
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, "ID必须为数字", "注册出错",JOptionPane.WARNING_MESSAGE);
            return;
        }
        String type="";
        if (view.user0.isSelected()){
            type="学生";
        }
        if (view.user1.isSelected()){
            type="管理员";
        }
        if (type.equals("")){
            JOptionPane.showMessageDialog(null, "注册类型未选择", "注册出错",JOptionPane.WARNING_MESSAGE);
            return;
        }
        userInfo_register.setId(id);
        userInfo_register.setUserName(view.userNameText.getText());
        userInfo_register.setUserPassword(view.userPasswordText.getText());
        userInfo_register.setUserType(type);
        userInfo_register.register();
        userInfo_register.copy((UserInfoRegister) ThreadRead.obj);
        int result=userInfo_register.getResult();
        if (result==0){
            JOptionPane.showMessageDialog(null, "注册成功", "",JOptionPane.WARNING_MESSAGE);
            Handle.userInfo.copy(userInfo_register);
        }
        if (result==1){
            JOptionPane.showMessageDialog(null, "ID重复", "注册出错",JOptionPane.WARNING_MESSAGE);
        }
    }
}
