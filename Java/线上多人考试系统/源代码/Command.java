import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Command implements Serializable {
    int command;

    transient Socket mysocket = null;
    transient DataInputStream in = null;
    transient DataOutputStream out = null;
    transient ObjectOutputStream objOP=null;
    transient ObjectInputStream objIS=null;
    transient Thread threadRead;

    public Command() {

    }

    public Command(int command) {
        this.command = command;
    }

    public void command(){
        connect();
        threadRead=new Thread(new ThreadRead(in,objIS));
        threadRead.start();
        try {
            out.writeInt(-1);
            objOP.writeObject(this);
            out.flush();
            objOP.flush();
            while (ThreadRead.isFinish){
                Thread.sleep(1000);
            }
            ThreadRead.isFinish=true;
            in.close();
            out.close();
            objOP.close();
            objIS.close();
            mysocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void connect(){
        try {
            mysocket = new Socket();//创建一个不带参数的套接字对象
            String IP = "127.0.0.1";
            int port = 2021;
            if (mysocket.isConnected()) {//判断是否已经与服务器连接
            } else {
                InetAddress address = InetAddress.getByName(IP);//获取ip地址
                InetSocketAddress socketAddress = new InetSocketAddress(address, port);//用于与mysocket通信
                mysocket.connect(socketAddress);//mysocket与由socketAddress指定地址的服务器端的套接字建立连接
                in = new DataInputStream(mysocket.getInputStream());
                out = new DataOutputStream(mysocket.getOutputStream());
                objOP=new ObjectOutputStream(mysocket.getOutputStream());
                objIS=new ObjectInputStream(mysocket.getInputStream());
            }
        } catch (Exception e) {
            System.out.println("服务器已断开" + e);
        }
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public int getCommand(){
        return command;
    };
}
class ThreadRead implements Runnable{
    DataInputStream isNull;
    ObjectInputStream in;
    static Object obj=null;
    static boolean isFinish=true;
    public ThreadRead(DataInputStream isNull,ObjectInputStream in) {
        this.isNull=isNull;
        this.in = in;
    }
    @Override
    public void run() {
        while (isFinish) {
            try {
                isNull.readInt();//读入服务器端输出流的内容
                try {
                    isFinish=false;
                    obj=in.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                break;
            }
        }
    }
}
