import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    JPanel main_panel;
    JPanel show;
    JButton login_button;
    UserInfoLogin_View login;
    JButton register_button;
    UserInfoRegister_View register;
    JButton insertQuestion_button;
    QuestionAdd_View insertQuestion;
    JButton updateQuestion_button;
    QuestionUpdate_View updateQuestion;
    JButton test_button;
    UserInfoTest_View test_view;
    Handle handle;

    public View() {
        init();
        registerHandle();
        show.setVisible(true);
        setVisible(true);
    }

    private void registerHandle() {
        handle=new Handle(this);
        login_button.addActionListener(handle);
        register_button.addActionListener(handle);
        insertQuestion_button.addActionListener(handle);
        updateQuestion_button.addActionListener(handle);
        test_button.addActionListener(handle);
    }

    private void init() {
        login_button=new JButton("登录");
        login=new UserInfoLogin_View();
        register_button=new JButton("注册");
        register=new UserInfoRegister_View();
        insertQuestion_button=new JButton("增加题库");
        insertQuestion=new QuestionAdd_View();
        updateQuestion_button=new JButton("删改问题");
        updateQuestion=new QuestionUpdate_View();
        test_button=new JButton("考试页面");
        test_view=new UserInfoTest_View();
        main_panel=new JPanel();
        show=new JPanel();
        main_panel.setLayout(null);
        setSize(700,550);
        setLayout(new FlowLayout());
        setResizable(false);
        setContentPane(main_panel);
        setTitle("张依凡3200608016考试系统");
        setMenu();
    }

    public void setMenu() {
        login_button.setBounds(10,40,100,50);
        main_panel.add(login_button);
        register_button.setBounds(10,120,100,50);
        main_panel.add(register_button);
        insertQuestion_button.setBounds(10,200,100,50);
        main_panel.add(insertQuestion_button);
        updateQuestion_button.setBounds(10,280,100,50);
        main_panel.add(updateQuestion_button);
        test_button.setBounds(10,360,100,50);
        main_panel.add(test_button);
        show.setBounds(120,10,580,490);
        main_panel.add(show);
    }
}
