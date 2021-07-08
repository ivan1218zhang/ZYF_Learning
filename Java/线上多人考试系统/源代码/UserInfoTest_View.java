import javax.swing.*;
import java.awt.*;

public class UserInfoTest_View extends JPanel{
    JButton start;
    JButton submit;
    Test_View test_view;
    Box box;
    Box box1;
    UserInfoTest_Handle userInfoTest_handle;

    public UserInfoTest_View() {
        init();
        registerHandle();
        setVisible(true);
    }

    private void registerHandle() {
        userInfoTest_handle=new UserInfoTest_Handle(this);
        start.addActionListener(userInfoTest_handle);
        submit.addActionListener(userInfoTest_handle);
    }

    private void init() {
        box=Box.createVerticalBox();
        box1=Box.createHorizontalBox();
        start=new JButton("开始考试");
        submit=new JButton("交卷");
        box1.add(start);
        box1.add(submit);
        box.add(box1);
        add(box);
    }

}
class Test_View extends JPanel{
    JButton pre;
    JButton next;
    Box box;
    Box box1;
    CardLayout cardLayout;
    JPanel show;
    Answer_View[] answer_views;
    JLabel time;
    UserInfoTest_Handle userInfoTest_handle;

    public Test_View() {
        cardLayout=new CardLayout();
        time=new JLabel("60");
        pre=new JButton("上一题");
        next=new JButton("下一题");
        show=new JPanel();
        show.setLayout(cardLayout);
        box=Box.createVerticalBox();
        box1=Box.createHorizontalBox();
        init();
        registerHandle();
        setVisible(false);
    }

    private void registerHandle() {
        userInfoTest_handle=new UserInfoTest_Handle(this);
        pre.addActionListener(userInfoTest_handle);
        next.addActionListener(userInfoTest_handle);
    }

    private void init() {
        box1.add(pre);
        box1.add(next);
        box.add(time);
        box.add(box1);
        box.add(show);
        add(box);
    }
}

class Answer_View extends JPanel {
    JLabel text;
    JLabel pic;
    JRadioButton a;
    JRadioButton b;
    JRadioButton c;
    JRadioButton d;
    ButtonGroup group;
    Box box;
    UserInfoTest_Handle userInfoTest_handle;

    public Answer_View(int i) {
        registerHandle(i);
        init();
        setVisible(true);
    }

    private void registerHandle(int i) {
        userInfoTest_handle=new UserInfoTest_Handle(this);
        userInfoTest_handle.setAnswer_view(i);
    }

    private void init() {
        group=new ButtonGroup();
        group.add(a);
        group.add(b);
        group.add(c);
        group.add(d);
        box=Box.createVerticalBox();
        box.add(text);
        box.add(pic);
        box.add(a);
        box.add(b);
        box.add(c);
        box.add(d);
        add(box);
    }
}
