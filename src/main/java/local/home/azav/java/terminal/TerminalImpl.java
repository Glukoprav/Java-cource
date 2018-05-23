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
     * Проверить состояние счета.
     * Возвращает сумму на аккаунте.
     */
    @Override
    public BigDecimal checkSumAccount() throws NoSuchFieldException {
        if (!checkAuthentication()) {
            throw new NoSuchFieldException();
        };
        return server.sumAccount(currentAccountT);
    }

    /**
     * Снять деньги у акаунта.
     * Возвращает true, если денег хватает.
     */
    @Override
    public boolean withdrawMoney(BigDecimal sum) throws NoSuchFieldException {
        if (!checkAuthentication()) {
            throw new NoSuchFieldException();
        };
        try {
            server.serverWithdrawMoney(sum, currentAccountT);
            return true;
        } catch (NoSuchMethodException e) {
            ioter.outRepSumm();
            return false;
        }

    }

    /**
     * Положить деньги на аккаунт.
     */
    @Override
    public boolean putMoney(BigDecimal sum) throws NoSuchFieldException {
        if (!checkAuthentication()) {
            throw new NoSuchFieldException();
        };
        server.serverPutMoney(sum,currentAccountT);
        return true;
    }

    private boolean checkAuthentication() {
        if (currentAccountT != 0 && currentAccountT != 99999 &&
                currentPinT != 0 && currentPinT != 9999) {
            return true;
        } else {
            return false;
        }
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
     * Проверяет наличие аккаунта на сервере TerminalServer
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
     * Проверяет правильность pin аккаунта у PinValidator
     */
    private boolean checkPin(int account) {
        int count = 0;
        boolean boo = false;
        do {
            count++;                                    // счетчик попыток
            int pin = ioter.inTerPin();
            try {
                boo = pinValidator.checkPin(account, pin);
            } catch (IllegalAccessException e) {
                if (count == 3) {
                    ioter.outBlock(5);
                } else {
                ioter.outRepPin();}
            } catch (Exception e) {
                ioter.outErrIncom(e.getCause() + " " + e.getMessage());
            }
        } while (boo == false && count < 3);
        if ((boo == false && count == 3)) {            // при 3 неудачных попытках - блокируем ввод
            // лочим аккаунт на 5 сек.
            long start = System.currentTimeMillis();
            long end, traceTime = 0;
            do {
                try {
                    ioter.blocked();
                } catch (AccountIsLockedException e) {
                    end = System.currentTimeMillis();
                    traceTime = end - start;
                    ioter.outBlock(6 - traceTime/1000);
                    ioter.clearLine();
                } catch (InterruptedException e) {
                    ioter.outErrIncom(e.getCause() + " " + e.getMessage());
                }
            } while (traceTime < 5000);
        }
        return boo;
    }

    /**
     * Обработчик операций
     */
    void operationHandler() throws NoSuchFieldException {
        int account = 99999;
        int pin = 9999;
        int oper = 99;
        BigDecimal summ = BigDecimal.valueOf(0).setScale(2, ROUND_HALF_UP);
        do {
            account = ioter.inTerAcc();                         // запрос аккаунта
            if (checkAcc(account)) {                            // проверяем наличие аккаунта
                if (checkPin(account)) {                        // проверяем pin
                    do {
                        summ = BigDecimal.valueOf(0);
                        oper = ioter.inTerOper();               // запрос операции
                        switch (oper) {
                            case 1:                             // проверить остаток
                                ioter.outSumAkk(checkSumAccount().toString());
                                break;
                            case 2:                             // снять деньги
                                summ = ioter.inTerSum();
                                if (withdrawMoney(summ)) {
                                    ioter.outMoney(summ, checkSumAccount());
                                }
                                break;
                            case 3:                             // положить деньги
                                summ = ioter.inTerSum();
                                if (putMoney(summ)) {
                                    ioter.outOk();
                                    ioter.outSumAkk(checkSumAccount().toString());
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
        ioter.outOk();
    }
}
