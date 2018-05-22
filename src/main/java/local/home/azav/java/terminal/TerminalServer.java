package local.home.azav.java.terminal;

/**
 * Операции с суммами по аккаунтам
 */
public class TerminalServer {

    public TerminalServer() {
        System.out.println("Сервер ожил");
    }

    /**
     * Ядро запуска терминала
     */
    public static void main(String[] args) {
        // поднимаем сервер
        TerminalServer terSer = new TerminalServer();
        // поднимаем валидатор
        PinValidator pinVal = new PinValidator();
        // поднимаем терминал
        TerminalImpl terImpl = new TerminalImpl(terSer, pinVal);
        // Запускаем обаботчик операций
        terImpl.operationHandler();
    }
}
