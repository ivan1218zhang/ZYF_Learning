import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class QuestionUpdate_Handle implements MouseListener, ActionListener, KeyListener {
    QuestionUpdate_View view;
    final static String[] columnNames={"问题序号","题干","图片","A","B","C","D","答案","题库"};
    String[][] tableValues;
    QuestionUpdate upgradeQuestion;
    QuestionDelete deleteQuestion;

    public QuestionUpdate_Handle(QuestionUpdate_View view) {
        this.view = view;
        upgradeQuestion=new QuestionUpdate();
        deleteQuestion=new QuestionDelete();
        new Command(3).command();
        Question[] questions= (Question[]) ThreadRead.obj;
        tableValues=new String[questions.length][9];
        for(int i=0;i<questions.length;i++){
            tableValues[i][0]= String.valueOf(questions[i].getId());
            tableValues[i][1]=questions[i].getText();
            tableValues[i][2]=questions[i].getImage_url();
            tableValues[i][3]=questions[i].getA();
            tableValues[i][4]=questions[i].getB();
            tableValues[i][5]=questions[i].getC();
            tableValues[i][6]=questions[i].getD();
            tableValues[i][7]=questions[i].getAnswer();
            tableValues[i][8]=questions[i].getTable();
        }
        view.table=new MyTable(tableValues,columnNames);
        view.jScrollPane=new JScrollPane(view.table);
        view.jScrollPane.validate();
        view.jScrollPane.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3){
            int row=view.table.getSelectedRow();
            try{
                int delete_id=Integer.valueOf(tableValues[row][0]);
                deleteQuestion.setId(delete_id);
                deleteQuestion.delete();
                JOptionPane.showMessageDialog(null, "删除题目成功", "",JOptionPane.WARNING_MESSAGE);
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button_name=e.getActionCommand();
        if (button_name.equals("刷新")){
            view.remove(view.jScrollPane);
            new Command(3).command();
            Question[] questions= (Question[]) ThreadRead.obj;
            tableValues=new String[questions.length][10];
            for(int i=0;i<questions.length;i++){
                tableValues[i][0]= String.valueOf(questions[i].getId());
                tableValues[i][1]=questions[i].getText();
                tableValues[i][2]=questions[i].getImage_url();
                tableValues[i][3]=questions[i].getA();
                tableValues[i][4]=questions[i].getB();
                tableValues[i][5]=questions[i].getC();
                tableValues[i][6]=questions[i].getD();
                tableValues[i][7]=questions[i].getAnswer();
                tableValues[i][8]=questions[i].getTable();
            }
            view.table=new MyTable(tableValues,columnNames);
            view.table.addMouseListener(this);
            view.table.addKeyListener(this);
            view.jScrollPane=new JScrollPane(view.table);
            view.add(view.jScrollPane);
            view.validate();
            view.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar()==KeyEvent.VK_ENTER){
            int row=view.table.getSelectedRow();
            String id= (String) view.table.getValueAt(row,0);
            String text= (String) view.table.getValueAt(row,1);
            String pic= (String) view.table.getValueAt(row,2);
            String a= (String) view.table.getValueAt(row,3);
            String b= (String) view.table.getValueAt(row,4);
            String c= (String) view.table.getValueAt(row,5);
            String d= (String) view.table.getValueAt(row,6);
            String result= (String) view.table.getValueAt(row,7);
            upgradeQuestion.setId(Integer.valueOf(id));
            upgradeQuestion.setText(text);
            upgradeQuestion.setImage_url(pic);
            upgradeQuestion.setA(a);
            upgradeQuestion.setB(b);
            upgradeQuestion.setC(c);
            upgradeQuestion.setD(d);
            upgradeQuestion.setAnswer(result);
            try {
                upgradeQuestion.update();
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "更新图片失败", "错误",JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
