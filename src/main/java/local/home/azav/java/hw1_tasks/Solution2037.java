package local.home.azav.java.hw1_tasks;

/*
Задача N 2037. Строки. Слишком короткие слова
Дан текст, состоящий только из маленьких латинских букв и запятых.
 Последовательность подряд идущих букв, окруженную запятыми (началом, концом строки),
 назовем словом. Слово может быть пустым.
    Ваша задача — удалить из текста все слова короче k символов.
        Входные данные
 Первая строка входных данных — это текст, состоящий только из строчных
 латинских букв и запятых. Его длина не превосходит 1000 символов.
 На второй строке находится единственное число k — минимальная допустимая длина
 слова (0 ≤ k ≤ 1000).
        Выходные данные
 Выведите текст в таком же формате, как он задан — слова следует разделять запятыми.
     input.txt                       output.txt
    ,a,ab,abc,abcd                  ab,abc,abcd
    2
,pam,,param,,param,,pam,,pam,        param,param
4
*/

import java.util.*;

public class Solution2037 {
    int intIn = 0;
    StringBuilder strin;

    String sol2037() {
        if (intIn == 0) {
            return strin.toString();
        } else {
            char ch;
            int posLeft = 0;
            int posRight = 0;
            selectComma(posLeft, posRight);
            posLeft = 0;
            posRight = 0;
            int length = strin.length();
            while (posRight < length) {
                ch = strin.charAt(posRight);
                if (ch != ',') {
                    posRight++;
                } else if (ch == ',' && (posRight - posLeft) >= intIn) {
                    posRight++;
                    posLeft = posRight;
                } else if (ch == ',' && (posRight == posLeft)) {
                    strin.delete(posLeft, posRight + 1);
                    length = strin.length();
                } else if (ch == ',' && (posRight - posLeft) < intIn) {
                    strin.delete(posLeft, posRight + 1);
                    length = strin.length();
                    posRight = posLeft;
                }
            }
            return strInProc(posLeft, posRight);
        }
    }

    void selectComma(int posLeft, int posRight) {
        while (strin.charAt(posRight) == ',') {
            posRight++;
            if (posRight == strin.length()) {
                break;
            }
        }
        strin.delete(posLeft, posRight);
    }

    String strInProc(int posLeft, int posRight){
        int length = strin.length();
        if (length > 0 && strin.charAt(length - 1) == ',') {
            strin.delete(length - 1, length);
        } else if (length > 0 && length < intIn) {
            strin.delete(0, length);
        } else if (length > 0 && length > intIn && (posRight - posLeft) < intIn) {
            strin.delete(posLeft - 1, posRight);
        }
        return strin.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Solution2037 sol = new Solution2037();
        sol.strin = new StringBuilder(scanner.next());
        sol.intIn = scanner.nextInt();
        System.out.println(sol.sol2037());
    }
}
