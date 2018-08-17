package local.home.azav.java.hw5_terminal;

import java.math.BigDecimal;

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
        ioter = new IOTerminal();
    }

    /**
     * Проверить состояние счета.
     * Возвращает сумму на аккаунте.
     */
    @Override
    public BigDecimal checkSumAccount() throws NoSuchFieldException {
        if (!checkAuthentication()) {
            throw new NoSuchFieldException();
        }
        return server.sumAccount(currentAccountT);
    }

    /**
     * Снять деньги у акаунта на сервере.
     * Возвращает true, если денег хватает, сумма кратна 100 и снятие прошло нормально.
     */
    @Override
    public boolean withdrawMoney(int sum) throws NoSuchFieldException {
        if (!checkAuthentication()) {
            throw new NoSuchFieldException();
        }
        if (sum % 100 != 0) {
            ioter.outBadSum(sum);
            return false;
        }
        try {
            BigDecimal sumBig = BigDecimal.valueOf(sum);
            server.serverWithdrawMoney(sumBig, currentAccountT);
            ioter.outMoney(sumBig, checkSumAccount());
            return true;
        } catch (NoSuchMethodException e) {
            ioter.outRepSumm();
            return false;
        }

    }

    /**
     * Положить деньги на аккаунт на сервере.
     * Возвращает true, если сумма кратна 100 и зачисление прошло нормально.
     */
    @Override
    public boolean putMoney(int sum) throws NoSuchFieldException {
        if (!checkAuthentication()) {
            throw new NoSuchFieldException();
        }
        if (sum % 100 != 0) {
            ioter.outBadSum(sum);
            return false;
        }
        BigDecimal sumBig = BigDecimal.valueOf(sum);
        server.serverPutMoney(sumBig, currentAccountT);
        return true;
    }

    boolean checkAuthentication() {
        if (getCurrentAccount() != 0 && getCurrentAccount() != 99999 &&
                getCurrentPin() != 0 && getCurrentPin() != 9999) {
            return true;
        } else {
            return false;
        }
    }

    int getCurrentAccount() {
        return currentAccountT;
    }

    void setCurrentAccount(int currentAccount) {
        this.currentAccountT = currentAccount;
    }

    int getCurrentPin() {
        return currentPinT;
    }

    void setCurrentPin(int currentPin) {
        this.currentPinT = currentPin;
    }

    /**
     * Проверяет наличие аккаунта на сервере TerminalServer
     */
    boolean checkAcc(int acc) {
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
     * После 3 неправильных вводов PIN - блокирует ввод на 5 сек.
     */
    boolean checkPin(int account) {
        int count = 0;
        boolean boo = false;
        do {
            count++;                                    // счетчик попыток
            int pin = ioter.inTerPin();
            try {
                boo = pinValidator.checkPin(account, pin);
                setCurrentPin(pin);
            } catch (IllegalAccessException e) {
                if (count == 3) {
                    ioter.outBlock(5);
                } else {
                    ioter.outRepPin();
                }
            } catch (Exception e) {
                ioter.outErrIncom(e.getCause() + " " + e.getMessage());
            }
        } while (!boo && count < 3);
        if ((!boo && count == 3)) {            // при 3 неудачных попытках - блокируем ввод
            // лочим аккаунт на 5 сек.
            lockCheckPin();
        }
        return boo;
    }

    // лочим аккаунт на 5 сек.
    void lockCheckPin() {
        long start = System.currentTimeMillis();
        long end;
        long traceTime = 0;
        do {
            try {
                ioter.blocked();
            } catch (AccountIsLockedException e) {
                end = System.currentTimeMillis();
                traceTime = end - start;
                ioter.outBlock(6 - traceTime / 1000);
                ioter.clearLine();
            }
        } while (traceTime < 5000);
    }

    /**
     * Обработчик операций
     */
    void operationHandler() throws NoSuchFieldException {
        int account;
        int oper;
        do {
            account = ioter.inTerAcc();                         // запрос аккаунта
            if (checkAcc(account)) {                            // проверяем наличие аккаунта
                if (checkPin(account)) {                        // проверяем pin
                    do {
                        oper = ioter.inTerOper();               // запрос операции
                        operSwitch(oper);
                    } while (oper != 99);
                }
            }
            setCurrentPin(9999);
            setCurrentAccount(99999);
        } while (account != 99999);
        ioter.outOk();
    }

    private void operSwitch(int oper) throws NoSuchFieldException {
        switch (oper) {
            case 1:                             // проверить остаток
                ioter.outSumAkk(checkSumAccount().toString());
                break;
            case 2:                             // снять деньги
                if (withdrawMoney(ioter.inTerSum())) {
                    ioter.outOk();
                }
                break;
            case 3:                             // положить деньги
                if (putMoney(ioter.inTerSum())) {
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
    }
}
