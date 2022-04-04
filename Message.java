
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

    public Message(){
        
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



    /**
     * @param chatRoomID the chatRoomID to set
     */
    public void setChatRoomID(int chatRoomID) {
        this.chatRoomID = chatRoomID;
    }

    /**
     * @param senderEmail the senderEmail to set
     */
    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    /**
     * @param sendTime the sendTime to set
     */
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

}
