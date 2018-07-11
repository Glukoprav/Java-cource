package local.home.azav.java.hw15_socket_schoolChat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Задание 15
 * <p>
 * Разработать клиент-серверное приложение SchoolChat
 * - вход в систему по логину - дополнительных проверок не нужно
 * - историю чатов хранить не нужно
 * - сервер понимает 2 команды: отправить сообщение пользователю и получить все сообщения текущему пользователю
 * - клиент при получении сообщения просто выводит информацию: [логин] message
 * - при входе нового пользователя все участники чата получают нотификацию - сообщение от пользователя SYSTEM
 * - формат сообщения от текущего пользователя к другому пользователю, пересылаемый через сервер:
 * $login message, где login - логин другого пользователя, т.е. $ - признак сообщения для пересылки
 */

public class SchoolChatServer {
    private volatile static int numberServer = 0;
    private final static int PORT = 21212;
    private final Object object = new Object();
    // Карта логинов клиентов c из сокетами и входным/выходным потоком
    private final ConcurrentMap<String, ClientsInfo> mapClients = new ConcurrentHashMap<>();

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
                mapClients.put(login,clientsInfo);
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
                // Сообщение всем от сервера - о подключении нового пользователя.
                for (String key : mapClients.keySet()) {
                    mapClients.get(key).sendMessage(serverMessage("Подключен новый клиент " + login));
                }
                String messageFull;
                // Получаем сообщения от клиента, пока не получим: exit
                do {
                    messageFull = inputStream.readUTF();
                    // Если сообщение не пустое, начинается с символа $ и его логин имеется среди списка клиентов, то оно для пересылки
                    if (messageFull.length() > 0 && messageFull.charAt(0) == '$') {
                        String strLoginExternal = extpactLoginExternal(messageFull);
                        if (mapClients.containsKey(strLoginExternal)) {
                            ClientsInfo clientInfo = mapClients.get(strLoginExternal);
                            String messSend = messageFull.substring(strLoginExternal.length() + 2, messageFull.length());
                            clientInfo.sendMessage(clientsMessage(login, messSend));
                        }
                    }
                } while (!"exit".equals(messageFull));
                // Сообщение всем от сервера - об отключении пользователя.
                for (String key : mapClients.keySet()) {
                    mapClients.get(key).sendMessage(serverMessage("Отключается клиент " + login));
                }
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String extpactLoginExternal(String message) {
            // Берем адресный логин из сообщения
            String[] strMess = message.split(" ");
            StringBuilder loginExternal = new StringBuilder(strMess[0]);
            loginExternal.deleteCharAt(0);
            return loginExternal.toString();
        }

        private String serverMessage(String message) {
            return "[SYSTEM] " + message;
        }

        private String clientsMessage(String login, String message) {
            return "[" + login + "] " + message;
        }

        private void sendMessage(String message) throws IOException {
            outputStream.writeUTF(message);
            outputStream.flush();
        }
    }
}