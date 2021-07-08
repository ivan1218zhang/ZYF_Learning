import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionAdd_Handle implements ActionListener {
    QuestionAdd_View view;
    QuestionAdd question;

    public QuestionAdd_Handle(QuestionAdd_View view) {
        this.view=view;
        question=new QuestionAdd();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String table=view.tableTextField.getText();
        String text=view.textField.getText();
        String pic=view.pic_url.getText();
        String a=view.aTextField.getText();
        String b=view.bTextField.getText();
        String c=view.cTextField.getText();
        String d=view.dTextField.getText();
        String answer=view.answerTextField.getText();
        if (!(answer.equals("A")||answer.equals("B")||answer.equals("C")||answer.equals("D"))){
            JOptionPane.showMessageDialog(null, "必须有个正确答案ABCD", "错误",JOptionPane.WARNING_MESSAGE);
            return;
        }
        question.setText(text);
        question.setImage_url(pic);
        question.setA(a);
        question.setB(b);
        question.setC(c);
        question.setD(d);
        question.setAnswer(answer);
        question.setTable(table);
        question.add();
        JOptionPane.showMessageDialog(null, "加入题库成功", "",JOptionPane.WARNING_MESSAGE);
    }
}
