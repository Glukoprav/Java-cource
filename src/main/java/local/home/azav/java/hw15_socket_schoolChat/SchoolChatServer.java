package local.home.azav.java.hw15_socket_schoolChat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SchoolChatServer {
    private volatile static int numberServer = 0;
    private final static int PORT = 21212;
    private final Object object = new Object();
    // Список клиентов c из сокетами и входным/выходным потоком
    private final CopyOnWriteArrayList<ClientsInfo> listClientsInfo = new CopyOnWriteArrayList<>();

    private SchoolChatServer() {
        synchronized (object) {
            numberServer++;
        }
    }

    private void workServer() throws IOException {
        // Пул потоков для клиентов
        ExecutorService executorService = Executors.newFixedThreadPool(12);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                String login;
                // Ждем подсоединения нового клиента
                Socket socket = serverSocket.accept();
                System.out.println(String.format("Коннект с клиентом, client ip %s port %s", socket.getInetAddress(), socket.getPort()));
                DataInputStream inStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
                login = inStream.readUTF();
                // Собираем информацию о новом клиентском подключении
                ClientsInfo clientsInfo = new ClientsInfo(socket, inStream, outStream, login);
                listClientsInfo.add(clientsInfo);
                // Запускаем в отдельном потоке
                executorService.execute(clientsInfo);
                if (false) {
                    System.out.println("Сервер остановлен! 1");
                    break;
                }
            }
        }
        executorService.shutdown();
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

    private class ClientsInfo implements Runnable {
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

        @Override
        public void run() {
            try {
                outputStream.writeUTF("[SYSTEM] Сервер приветствует " + login);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}