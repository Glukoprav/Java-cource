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
    private int currentAccountT;
    private int currentPinT;

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
        // наличие
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

    private int getCurrentAccount() {
        return currentAccountT;
    }

    private void setCurrentAccount(int currentAccount) {
        this.currentAccountT = currentAccount;
    }

    private int getCurrentPin() {
        return currentPinT;
    }

    private void setCurrentPin(int currentPin) {
        this.currentPinT = currentPin;
    }

    /**
     * Проверяет наличие аккаунта на сервере
     */
    private boolean checkAcc(int acc) {
        if (acc == 99999) {
            return false;
        }
        try {
            if (server.checkAccount(acc)) {
                setCurrentAccount(acc);
                return true;
            } else {
                return false;
            }
        } catch (NoSuchFieldException e) {
            ioter.outRepAcc();
            return false;
        } catch (Exception e) {
            ioter.outErrIncom(e.getCause() + " " + e.getMessage());
            return false;
        }
    }

    /**
     * Проверяет правильность pin у аккаунта
     */
    private boolean checkPin(int account) {
        int count = 0;
        boolean boo = false;
        do {
            count++;
            int pin = ioter.inTerPin();
            try {
                boo = pinValidator.checkPin(account, pin);
                setCurrentPin(pin);
            } catch (IllegalAccessException e) {
                if (count == 3) {
                    ioter.outBlock(5);
                } else {
                ioter.outRepPin();}
            } catch (Exception e) {
                ioter.outErrIncom(e.getCause() + " " + e.getMessage());
            }
        } while (boo == false && count < 3);
        if ((boo == false && count == 3)) {
            // лочим аккаунт на 5 сек.
            long start = System.currentTimeMillis();
            long end, traceTyme = 0;
            do {
                try {
                    ioter.blocked();
                } catch (AccountIsLockedException e) {
                    end = System.currentTimeMillis();
                    traceTyme = end - start;
                    ioter.outBlock(6 - traceTyme/1000);
                    ioter.clearLine();
                } catch (InterruptedException e) {
                    ioter.outErrIncom(e.getCause() + " " + e.getMessage());
                }
            } while (traceTyme < 5000);
        }
        return boo;
    }

    /**
     * Обработчик операций
     */
    protected void operationHandler() {
        int account = 99999;
        int pin = 9999;
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
            setCurrentPin(9999);
            setCurrentAccount(99999);
        } while (account != 99999);
    }
}
