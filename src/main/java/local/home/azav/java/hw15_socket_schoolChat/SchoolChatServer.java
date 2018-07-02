package local.home.azav.java.hw15_socket_schoolChat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SchoolChatServer {
    private final static int PORT = 21212;


    public void startServer() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(PORT)
        ) {
            Socket socket = serverSocket.accept();
            InputStream inStream = socket.getInputStream();
            OutputStream outStream = socket.getOutputStream();
            ChatServerThread chatServer = new ChatServerThread(socket, new DataInputStream(inStream), new DataOutputStream(outStream));
            // Запускаем поток сервера
            Thread threadServer = new Thread(chatServer);
            threadServer.start();
        }
    }

    // Запуск сервера
    public static void main(String[] args) {
        try {
            SchoolChatServer serverChat = new SchoolChatServer();
            serverChat.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
