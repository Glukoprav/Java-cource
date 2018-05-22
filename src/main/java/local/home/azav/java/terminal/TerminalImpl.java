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

    TerminalImpl(TerminalServer server, PinValidator pinValidator) {
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
    public BigDecimal checkSumAccount() {
        return BigDecimal.valueOf(0);
    }

    /**
     * Снять деньги
     */
    @Override
    public boolean withdrawMoney(BigDecimal sum) {
        return true;
    }

    /**
     * Положить деньги
     */
    @Override
    public boolean putMoney(BigDecimal sum) {
        return false;
    }

    /**
     * Проверить наличие аккаунта на сервере
     */
    private boolean checkAcc(int acc) {
        if (acc == 99999) {
            return false;
        }
        try {
            return server.checkAccount(acc);
        } catch (NoSuchFieldException e) {
            ioter.outRepAcc();
            return false;
        } catch (Exception e) {
            System.out.println("Спасайся кто может! Неизвестная ошибка!");
            System.out.println(e.getCause() + " " + e.getMessage());
            return false;
        }
    }

    /**
     * Проверить правильность pin у аккаунта
     */
    private boolean checkPin(int account) {
        int count = 0;
        boolean boo = false;
        do {
            count++;
            int pin = ioter.inTerPin();
            try {
                boo = pinValidator.checkPin(account,pin);
            } catch (IllegalAccessException e) {
                ioter.outRepPin();
            } catch (Exception e) {
                System.out.println("Спасайся кто может! Неизвестная ошибка!");
                System.out.println(e.getCause() + " " + e.getMessage());
            }
        } while (boo == false && count < 3);
        if ((boo == false && count == 3)) {
            // лочим аккаунт на 5 сек.
            try {
                ioter.sleeps();
            } catch (AccountIsLockedException e) {
                System.out.println("Потерпите!!");
            } catch (InterruptedException e) {
                System.out.println("Потерпите!!");
            }
        }
        return boo;
    }

    /**
     * Обработчик операций
     */
    protected void operationHandler() {
        int account = 99999;
        int oper = 99;
        BigDecimal summ = BigDecimal.valueOf(0);
        summ.setScale(2, ROUND_HALF_UP);
        do {
            account = ioter.inTerAcc();                         // запрос аккаунта
            if (checkAcc(account)) {                            // проверяем наличие аккаунта
                if (checkPin(account)) {                        // проверяем pin
                    do {
                        summ = BigDecimal.valueOf(0);
                        oper = ioter.inTerOper();               // запрос операции
                        switch (oper) {
                            case 1:                             // проверить остаток
                                ioter.outTer(checkSumAccount().toString());
                                break;
                            case 2:                             // положить деньги
                                summ = ioter.inTerSum();
                                if (putMoney(summ)) {
                                    ioter.outOk();
                                }
                                break;
                            case 3:                             // снять деньги
                                summ = ioter.inTerSum();
                                if (withdrawMoney(summ)) {
                                    ioter.outMoney(summ);
                                }
                                break;
                            case 99:                            // выход из операций
                                ioter.outOk();
                                break;
                            default:
                                ioter.outRep();
                                break;
                        }
                    } while (oper != 99);
                }
            }
        } while (account != 99999);
    }
}
