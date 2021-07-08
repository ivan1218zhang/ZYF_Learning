import javax.swing.*;
import java.awt.*;

public class QuestionAdd_View extends JPanel {
    JLabel table;
    JTextArea tableTextField;
    JLabel text;
    JTextArea textField;
    JLabel pic;
    JTextArea pic_url;
    JLabel aLabel;
    JTextArea aTextField;
    JLabel bLabel;
    JTextArea bTextField;
    JLabel cLabel;
    JTextArea cTextField;
    JLabel dLabel;
    JTextArea dTextField;
    JLabel answer;
    JTextArea answerTextField;
    JButton add;
    Box box;
    QuestionAdd_Handle questionAdd_handle;

    public QuestionAdd_View() {
        init();
        registerHandle();
        setVisible(true);
    }

    private void registerHandle() {
        questionAdd_handle=new QuestionAdd_Handle(this);
        add.addActionListener(questionAdd_handle);
    }

    private void init() {
        setLayout(new FlowLayout());
        table=new JLabel("题库:");
        tableTextField=new JTextArea();
        text=new JLabel("题干:");
        textField= new JTextArea();
        pic=new JLabel("图片:");
        pic_url= new JTextArea();
        aLabel=new JLabel("A:");
        bLabel=new JLabel("B:");
        cLabel=new JLabel("C:");
        dLabel=new JLabel("D:");
        aTextField= new JTextArea(5,30);
        bTextField= new JTextArea(5,30);
        cTextField= new JTextArea(5,30);
        dTextField= new JTextArea(5,30);
        answer=new JLabel("答案:");
        answerTextField= new JTextArea();
        add=new JButton("提交");
        box=Box.createVerticalBox();
        box.add(new JLabel("注意：题目图片输入本地图片的绝对路径"));
        box.add(getBox(table,tableTextField));
        box.add(getBox(text,textField));
        box.add(getBox(pic,pic_url));
        box.add(getBox(aLabel,aTextField));
        box.add(getBox(bLabel,bTextField));
        box.add(getBox(cLabel,cTextField));
        box.add(getBox(dLabel,dTextField));
        box.add(getBox(answer,answerTextField));
        box.add(add);
        add(box);
    }

    Box getBox(JLabel label,JTextArea textField){
        Box b=Box.createHorizontalBox();
        b.add(label);
        b.add(textField);
        return b;
    }


}
