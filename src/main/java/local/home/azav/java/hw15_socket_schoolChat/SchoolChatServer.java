package local.home.azav.java.hw15_socket_schoolChat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class SchoolChatServer {
    private volatile static int numberServer = 0;
    private final static int PORT = 21212;
    // Список клиентов c из сокетами и входным/выходным потоком
    private final CopyOnWriteArrayList<ClientsInfo> listClientsInfo = new CopyOnWriteArrayList<>();

    private SchoolChatServer() {
        numberServer++;
    }

    private void workServer() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                String login;
                Socket socket = serverSocket.accept();
                System.out.println(String.format("Коннект с клиентом, client ip %s port %s", socket.getInetAddress(), socket.getPort()));
                DataInputStream inStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
                login = inStream.readUTF();
                ClientsInfo clientsInfo = new ClientsInfo(socket, inStream, outStream, login);
                System.out.println("clientsInfo> " + clientsInfo.toString());
                //outStream.writeUTF("Пришел логин: " + );
                listClientsInfo.add(clientsInfo);
                if (false) {
                    System.out.println("Сервер остановлен! 1");
                    break;
                }
            }
        }
    }

    // Запуск сервера
    public static void main(String[] args) throws IOException {
        if (SchoolChatServer.numberServer > 0) {
            System.out.println("Чат-Сервер уже запущен! Лишний процесс прерываем.");
            System.exit(0);
        }
        SchoolChatServer chatServer = new SchoolChatServer();
        chatServer.workServer();
    }

    private class ClientsInfo {
        private final Socket socket;
        private final DataInputStream inputStream;
        private final DataOutputStream outputStream;
        private final String login;

        public ClientsInfo(Socket socket, DataInputStream inputStream, DataOutputStream outputStream, String login) {
            this.socket = socket;
            this.inputStream = inputStream;
            this.outputStream = outputStream;
            this.login = login;
        }
    }
}