import java.text.SimpleDateFormat;
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

    public void listChatRoom(){
        System.out.println("Listing avaliable chat room: \n");
        List<ChatRoom> avaChatRoom= new ArrayList<ChatRoom>();

        //some data base application to populate the list

        for (int i=0; i<avaChatRoom.size(); i++){
            System.out.println("Chat Room ID: " + avaChatRoom.get(i).getChatRoomID()+"            " + "Chat Room Name: "+avaChatRoom.get(i).getName()+"\n");
        }
    }

    public void joinChatRoom(ChatRoom a){
        a.addNewUser(this);
        System.out.println("Updating list of chat room: \n");
        for (int i=0; i<chatRoomList.size(); i++){
            System.out.println("Chat Room ID: " + chatRoomList.get(i).getChatRoomID()+"            " + "Chat Room Name: "+chatRoomList.get(i).getName()+"\n");
        }
    }
    public void leaveChatRoom(ChatRoom a){
        a.removeUser(this);
        System.out.println("Updating list of chat room: \n");
        for (int i=0; i<chatRoomList.size(); i++){
            System.out.println("Chat Room ID: " + chatRoomList.get(i).getChatRoomID()+"            " + "Chat Room Name: "+chatRoomList.get(i).getName()+"\n");
        }
    }
    public void createChatRoom(){
        Scanner a = new Scanner(System.in);
        System.out.println("Please enter name of the chat room: ");
        String name = a.nextLine();
        ChatRoom b= new ChatRoom(this, name);
        joinChatRoom(b);
        a.close();
    }
    public void sendMessage(ChatRoom chatRoom){
        Scanner a = new Scanner(System.in);
        String text = a.nextLine();
        SimpleDateFormat current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date b= new Date();
        String date= current.format(b);
        Message m=new Message(chatRoom.getChatRoomID(), this.getEmail(), date, text);
        chatRoom.updateMessageList(m);
        chatRoom.broadcast();
        a.close();
    }


    /**
     * @return List<ChatRoom> return the chatRoomList
     */
    public List<ChatRoom> getChatRoomList() {
        return chatRoomList;
    }



}
