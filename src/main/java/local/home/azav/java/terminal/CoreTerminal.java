package local.home.azav.java.terminal;

/** Ядро запуска терминала */
public class CoreTerminal {
    public static void main(String[] args) {
        // поднимаем сервер
        TerminalServer terSer = new TerminalServer();
        // поднимаем валидатор
        PinValidator pinVal = new PinValidator();
        // поднимаем терминал
        TerminalImpl terImpl = new TerminalImpl(terSer,pinVal);
        // Запускаем обаботчик операций
        terImpl.operationHandler();
    }
}
