import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Handle implements ActionListener {
    View view;
    static UserInfo userInfo=new UserInfoLogin();

    public Handle(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button_name=e.getActionCommand();
        if (button_name.equals("登录")){
            view.show.removeAll();
            view.show.add(view.login);
            view.show.validate();
            view.show.repaint();
        }
        else if (button_name.equals("注册")){
            view.show.removeAll();
            view.show.add(view.register);
            view.show.validate();
            view.show.repaint();
        }
        else if (button_name.equals("增加题库")){
            if (!userInfo.getUserType().equals("管理员")){
                JOptionPane.showMessageDialog(null, "你不是管理员", "权限问题",JOptionPane.WARNING_MESSAGE);
                return;
            }
            view.show.removeAll();
            view.show.add(view.insertQuestion);
            view.show.validate();
            view.show.repaint();
        }
        else if (button_name.equals("删改问题")){
            if (!userInfo.getUserType().equals("管理员")){
                JOptionPane.showMessageDialog(null, "你不是管理员", "权限问题",JOptionPane.WARNING_MESSAGE);
                return;
            }
            view.show.removeAll();
            view.show.add(view.updateQuestion);
            view.show.validate();
            view.show.repaint();
        }
        else if (button_name.equals("考试页面")){
            if (userInfo.getUserType().equals("")){
                JOptionPane.showMessageDialog(null, "请先登录", "权限问题",JOptionPane.WARNING_MESSAGE);
                return;
            }
            view.show.removeAll();
            if (userInfo.getId()!=view.test_view.userInfoTest_handle.userInfo_test.getId()){
                view.test_view=new UserInfoTest_View();
            }
            view.show.add(view.test_view);
            view.show.validate();
            view.show.repaint();
        }

    }
}
