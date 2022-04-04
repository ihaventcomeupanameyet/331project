import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static Map<String,ChatRoom> map=new HashMap<>();

    public static SimpleDateFormat current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
    try {
        String url="sunfire.csci.viu.ca";
        String username="";
        String Password="";

        Connection conn=DriverManager.getConnection(url,username,Password);
        Statement statement=conn.createStatement();
        ResultSet r= statement.executeQuery("select cid,name from chatrooms");
        while (r.next()){
            String id=r.getString(1);
            String name=r.getString(2);
            int chatid=Integer.parseInt(id);
            ChatRoom a=new ChatRoom(chatid, name);
            PreparedStatement k=conn.prepareStatement("select email,username from users natural join memberof where cid=?");
            k.setString(1,id);
            ResultSet s= k.executeQuery();
            while (s.next()){
                User b=new User();
                b.setEmail(s.getString(1));
                b.setUserName(s.getString(2));
                a.addNewUser(b);
            }
            PreparedStatement l=conn.prepareStatement("select sendtime,email,text from chatrooms natural join message where cid=?");
            l.setString(1, id);
            s=l.executeQuery();
            while (s.next()){
                Message c=new Message();
                c.setSendTime(s.getString(1));
                c.setSenderEmail(s.getString(2));
                c.setText(s.getString(3));
                a.updateMessageList(c);
            }
            map.put(id, a);
        }
    } catch (Exception e) {
        //TODO: handle exception
        e.printStackTrace();
    }
        

    }
}
