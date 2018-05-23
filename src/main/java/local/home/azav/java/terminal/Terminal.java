package local.home.azav.java.terminal;

import java.math.BigDecimal;

public interface Terminal {
    // Проверить состояние счета
    BigDecimal checkSumAccount () throws NoSuchFieldException;

    // Снять деньги
    boolean withdrawMoney (BigDecimal sum) throws NoSuchFieldException;

    // Положить деньги
    boolean putMoney (BigDecimal sum) throws NoSuchFieldException;
}
