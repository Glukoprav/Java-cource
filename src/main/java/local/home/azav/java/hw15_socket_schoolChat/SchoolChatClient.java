package local.home.azav.java.hw15_socket_schoolChat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс клиентского приложения SchoolChat
 */
public class SchoolChatClient {
    private static int numberClient = 0;
    private final static String ADDRESS = "localhost";
    private final static int PORT = 21212;
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private String loginClient;

    public SchoolChatClient(Socket socket, DataInputStream inputStream, DataOutputStream outputStream) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        numberClient++;
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(ADDRESS, PORT);
             InputStream inStream = socket.getInputStream();
             OutputStream outStream = socket.getOutputStream();) {
            SchoolChatClient clientChat = new SchoolChatClient(socket, new DataInputStream(inStream), new DataOutputStream(outStream));
            System.out.println(String.format("Коннект с сервером, client ip %s port %s", socket.getInetAddress(), socket.getPort()));
            System.out.println("Стартовал клиент: " + SchoolChatClient.numberClient);
            // Поднимаем поток сообщений от сервера
            clientChat.startMessageFromServer();
            // Cчитываем логин
            Scanner scanner = new Scanner(System.in);
            clientChat.loginClient = clientChat.inputLogin(scanner);
            // Засылаем логин на сервер
            clientChat.outputStream.writeUTF(clientChat.loginClient);
            clientChat.outputStream.flush();
            // Отправляем сообщения серверу, пока не введем "exit"
            clientChat.outputMessage(clientChat, scanner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startMessageFromServer() {
        Thread threadMess = new Thread(new MessageFromServer());
        threadMess.start();
    }

    private String inputLogin(Scanner scanner) {
        System.out.print("Введите логин: ");
        String login = scanner.next();
        while (login.length() == 0 || login.equals("\n") || login.equals(" ")
                || login.contains("[") || login.contains("]")) {
            System.out.println("Логин не может быть пустым и содержать символы \'[]\'");
            login = scanner.next();
        }
        return login;
    }

    private void outputMessage(SchoolChatClient clientChat, Scanner scanner) throws IOException {
        String message = "";
        do {
            System.out.println("[" + clientChat.loginClient + "] >");
            message = scanner.nextLine();
            if (!" ".equals(message) || !"\n".equals(message) || !"\0".equals(message) ||
                    !"exit".equals(message) || !" \n".equals(message) || message.length() != 0) {
                clientChat.outputStream.writeUTF("[" + clientChat.loginClient + "]" + message);
                clientChat.outputStream.flush();
            }
        } while (!"exit".equals(message));
    }

    // Поток для вывода сообщений от сервера
    private class MessageFromServer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                    System.out.println(inputStream.readUTF());
                }
            } catch (IOException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}
