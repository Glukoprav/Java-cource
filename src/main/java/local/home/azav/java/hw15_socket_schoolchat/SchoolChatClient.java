package local.home.azav.java.hw15_socket_schoolchat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс клиентского приложения SchoolChat
 */
public class SchoolChatClient {
    private static final Logger LOG = Logger.getLogger(SchoolChatClient.class.getName());
    private static final String ADDRESS = "localhost";
    private static final int PORT = 21212;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private String loginClient;

    private SchoolChatClient(DataInputStream inputStream, DataOutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(ADDRESS, PORT);
             InputStream inStream = socket.getInputStream();
             OutputStream outStream = socket.getOutputStream()) {
            SchoolChatClient clientChat = new SchoolChatClient(new DataInputStream(inStream), new DataOutputStream(outStream));
            LOG.log(Level.INFO, "Коннект с сервером, ip: {0}, port: {1}", new Object[]{socket.getInetAddress(), socket.getPort()});
            // Поднимаем поток сообщений от сервера
            clientChat.startMessageFromServer();
            // Cчитываем логин
            Scanner scanner = new Scanner(System.in);
            clientChat.loginClient = clientChat.inputLogin(scanner);
            // Засылаем логин на сервер
            clientChat.outputStream.writeUTF(clientChat.loginClient);
            clientChat.outputStream.flush();
            // Немного подождем, чтоб успел подняться поток ответов
            Thread.sleep(500);
            // Сидим в цикле и отправляем сообщения серверу, пока не введем "exit"
            clientChat.outputMessage(scanner);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }  catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void startMessageFromServer() {
        Thread threadMess = new Thread(new MessageFromServer());
        threadMess.start();
    }

    private String inputLogin(Scanner scanner) {
        LOG.log(Level.INFO,"Введите логин: ");
        String login = scanner.next();
        while (login.length() == 0 || login.equals("\n") || login.equals(" ")
                || login.contains("[") || login.contains("]")) {
            LOG.log(Level.INFO,"Логин не может быть пустым и содержать символы \'[]\'");
            login = scanner.next();
        }
        return login;
    }

    // Отправка сообщений на сервер, пока не введем exit
    private void outputMessage(Scanner scanner) throws IOException {
        String message = "";
        do {
            LOG.log(Level.INFO,"[ {0} ] > ",loginClient);
            message = scanner.nextLine();
            if (!"exit".equals(message) || !" ".equals(message) || !"\n".equals(message) || !"\0".equals(message) ||
                    !" \n".equals(message) || message.length() != 0) {
                outputStream.writeUTF(message);
                outputStream.flush();
            }
        } while (!"exit".equals(message));
    }

    // Поток для вывода сообщений от сервера
    private class MessageFromServer implements Runnable {
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    LOG.log(Level.INFO, "Пришло: {0}", inputStream.readUTF());
                }
            } catch (IOException e) {
                Thread.currentThread().interrupt();
                LOG.log(Level.SEVERE, "Exception: ", e);
            }
        }
    }
}
