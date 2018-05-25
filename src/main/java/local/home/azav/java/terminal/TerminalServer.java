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

    private TerminalServer() {
        listAccSumm = new TreeMap<>();

        // Составляем список аккаунтов с суммами для теста
        listAccSumm.put(12345, BigDecimal.valueOf(120.55));
        listAccSumm.put(12346, BigDecimal.valueOf(222.4));
        listAccSumm.put(12342, BigDecimal.valueOf(0.44));
        listAccSumm.put(12340, BigDecimal.valueOf(50.0));
    }

    /**
     * Проверка наличия аккаунта
     */
    boolean checkAccount(int account) throws NoSuchFieldException {
        if (listAccSumm.containsKey(account)) {
            return true;
        } else {
            throw new NoSuchFieldException();
        }
    }

    /**
     * Возвращает размер суммы для указанного аккаунта
     */
    BigDecimal sumAccount(int account) {
        return listAccSumm.get(account);
    }

    /**
     * Снять сумму с указанного аккаунта.
     * Если суммы не хватает, то возвращает исключение
     */
    void serverWithdrawMoney(BigDecimal sum, int account) throws NoSuchMethodException {
        if (listAccSumm.get(account).compareTo(sum) >= 0) {
            BigDecimal bdSum = listAccSumm.get(account).subtract(sum);
            listAccSumm.remove(account);
            listAccSumm.put(account,bdSum);
        } else {
            throw new NoSuchMethodException();
        }
    }

    /**
     * Положить сумму на указанный аккаунт
     */
     void serverPutMoney(BigDecimal sum, int account) {
         BigDecimal bdSum = listAccSumm.get(account).add(sum);
         listAccSumm.remove(account);
         listAccSumm.put(account,bdSum);
     }

    /**
     * Ядро запуска терминала
     */
    public static void main(String[] args) throws NoSuchFieldException {
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
