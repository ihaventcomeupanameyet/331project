
public class Message {
    private int chatRoomID;
    private String senderEmail;
    private String sendTime;
    private String text;

    
    public Message(int CRID, String email, String a, String Text){
        chatRoomID=CRID;
        senderEmail=email;
        sendTime=a;
        text=Text;
    }

    /**
     * @return String return the chatRoomID
     */
    public int getChatRoomID() {
        return chatRoomID;
    }



    /**
     * @return String return the senderEmail
     */
    public String getSenderEmail() {
        return senderEmail;
    }

    /**
     * @return Date return the sendTime
     */
    public String getSendTime() {
        return sendTime;
    }


    /**
     * @return String return the text
     */
    public String getText() {
        return text;
    }


}
