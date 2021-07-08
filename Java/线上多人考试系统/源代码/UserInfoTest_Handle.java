import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfoTest_Handle implements ActionListener,Runnable {
    UserInfoTest_View view;
    Test_View test_view;
    Answer_View answer_view;
    static QuestionTest[] questions;
    UserInfoTest userInfo_test;

    public UserInfoTest_Handle(UserInfoTest_View view) {
        this.view = view;
        userInfo_test=new UserInfoTest();
        userInfo_test.copy(Handle.userInfo);
    }

    public UserInfoTest_Handle(Test_View test_view) {
        this.test_view = test_view;
        test_view.answer_views=new Answer_View[questions.length];
        for(int i=0;i<questions.length;i++){
            Answer_View answer_view=new Answer_View(i);
            answer_view.userInfoTest_handle.setAnswer_view(i);
            test_view.answer_views[i]=answer_view;
            test_view.show.add(answer_view);
        }
    }

    public UserInfoTest_Handle(Answer_View answer_view) {
        this.answer_view = answer_view;
    }

    public void setAnswer_view(int i) {
        answer_view.text=new JLabel(questions[i].getText());
        answer_view.pic=new JLabel(new ImageIcon(questions[i].pic_data));
        answer_view.a=new JRadioButton(questions[i].getA());
        answer_view.b=new JRadioButton(questions[i].getB());
        answer_view.c=new JRadioButton(questions[i].getC());
        answer_view.d=new JRadioButton(questions[i].getD());
    }

    public void setQuestions(QuestionTest[] questions) {
        this.questions = questions;
    }

    public Question[] getQuestions() {
        return questions;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand(); // 获取是何种操作
        if ("下一题".equals(cmd)){
            test_view.cardLayout.next(test_view.show); // 切换下一个选项卡
        }
        else if ("上一题".equals(cmd)){
            test_view.cardLayout.previous(test_view.show); // 切换上一个选项卡
        }
        if ("开始考试".equals(cmd)){
            if (view.box.getComponents().length==2){
                JOptionPane.showMessageDialog(null, "已经开始考试", "",JOptionPane.WARNING_MESSAGE);
                return;
            }
            new Command(7).command();
            Question[] questions1=(Question[])ThreadRead.obj;
            QuestionTest[] questionTests=new QuestionTest[questions1.length];
            for(int i=0;i<questionTests.length;i++){
                questionTests[i]=new QuestionTest();
                questionTests[i].copy(questions1[i]);
            }
            setQuestions(questionTests);
            view.test_view=new Test_View();
            view.box.add(view.test_view);
            new Thread(this).start();
            view.test_view.setVisible(true);
        }
        else if ("交卷".equals(cmd)){
            if (view.box.getComponents().length==1){
                JOptionPane.showMessageDialog(null, "请先开始考试", "",JOptionPane.WARNING_MESSAGE);
                return;
            }
            for(int i=0;i<questions.length;i++){
                if (view.test_view.answer_views[i].a.isSelected()){
                    questions[i].setUserAnswer("A");
                }
                if (view.test_view.answer_views[i].b.isSelected()){
                    questions[i].setUserAnswer("B");
                }
                if (view.test_view.answer_views[i].c.isSelected()){
                    questions[i].setUserAnswer("C");
                }
                if (view.test_view.answer_views[i].d.isSelected()){
                    questions[i].setUserAnswer("D");
                }
            }
            int mark=0;
            for (int i=0;i<questions.length;i++){
                mark+=questions[i].getMark();
            }
            userInfo_test.setMark(mark);
            userInfo_test.saveMark();
            view.box.remove(view.test_view);
            view.validate();
            view.repaint();
            JOptionPane.showMessageDialog(null, mark+"分", "",JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void run() {
        long now=System.currentTimeMillis();
        int d= 1;
        while (d>0){
            d= (int) (now+10*1000-System.currentTimeMillis())/1000;
            if (view.box.getComponents().length==1){
                return;
            }
            view.test_view.time.setText("还有"+d+"秒");
        }
        if (view.box.getComponents().length==1){
            return;
        }
        for(int i=0;i<questions.length;i++){
            if (view.test_view.answer_views[i].a.isSelected()){
                questions[i].setUserAnswer("A");
            }
            if (view.test_view.answer_views[i].b.isSelected()){
                questions[i].setUserAnswer("B");
            }
            if (view.test_view.answer_views[i].c.isSelected()){
                questions[i].setUserAnswer("C");
            }
            if (view.test_view.answer_views[i].d.isSelected()){
                questions[i].setUserAnswer("D");
            }

        }
        int mark=0;
        for (int i=0;i<questions.length;i++){
            mark+=questions[i].getMark();
        }
        userInfo_test.setMark(mark);
        userInfo_test.saveMark();
        JOptionPane.showMessageDialog(null, mark+"分", "",JOptionPane.WARNING_MESSAGE);
        view.box.remove(view.test_view);
        view.validate();
        view.repaint();
    }
}
