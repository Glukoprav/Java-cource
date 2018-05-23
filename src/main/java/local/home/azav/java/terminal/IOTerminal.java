package local.home.azav.java.terminal;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * Ввод/вывод сообщений для терминала
 */
class IOTerminal {
    private Scanner scanner;

    IOTerminal() {
        scanner = new Scanner(System.in);
        outTer("Подняли сканер");
    }

    /**
     * Вывод обычного сообшения на терминал
     */
    private void outTer(String str) {
        System.out.println(str);
    }

    /**
     * Метод вывода сообшения на терминал
     */
    private void outErr(String str) {
        System.err.println(str);
    }

    /**
     * Запрос на ввод аккаунта
     */
    int inTerAcc() {
        outTer("*********************************");
        outTer("Выход - 99999");
        outTer("Введите номер аккаунта (nnnnn): ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            outErr("Ошибка ввода!");
            scanner.nextLine();
            return 0;
        }
    }

    /**
     * Запрос на ввод pin
     */
    int inTerPin() {
        outTer("Введите pin (nnnn): ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            outErr("Ошибка ввода!");
            scanner.nextLine();
            return 0;
        }
    }

    /**
     * Запрос на ввод операции с терминала
     */
    int inTerOper() {
        outTer("-------------------------------");
        outTer("Введите операцию:");
        outTer("99 - Выход");
        outTer("1 - Проверить состояние счета");
        outTer("2 - Снять деньги");
        outTer("3 - Положить деньги");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            outErr("Ошибка ввода!");
            scanner.nextLine();
            return 0;
        }
    }

    /**
     * Запрос суммы на ввод
     */
    BigDecimal inTerSum() {
        outTer("Введите сумму: ");
        try {
            scanner.useLocale(Locale.US);
            BigDecimal bigDecimal = scanner.nextBigDecimal().setScale(2, ROUND_HALF_UP);
            return bigDecimal;
        } catch (InputMismatchException e) {
            outErr("Ошибка ввода!");
            clearLine();
            return BigDecimal.valueOf(0);
        }
    }

    /**
     * Вывод сообщения об остатке на счете
     */
    void outSumAkk(String str) {
        outTer("Остаток = " + str);
    }

    /**
     * Вывод сообщения, что всё ОК
     */
    void outOk() {
        outTer("OK");
    }

    /**
     * Вывод сообщения, чтоб забрали деньги
     */
    void outMoney(BigDecimal sum, BigDecimal ost) {
        outTer("Получите свои: " + sum.toString());
        outTer("Остаток: " + ost.toString());
    }

    /**
     * Вывод сообщения о неизвестной операции
     */
    void outRep() {
        outErr("Операция не распознана, введите снова!");
    }

    /**
     * Вывод сообщения о неизвестном аккаунте
     */
    void outRepAcc() {
        outErr("Аккаунт не найден, введите снова!");
    }

    /**
     * Вывод сообщения о неизвестном аккаунте
     */
    void outRepPin() {
        outErr("Неверный PIN, введите снова!");
    }

    void outRepSumm() {
        outErr("Недостаточно средств на счете для данной операции!");
    }

    /**
     * Временно блокируем при 3 неверных pin
     */
    void blocked() throws InterruptedException, AccountIsLockedException {
        if (scanner.hasNext()) {
            throw new AccountIsLockedException("Терпите!!!");
        }
    }

    /**
     * Вывод сообщения о временной блокировке
     */
    void outBlock(long lTime) {
        outErr("Аккаунт блокирован ещё " + lTime + "сек.");
    }

    /** Чистка строки */
    void clearLine() {
        scanner.nextLine();
    }

    /** Сообщение о неизвестной ошибке */
    void outErrIncom(String str) {
        outErr("Спасайся кто может! Неизвестная ошибка!");
        outErr(str);
    }
}
