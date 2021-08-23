package UJS_Bus_GPS;

import net.sf.json.JSONObject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;


@WebServlet(name = "UJS_Bus_GPS.Save_GPS_Data", value = "/UJS_Bus_GPS/Save_GPS_Data")
public class Save_GPS_Data extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer writer=response.getWriter();

        String url="jdbc:mysql://1.116.120.159:3306/ujs_bus";
        String user="ujs_bus";
        String password="20011218zyf";
        String sql="select * from `gps_data`";
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(url,user,password);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while (rs.next()){
                int id=rs.getInt(1);
                String gps_id=rs.getString(2);
                int time=rs.getInt(3);
                double longitude=rs.getDouble(4);
                double latitude=rs.getDouble(5);
                double altitude=rs.getDouble(6);
                int confirm=rs.getInt(7);
                writer.write(String.format("id:%d\tgps_id:%s\ttime:%d\tlongitude:%f\tlatitude:%f\taltitude:%f\tconfirm:%d\n",id,gps_id,time,longitude,latitude,altitude,confirm));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (pst!=null){
                try {
                    pst.close();
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer writer=response.getWriter();

        BufferedReader reader=request.getReader();
        String line;
        StringBuffer jb = new StringBuffer();
        while ((line = reader.readLine()) != null){
            jb.append(line);
        }

        JSONObject json= JSONObject.fromObject(jb.toString()).getJSONObject("notify_data").getJSONObject("body").getJSONObject("content");
        String gps_id=json.getString("id");
        int long_int=json.getInt("Lo_i");
        int long_flo=json.getInt("Lo_f");
        double longitude=long_int+long_flo*Math.pow(0.1,String.valueOf(long_flo).length());
        int lat_int=json.getInt("Lat_i");
        int lat_flo=json.getInt("Lat_f");
        double latitude=lat_int+lat_flo*Math.pow(0.1,String.valueOf(lat_flo).length());
        int alt_int=json.getInt("Alt_i");
        int alt_flo=json.getInt("Alt_f");
        double altitude=alt_int+alt_flo*Math.pow(0.1,String.valueOf(alt_flo).length());
        int confirm=json.getInt("Con");
        int hour = json.getInt("hour");
        int min = json.getInt("min");
        int sec = json.getInt("sec");
        int current_time=hour*3600+min*60+sec;

        String url="jdbc:mysql://1.116.120.159:3306/ujs_bus";
        String user="ujs_bus";
        String password="20011218zyf";
        String sql=String.format("INSERT INTO `gps_data` (`gps_id`, `current_time`, `longitude`, `latitude`, `altitude`,`confirm`) VALUES ('%s', %d, %f, %f, %f,%d)",gps_id,current_time,longitude,latitude,altitude,confirm);
        Connection con=null;
        PreparedStatement pst=null;
        boolean res;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(url,user,password);
            pst=con.prepareStatement(sql);
            res=pst.execute();
            if (res){
                writer.write("fail to insert mysql");
            }else {
                writer.write(String.format("insert mysql successfully{gps_id:%s,current_time:%d,longitude:%f,latitude:%f,altitude:%f,confirm:%d}",gps_id,current_time,longitude,latitude,altitude,confirm));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (pst!=null){
                try {
                    pst.close();
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
