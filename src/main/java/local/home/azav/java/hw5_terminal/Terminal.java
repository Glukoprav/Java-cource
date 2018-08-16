package local.home.azav.java.hw5_terminal;

import java.math.BigDecimal;

public interface Terminal {
    // Проверить состояние счета
    BigDecimal checkSumAccount () throws NoSuchFieldException;

    // Снять деньги
    boolean withdrawMoney (int sum) throws NoSuchFieldException;

    // Положить деньги
    boolean putMoney (int sum) throws NoSuchFieldException;
}
