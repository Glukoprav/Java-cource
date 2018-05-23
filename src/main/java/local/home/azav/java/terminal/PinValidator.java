package local.home.azav.java.terminal;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

class PinValidator {
    // Хранение pin по аккаунтам
    private Map<Integer, BigDecimal> listAccPin;

    PinValidator() {
        listAccPin = new TreeMap<>();

        // Составляем список аккаунтов для теста
        listAccPin.put(12345, BigDecimal.valueOf(1234));
        listAccPin.put(12346, BigDecimal.valueOf(1235));
        listAccPin.put(12342, BigDecimal.valueOf(1236));
        listAccPin.put(12340, BigDecimal.valueOf(1237));
        System.out.println("Пин-валидатор зашевелился");
    }

    /**
     * Проверить pin
     */
    boolean checkPin(int acc, int pin) throws IllegalAccessException {
        if (listAccPin.containsKey(acc)) {
            if (listAccPin.get(acc).intValue() == pin) {
                return true;
            } else {
                throw new IllegalAccessException();
            }
        } else {
            throw new IllegalAccessException();
        }
    }
}
