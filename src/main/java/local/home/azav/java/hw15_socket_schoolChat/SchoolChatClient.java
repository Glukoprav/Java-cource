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
    private static int numberClient = 0;

    public SchoolChatClient(Socket socket, DataInputStream inputStream, DataOutputStream outputStream) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        numberClient++;
    }

//    private void startClient() {
//
////            Thread treadClient = new Thread(chatClient);
////            treadClient.start();
//
//    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(ADDRESS, PORT);
             InputStream inStream = socket.getInputStream();
             OutputStream outStream = socket.getOutputStream();) {
            SchoolChatClient clientChat = new SchoolChatClient(socket, new DataInputStream(inStream), new DataOutputStream(outStream));
            //clientChat.startClient();
            System.out.println(String.format("Коннект с сервером, client ip %s port %s", socket.getInetAddress(), socket.getPort()));
            System.out.println("Стартовал клиент: " + numberClient);
            System.out.println("Введите логин: ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
