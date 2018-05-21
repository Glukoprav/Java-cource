package local.home.azav.java.terminal;

import java.math.BigDecimal;

public interface Terminal {
    // Проверить состояние счета
    BigDecimal checkAccount ();

    // Снять деньги
    boolean withdrawMoney (BigDecimal sum);

    // Положить деньги
    boolean putMoney (BigDecimal sum);
}
