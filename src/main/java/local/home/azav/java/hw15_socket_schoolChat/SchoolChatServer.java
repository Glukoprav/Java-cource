package local.home.azav.java.hw15_socket_schoolChat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SchoolChatServer {
    private volatile static int numberServer = 0;
//    private final Socket socket;
//    private final DataInputStream inputStream;
//    private final DataOutputStream outputStream;
    private final static int PORT = 21212;

    // Socket socket, DataInputStream inputStream, DataOutputStream outputStream
    SchoolChatServer() {
//        this.socket = socket;
//        this.inputStream = inputStream;
//        this.outputStream = outputStream;
        numberServer++;
    }

//    public void startServer() throws IOException {
//
//        try (ServerSocket serverSocket = new ServerSocket(PORT)
//        ) {
//            Socket socket = serverSocket.accept();
//            InputStream inStream = socket.getInputStream();
//            OutputStream outStream = socket.getOutputStream();
    // Запускаем поток сервера
//            Thread threadServer = new Thread(chatServer);
//            threadServer.start();

    // Запуск сервера
    public static void main(String[] args) throws IOException {
        if (SchoolChatServer.numberServer > 0) {
            System.out.println("Чат-Сервер уже запущен! Лишний процесс прерываем.");
            System.exit(0);
        }
        String strCommand = "exit";
        SchoolChatServer chatServer = new SchoolChatServer();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println(String.format("Коннект с клиентом, client ip %s port %s", socket.getInetAddress(), socket.getPort()));
                if ("exit".equals(strCommand)) {
                    System.out.println("Сервер остановлен! 1");
                    break;
                }
            }
        }
        System.out.println("Сервер остановлен! 2");
    }
}