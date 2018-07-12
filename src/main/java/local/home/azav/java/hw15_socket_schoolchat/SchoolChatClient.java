package local.home.azav.java.hw15_socket_schoolchat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс клиентского приложения SchoolChat
 */
public class SchoolChatClient {
    private volatile static int numberClient = 0;
    private final static String ADDRESS = "localhost";
    private final static int PORT = 21212;
    private final Object object = new Object();
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private String loginClient;

    public SchoolChatClient(Socket socket, DataInputStream inputStream, DataOutputStream outputStream) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        synchronized (object) {
            numberClient++;
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(ADDRESS, PORT);
             InputStream inStream = socket.getInputStream();
             OutputStream outStream = socket.getOutputStream()) {
            SchoolChatClient clientChat = new SchoolChatClient(socket, new DataInputStream(inStream), new DataOutputStream(outStream));
            System.out.println(String.format("Коннект с сервером, ip: %s, port: %s", socket.getInetAddress(), socket.getPort()));
            // Поднимаем поток сообщений от сервера
            clientChat.startMessageFromServer();
            // Cчитываем логин
            Scanner scanner = new Scanner(System.in);
            clientChat.loginClient = clientChat.inputLogin(scanner);
            // Засылаем логин на сервер
            clientChat.outputStream.writeUTF(clientChat.loginClient);
            clientChat.outputStream.flush();
            // Немного подождем, чтоб успел подняться поток ответов
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Сидим в цикле и отправляем сообщения серверу, пока не введем "exit"
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

    // Отправка сообщений на сервер
    private void outputMessage(SchoolChatClient clientChat, Scanner scanner) throws IOException {
        String message = "";
        do {
            System.out.print("[" + loginClient + "] >");
            message = scanner.nextLine();
            /*(!" ".equals(message) || !"\n".equals(message) || !"\0".equals(message) ||
                    !"exit".equals(message) || !" \n".equals(message) || message.length() != 0)*/
            if (!"exit".equals(message) || !" ".equals(message) || !"\n".equals(message) || !"\0".equals(message) ||
                    !" \n".equals(message) || message.length() != 0) {
                outputStream.writeUTF(/*"[" + loginClient + "]" + */message);
                outputStream.flush();
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
