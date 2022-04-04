import java.sql.*;

import java.util.*;


public class User implements Interface{

    private String userName;
    private String email;
    private String password;
    private List<ChatRoom> chatRoomList;

    public User(String Email,String Password){
        email=Email;
        password=Password;
        chatRoomList=new ArrayList<ChatRoom>();
    }

    public User(){
        
    }

    public void addNewChatRoom(ChatRoom A){
        chatRoomList.add(A);
    }

    /**
     * @return String return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

   

    /**
     * @return String return the password
     */
    public String getPassword() {
        
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public void listJoinedChatRoom(){
        System.out.println("Listing chat room: \n");
        for (int i=0; i<chatRoomList.size(); i++){
            System.out.println("Chat Room ID: " + chatRoomList.get(i).getChatRoomID()+"            " + "Chat Room Name: "+chatRoomList.get(i).getName()+"\n");
        }
    }

    public void listChatRoom(Connection c){
        System.out.println("Listing avaliable chat room: \n");
        try {
            PreparedStatement statement=c.prepareStatement("select cid,name from chatrooms where cid not in(select cid from memberof natural join chatrooms where email=?)");
            statement.setString(1,this.email);
            ResultSet a= statement.executeQuery();
            while(a.next()){
                System.out.println("Chat Room ID: " + a.getString(1)+"            " + "Chat Room Name: "+a.getString(2)+"\n");
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        //some data base application to populate the list

        
    }

    public void joinChatRoom(Connection c,int chatid){
        ChatRoom a=Main.map.get(Integer.toString(chatid));
        a.addNewUser(this);
        System.out.println("Updating list of chat room: \n");
        for (int i=0; i<chatRoomList.size(); i++){
            System.out.println("Chat Room ID: " + chatRoomList.get(i).getChatRoomID()+"            " + "Chat Room Name: "+chatRoomList.get(i).getName()+"\n");
        }
        try {
            PreparedStatement statement=c.prepareStatement("insert into memberof(email,cid) values (?,?)");
            statement.setString(1, this.email);
            statement.setString(2, Integer.toString(chatid));
            statement.executeUpdate();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        Main.map.put(Integer.toString(chatid),a);
    }
    public void leaveChatRoom(Connection c,int chatid){
        ChatRoom a=Main.map.get(Integer.toString(chatid));
        a.removeUser(this);
        System.out.println("Updating list of chat room: \n");
        for (int i=0; i<chatRoomList.size(); i++){
            System.out.println("Chat Room ID: " + chatRoomList.get(i).getChatRoomID()+"            " + "Chat Room Name: "+chatRoomList.get(i).getName()+"\n");
        }
        try {
            PreparedStatement statement=c.prepareStatement("Delete from memberof where email=? and cid=?");
            statement.setString(1, this.email);
            statement.setString(2, Integer.toString(chatid));
            statement.executeUpdate();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        Main.map.put(Integer.toString(chatid),a);
    }
    public void createChatRoom(Connection c){
        Scanner a = new Scanner(System.in);
        System.out.println("Please enter name of the chat room: ");
        String name = a.nextLine();
        ChatRoom b= new ChatRoom(this, name);
        joinChatRoom(c,b.getChatRoomID());
        try {
            PreparedStatement statement=c.prepareStatement("insert into chatrooms(cid,name) values (?,?)");
            statement.setString(1, Integer.toString(b.getChatRoomID()));
            statement.setString(2, name);
            statement.executeUpdate();
            PreparedStatement state=c.prepareStatement("insert into memberof(email,cid) values (?,?)");
            state.setString(1, this.email);
            state.setString(2, Integer.toString(b.getChatRoomID()));
            state.executeUpdate();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        Main.map.put(Integer.toString(b.getChatRoomID()),b);
        a.close();
    }
    public void sendMessage(Connection c,int chatid){
        ChatRoom chatroom=Main.map.get(Integer.toString(chatid));
        Scanner a = new Scanner(System.in);
        String text = a.nextLine();
        java.util.Date b= new java.util.Date();
        String date= Main.current.format(b);
        Message m=new Message(chatid, this.getEmail(), date, text);
        chatroom.updateMessageList(m);
        chatroom.broadcast();
        try {
            PreparedStatement statement=c.prepareStatement("insert into message(email,cid,sendtime,text) values (?,?,?,?)");
            statement.setString(1, this.email);
            statement.setString(2, Integer.toString(chatid));
            statement.setString(3,date);
            statement.setString(4, text);
            statement.executeUpdate();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        Main.map.put(Integer.toString(chatid),chatroom);
        a.close();
    }


    /**
     * @return List<ChatRoom> return the chatRoomList
     */
    public List<ChatRoom> getChatRoomList() {
        return chatRoomList;
    }

 


    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

   
}
