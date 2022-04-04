import java.sql.Connection;

public interface Interface {

	void listJoinedChatRoom();
    void listChatRoom(Connection c);
    void joinChatRoom(Connection c,int chatid);
    void leaveChatRoom(Connection c,int chatid);
    void createChatRoom(Connection c);
    void sendMessage(Connection c,int chatid);
}