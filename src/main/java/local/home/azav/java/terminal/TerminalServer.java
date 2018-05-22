package local.home.azav.java.terminal;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

/**
 * Операции с суммами по аккаунтам
 */
public class TerminalServer {
    // Хранение сумм по аккаунтам
    private Map<Integer, BigDecimal> listAccSumm;

    public TerminalServer() {
        listAccSumm = new TreeMap<>();

        // Составляем список аккаунтов для теста
        listAccSumm.put(12345, BigDecimal.valueOf(20.55));
        listAccSumm.put(12346, BigDecimal.valueOf(222.4));
        listAccSumm.put(12342, BigDecimal.valueOf(0.44));
        listAccSumm.put(12340, BigDecimal.valueOf(50.0));
        System.out.println("Сервер ожил");
    }

    /**
     * Проверка наличия аккаунта
     */
    protected boolean checkAccount(int account) throws NoSuchFieldException {
        if (listAccSumm.containsKey(account)) {
            return true;
        } else {
            throw new NoSuchFieldException();
        }
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
