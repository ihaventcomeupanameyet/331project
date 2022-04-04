import java.util.*;
import java.sql.*;

public class Register{

    
    static User re(Connection conn){
       
        Scanner a = new Scanner(System.in); 
        System.out.println("email address:");
        String email=a.nextLine();
        System.out.println("password:");
        String password=a.nextLine();
        User u= new User(email,password);
        try {
            //some insert with database stuff
            PreparedStatement statement=conn.prepareStatement("insert into users(email,username,password) values (?,null,?)");
            statement.setString(1,email);
            statement.setString(2, password);
            statement.executeQuery();
            System.out.println("your username:");
            String name=a.nextLine();
            PreparedStatement state=conn.prepareStatement("update users set name=? where email=?");
            state.setString(1,name);
            state.setString(2,email);
            state.executeQuery();
            u.setUserName(name);
            
        } catch (Exception e) {
            //insert fails
            e.printStackTrace();
        }
        a.close();
        return u;
    }

}