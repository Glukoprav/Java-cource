package local.home.azav.java.hw15_socket_schoolChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

class ChatClientThread implements Runnable {
    private final static String ADDRESS = "localhost";
    private final static int PORT = 21212;
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private static int numberClient = 0;

    public ChatClientThread(Socket socket, DataInputStream inputStream, DataOutputStream outputStream) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        numberClient++;
    }

    @Override
    public void run() {
        System.out.println(String.format("Сonnect accepted, client ip %s port %s", socket.getInetAddress(), socket.getPort()));
        System.out.println("Стартовал клиент: " + numberClient);
        System.out.println("Введите логин: ");
    }
}
