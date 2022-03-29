import java.util.*;

public class Register{

    static void re(){
       
        Scanner a = new Scanner(System.in); 
        System.out.println("email address:");
        String email=a.nextLine();
        System.out.println("password:");
        String password=a.nextLine();
        a.close();
        try {
            //some insert with database stuff
            User u= new User(email,password);
            
            u=null;
            System.gc();
        } catch (Exception e) {
            //insert fails
        }
    
    }

}