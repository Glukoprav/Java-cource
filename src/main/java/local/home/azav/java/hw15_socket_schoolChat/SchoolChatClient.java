package local.home.azav.java.hw15_socket_schoolChat;

import java.io.*;
import java.net.Socket;

/**
 * Класс клиентского приложения SchoolChat
 */
public class SchoolChatClient {
    private final static String ADDRESS = "localhost";
    private final static int PORT = 21212;

    private void startClient() {
        try (Socket socket = new Socket(ADDRESS, PORT);
             InputStream inStream = socket.getInputStream();
             OutputStream outStream = socket.getOutputStream();) {
            ChatClientThread chatClient = new ChatClientThread(socket, new DataInputStream(inStream), new DataOutputStream(outStream));
            Thread treadClient = new Thread(chatClient);
            treadClient.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SchoolChatClient clientChat = new SchoolChatClient();
        clientChat.startClient();
    }
}
