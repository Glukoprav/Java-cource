package local.home.azav.java.terminal;

import java.io.IOException;
import java.util.Scanner;

public class IOTerminal {

    // Вывод сообшения на терминал
    public void outTer(String str){
        System.out.println(str);
    }

    // Запрос данных на ввод с терминала
    public String inTer(String str) throws IOException {
        System.out.println(str);
        Scanner scanner = new Scanner(System.in);
        return String.valueOf(scanner.next());
    }
}
