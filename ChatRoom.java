import java.util.*;

public class ChatRoom {
    private int chatRoomID;
    private String name;
    private List<User> userList;
    private List<Message> messageList;

    
    public ChatRoom(User a,String Name){
        name=Name;
        Random random=new Random();
        chatRoomID= 100000000 + random.nextInt(900000000);

    }
    /**
     * @return String return the chatRoomID
     */
    public int getChatRoomID() {
        return chatRoomID;
    }

    

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return List<User> return the userList
     */
    public List<User> getUserList() {
        return userList;
    }


    public void addNewUser(User a) {
        this.userList.add(a);
    }

    public boolean removeUser(User a){
        
        for (int i=0; i<userList.size(); i++){
            if (userList.get(i).getEmail()==a.getEmail())
            {
                userList.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * @return List<Message> return the messageList
     */
    public List<Message> getMessageList() {
        return messageList;
    }

    /**
     * @param messageList the messageList to set
     */
    public void updateMessageList(Message a) {
        this.messageList.add(a);
    }

    public void broadcast(){
        
    }


}
