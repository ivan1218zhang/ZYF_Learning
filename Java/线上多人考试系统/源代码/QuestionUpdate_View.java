import javax.swing.*;
import java.awt.*;

public class QuestionUpdate_View extends JPanel{
    MyTable table;
    JButton refresh;
    JScrollPane jScrollPane;
    QuestionUpdate_Handle questionUpdate_handle;
    Box box;

    public QuestionUpdate_View() {
        init();
        registerHandle();
        add(jScrollPane,BorderLayout.CENTER);
        setVisible(true);
    }

    private void registerHandle() {
        questionUpdate_handle=new QuestionUpdate_Handle(this);
        table.addKeyListener(questionUpdate_handle);
        table.addMouseListener(questionUpdate_handle);
        refresh.addActionListener(questionUpdate_handle);
    }

    private void init() {
        setLayout(new BorderLayout());
        refresh=new JButton("刷新");
        add(new JLabel("注意：选中一行，右键则删除改行，摁下ENTER则保存该行数据"),BorderLayout.SOUTH);
        add(refresh,BorderLayout.NORTH);
    }

}
class MyTable extends JTable{
    public MyTable(Object[][] rowData, Object[] columnNames) {
        super(rowData, columnNames);
    }

    public MyTable() {

    }

    @Override
    public boolean isCellEditable(int row, int column){
        if(column == 0){
            return false;
        }else{
            return true;
        }
    }
}
