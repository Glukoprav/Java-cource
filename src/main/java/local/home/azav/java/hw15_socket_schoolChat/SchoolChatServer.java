package local.home.azav.java.hw15_socket_schoolChat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SchoolChatServer {
    private final static int PORT = 21212;


    public void startServer() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(PORT)
        ) {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                InputStream inStream = socket.getInputStream();
                OutputStream outStream = socket.getOutputStream();
                ChatServerTread chatServer = new ChatServerTread(socket, new DataInputStream(inStream), new DataOutputStream(outStream));
                // Запускаем поток сервера
                Thread thread = new Thread(chatServer);
                thread.start();
            }
        }
    }


    // Запуск сервера
    public static void main(String[] args) {
        try {
            SchoolChatServer server = new SchoolChatServer();
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
