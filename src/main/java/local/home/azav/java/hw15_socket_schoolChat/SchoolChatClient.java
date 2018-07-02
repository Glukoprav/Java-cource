package local.home.azav.java.hw15_socket_schoolChat;

import java.io.*;
import java.net.Socket;

/**
 * Класс клиентского приложения SchoolChat
 */
public class SchoolChatClient {
    private final static String ADDRESS = "localhost";
    private final static int PORT = 21212;
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;

    public SchoolChatClient(Socket socket, DataInputStream inputStream, DataOutputStream outputStream) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    private void startClient() {

    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(ADDRESS, PORT);
             InputStream inStream = socket.getInputStream();
             OutputStream outStream = socket.getOutputStream();) {
            new SchoolChatClient(socket, new DataInputStream(inStream), new DataOutputStream(outStream)).startClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
