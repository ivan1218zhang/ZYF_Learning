import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Random;

public class Server {
    public static void main(String args[]) {
        System.out.println("客户端启动！");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        ServerSocket server = null;
        Socket you = null;
        while (true) {
            try {
                server = new ServerSocket(2021);//创建一个端口号为2021的服务器接口
            } catch (IOException e1) {
            }
            try {
                you = server.accept();//连接服务器与客户端的套接字
                System.out.println("客户的地址:" + you.getInetAddress());
            } catch (IOException e) {

            }
            if (you != null) {
                new ServerThread(you).start(); //为每个客户启动一个专门的线程
            }
        }
    }
}

class ServerThread extends Thread {
    Socket socket;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    DataInputStream isNull;
    DataOutputStream send;
    Command obj;

    ServerThread(Socket t) {
        socket = t;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            isNull= new DataInputStream(socket.getInputStream());
            send=new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
        }
    }

    public void run() {
        Connection con=null;
        PreparedStatement pst_=null;
        PreparedStatement pst=null;
        ResultSet rs;
        try{
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/exam_system","root","3333");
            System.out.println("数据库连接成功");
            isNull.readInt();//堵塞状态，除非读取到信息
            obj=(Command)in.readObject();
            int command = obj.getCommand();
            //用户登录
            if (command==0){
                UserInfoLogin userInfo=(UserInfoLogin) obj;
                pst_=con.prepareStatement("SELECT * FROM `exam_system`.`users` where `user_id`=? and `user_password`=?");
                pst_.setInt(1,userInfo.getId());
                pst_.setString(2,userInfo.getUserPassword());
                rs=pst_.executeQuery();
                if (rs.next()){
                    userInfo.setUserType(rs.getString(3));
                    System.out.println("id为"+userInfo.id+"的用户登录成功");
                }else {
                    userInfo.setUserType("");
                    System.out.println("id为"+userInfo.id+"的用户登录失败");
                }
                send.writeInt(-1);
                out.writeObject(userInfo);
                send.flush();
                out.flush();
            }
            //用户注册
            if (command==1){
                UserInfoRegister userInfo= (UserInfoRegister) obj;
                pst_=con.prepareStatement("SELECT * FROM `exam_system`.`users` where `user_id`=?");
                pst_.setInt(1,userInfo.getId());
                rs=pst_.executeQuery();
                if (rs.next()){
                    userInfo.setResult(1);
                    System.out.println("id为"+userInfo.id+"的用户注册失败");
                }
                else {
                    pst=con.prepareStatement("INSERT INTO `exam_system`.`users` (`user_id`, `user_name`, `user_type`, `user_password`) VALUES (?, ?, ?, ?);");
                    pst.setInt(1,userInfo.getId());
                    pst.setString(2,userInfo.getUserName());
                    pst.setString(3,userInfo.getUserType());
                    pst.setString(4,userInfo.getUserPassword());
                    pst.execute();
                    userInfo.setResult(0);
                    System.out.println("id为"+userInfo.id+"的用户注册成功");
                }
                send.writeInt(-1);
                out.writeObject(userInfo);
                send.flush();
                out.flush();
            }
            //添加题目
            if (command==2){
                Question question=(Question) obj;
                String sql="INSERT INTO `exam_system`.`questions` (`question_text`, `question_picture`, `A`, `B`, `C`, `D`, `question_answer`,`test`,`question_pic`) VALUES (?, ?, ?, ?, ?, ?, ?,?,?);";
                pst_=con.prepareStatement(sql);
                pst_.setString(1,question.getText());
                pst_.setString(2,question.getImage_url());
                pst_.setString(3, question.getA());
                pst_.setString(4,question.getB());
                pst_.setString(5,question.getC());
                pst_.setString(6,question.getD());
                pst_.setString(7, question.getAnswer());
                pst_.setString(8, question.getTable());
                SerialBlob photo = new SerialBlob(question.pic_data);
                pst_.setBlob(9,photo);
                pst_.execute();
                System.out.println("题目："+question.getText()+"添加成功");
                send.writeInt(-1);
                send.flush();
            }
            //刷新数据
            if (command==3){
                pst_=con.prepareStatement("SELECT * FROM `questions`;");
                rs=pst_.executeQuery();
                int i=0;
                while (rs.next()){
                    i++;
                }
                Question[] questions=new Question[i];
                rs=pst_.executeQuery();
                for(int j=0;j<i;j++){
                    rs.next();
                    questions[j]=new Question();
                    questions[j].setId(rs.getInt(1));
                    questions[j].setImage_url(rs.getString(3));
                    questions[j].setText(rs.getString(2));
                    questions[j].setA(rs.getString(4));
                    questions[j].setB(rs.getString(5));
                    questions[j].setC(rs.getString(6));
                    questions[j].setD(rs.getString(7));
                    questions[j].setAnswer(rs.getString(8));
                    questions[j].setTable(rs.getString(9));
                    Blob photo=rs.getBlob(10);
                    questions[j].setPic_data(photo.getBytes(1,photo.getBinaryStream().available()));
                }
                System.out.println("刷新数据或者页面初始化");
                send.writeInt(-1);
                out.writeObject(questions);
                send.flush();
                out.flush();
            }
            //删除题目
            if (command==4){
                Question question=(Question) obj;
                pst_=con.prepareStatement("DELETE FROM `exam_system`.`questions` WHERE `question_id`=?;");
                pst_.setInt(1,question.getId());
                pst_.execute();
                System.out.println("id为"+question.id+"的题目删除成功");
                send.writeInt(-1);
                send.flush();
            }
            //更新题目
            if (command==5){
                Question question=(Question) obj;
                pst_=con.prepareStatement("UPDATE `exam_system`.`questions` SET `question_id`=?, `question_text`=?, `question_picture`=?, `A`=?, `B`=?, `C`=?, `D`=?, `question_answer`=?,`test`=?, `question_pic`=? WHERE (`question_id`=?);");
                pst_.setInt(1,question.getId());
                pst_.setString(2,question.getText());
                pst_.setString(3,question.getImage_url());
                pst_.setString(4, question.getA());
                pst_.setString(5,question.getB());
                pst_.setString(6,question.getC());
                pst_.setString(7, question.getD());
                pst_.setString(8, question.getAnswer());
                pst_.setInt(11,question.getId());
                SerialBlob photo = new SerialBlob(question.getPic_data());
                pst_.setString(9,question.getTable());
                pst_.setBlob(10,photo);
                pst_.execute();
                System.out.println("id为"+question.id+"的题目更新成功");
                send.writeInt(-1);
                send.flush();
            }
            //用户保存分数
            if (command==6){
                UserInfoTest userInfo= (UserInfoTest) obj;
                pst=con.prepareStatement("INSERT INTO `exam_system`.`tests` (`user_id`, `marks`) VALUES (?,?);");
                pst.setInt(1,userInfo.getId());
                pst.setInt(2,userInfo.getMark());
                pst.execute();
                System.out.println("id为"+userInfo.id+"的用户保存分数成功，考试分数为:"+userInfo.getMark());
                send.writeInt(-1);
                send.flush();
            }
            //随机抽题，题库中有10道题就抽10道，没有就原题库随机打乱
            if (command==7){
                pst=con.prepareStatement("SELECT question_id from questions");
                rs=pst.executeQuery();
                int n=0;
                while (rs.next()){
                    n++;
                }
                int[] questions_id=new int[n];
                n=0;
                rs=pst.executeQuery();
                while (rs.next()){
                    questions_id[n]=rs.getInt(1);
                    n++;
                }
                int[] questions_id_selected=new int[10];
                int m=0;
                boolean flag;
                int t=10;
                if (n<10){
                    t=n;
                }
                while(m<t){
                    flag=false;
                    int tmp=questions_id[new Random().nextInt(n)];
                    for(int i=0;i<t;i++){
                        if (tmp==questions_id_selected[i]) {
                            flag=true;
                        }
                    }
                    if (flag){
                        continue;
                    }
                    questions_id_selected[m]=tmp;;
                    m++;
                }
                Question[] questions=new Question[t];
                pst_=con.prepareStatement("SELECT * FROM questions where question_id=?");
                for(int i=0;i<t;i++){
                    pst_.setInt(1,questions_id_selected[i]);
                    rs=pst_.executeQuery();
                    if (rs.next()){
                        Question question1=new Question();
                        question1.setId(rs.getInt(1));
                        question1.setText(rs.getString(2));
                        question1.setImage_url(rs.getString(3));
                        question1.setA(rs.getString(4));
                        question1.setB(rs.getString(5));
                        question1.setC(rs.getString(6));
                        question1.setD(rs.getString(7));
                        question1.setAnswer(rs.getString(8));
                        Blob photo=rs.getBlob(10);
                        question1.setPic_data(photo.getBytes(1,photo.getBinaryStream().available()));
                        questions[i]=question1;
                    }
                }
                System.out.println("随机抽了"+t+"道题");
                send.writeInt(-1);
                out.writeObject(questions);
                send.flush();
                out.flush();
            }
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println("删除数据产生的错误，客户端保留了数据，再进行操作导致找不到id");
            e.printStackTrace();
        }finally {
            if (pst!=null){
                try {
                    pst.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (pst_!=null){
                try {
                    pst_.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (con!=null){
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}

