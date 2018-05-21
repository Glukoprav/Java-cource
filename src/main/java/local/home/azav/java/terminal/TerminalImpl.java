package local.home.azav.java.terminal;

import java.math.BigDecimal;

public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
        System.out.println("и терминал появился");
    }

    /** Проверить состояние счета */
    @Override
    public BigDecimal checkAccount() {
        return null;
    }

    /** Снять деньги */
    @Override
    public boolean withdrawMoney(BigDecimal sum) {
        return false;
    }

    /** Положить деньги*/
    @Override
    public boolean putMoney(BigDecimal sum) {
        return false;
    }
}
