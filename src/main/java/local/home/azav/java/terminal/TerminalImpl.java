package local.home.azav.java.terminal;

import java.io.IOException;
import java.math.BigDecimal;
import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * Обработчик операций на терминале
 */
public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private final IOTerminal ioter;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
        System.out.println("И терминал появился");
        ioter = new IOTerminal();
        System.out.println("Включили ввод-вывод терминала!");
    }

    /**
     * Проверить состояние счета
     */
    @Override
    public BigDecimal checkAccount() {
        return BigDecimal.valueOf(0);
    }

    /**
     * Снять деньги
     */
    @Override
    public boolean withdrawMoney(BigDecimal sum) {
        return false;
    }

    /**
     * Положить деньги
     */
    @Override
    public boolean putMoney(BigDecimal sum) {
        return false;
    }

    /**
     * Запросить операцию на ввод
     */
    protected int operationRequest() {
        try {
            return ioter.inTerOper();
        } catch (IOException e) {
            return 99;
        }

    }

    /**
     * Обработчик операций
     */
    protected void operationHandler() {
        int oper = 99;
        BigDecimal summ = BigDecimal.valueOf(0);
        summ.setScale(2,ROUND_HALF_UP);
        do {
            summ = BigDecimal.valueOf(0);
            oper = operationRequest();
            switch (oper) {
                case 1:
                    ioter.outTer(checkAccount().toString());
                    break;
                case 2:
                    summ = ioter.inTerSum();
                    if (putMoney(summ)) {
                        ioter.outOk();
                    }
                    ;
                    break;
                case 3:
                    summ = ioter.inTerSum();
                    if (withdrawMoney(summ)) {
                        ioter.outMoney(summ);
                    }
                    break;
                case 99:
                    ioter.outOk();
                    break;
                default:
                    ioter.outRep();
                    break;
            }
        } while (oper != 99);
    }
}
