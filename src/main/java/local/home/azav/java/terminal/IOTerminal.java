package local.home.azav.java.terminal;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Ввод/вывод сообщений для терминала
 */
public class IOTerminal {
    private Scanner scanner;

    protected IOTerminal() {
        scanner = new Scanner(System.in);
        outTer("Подняли сканер");
    }

    /**
     * Метод вывода сообшения на терминал
     */
    protected void outTer(String str) {
        System.out.println(str);
    }

    /**
     * Запрос на ввод аккаунта
     */
    protected int inTerAcc() {
        outTer("*********************************");
        outTer("Выход - 99999");
        outTer("Введите номер аккаунта (nnnnn): ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            outTer("Ошибка ввода!");
            scanner.nextLine();
            return 0;
        }
    }

    /**
     * Запрос на ввод pin
     */
    protected int inTerPin() {
        outTer("Введите pin (nnnn): ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            outTer("Ошибка ввода!");
            scanner.nextLine();
            return 0;
        }
    }

    /**
     * Запрос на ввод операции с терминала
     */
    protected int inTerOper() {
        outTer("-------------------------------");
        outTer("Введите операцию:");
        outTer("99 - Выход");
        outTer("1 - Проверить состояние счета");
        outTer("2 - Положить деньги");
        outTer("3 - Снять деньги");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            outTer("Ошибка ввода!");
            scanner.nextLine();
            return 0;
        }
    }

    /**
     * Запрос суммы на ввод
     */
    protected BigDecimal inTerSum() {
        outTer("Введите сумму: ");
        try {
            return scanner.nextBigDecimal();
        }catch (InputMismatchException e) {
            outTer("Ошибка ввода!");
            scanner.nextLine();
            return BigDecimal.valueOf(0);
        }
    }

    /**
     * Вывод сообщения, что всё ОК
     */
    protected void outOk() {
        outTer("OK");
    }

    /**
     * Вывод сообщения, чтоб забрали деньги
     */
    protected void outMoney(BigDecimal sum) {
        outTer("Получите свои: " + sum.toString());
    }

    /**
     * Вывод сообщения о неизвестной операции
     */
    protected void outRep() {
        outTer("Операция не распознана, введите снова!");
    }

    /**
     * Вывод сообщения о неизвестном аккаунте
     */
    protected void outRepAcc() {
        outTer("Аккаунт не найден, введите снова!");
    }

    /**
     * Вывод сообщения о неизвестном аккаунте
     */
    protected void outRepPin() {
        outTer("Неверный PIN, введите снова!");
    }

    /**
     * Временно усыпляем при 3 неверных pin
     */
    protected void sleeps() throws InterruptedException, AccountIsLockedException {
        long start = System.currentTimeMillis();
        long end, traceTyme;
        do {
            end = System.currentTimeMillis();
            traceTyme = end - start;
            if (scanner.hasNext()) {
                throw new AccountIsLockedException("Терпите!!!");
            }
        } while (traceTyme < 1000);
        //Thread.sleep(5000);
        //System.CurrentTimeMillis () = милисекунды
        //System.nanoTime() = наносекунды
    }
}
