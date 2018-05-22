package local.home.azav.java.terminal;

import java.io.IOException;
import java.math.BigDecimal;
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
     * Вывод сообшения на терминал
     */
    protected void outTer(String str) {
        System.out.println(str);
    }

    /**
     * Запрос операции на ввод с терминала
     */
    protected int inTerOper() throws IOException {
        outTer("-------------------------------");
        outTer("Введите операцию:");
        outTer("99 - Выход");
        outTer("1 - Проверить состояние счета");
        outTer("2 - Положить деньги");
        outTer("3 - Снять деньги");
        return scanner.nextInt();
    }

    /**
     * Запрос суммы на ввод
     */
    protected BigDecimal inTerSum() {
        outTer("Введите сумму: ");
        return scanner.nextBigDecimal();
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
}
