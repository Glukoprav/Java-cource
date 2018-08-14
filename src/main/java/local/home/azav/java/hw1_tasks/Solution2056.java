package local.home.azav.java.hw1_tasks;

/*
   Задача N 2056. Структуры данных. Самое популярное слово
 Дан текст, ваша задача - найти слово, которое встречается в тексте наибольшее
 количество раз. Текст состоит только из латинских букв, пробелов, переводов строк.
   Слово - это последовательность подряд идущих латинских букв,
 регистр не имеет значения.
 Если искомых слов несколько, ваша задача - найти все такие слова.
        Входные данные
 Входные данные состоят только из латинских букв, пробелов и символов
 перевода строки. Гарантируется, что хотя бы одно слово в текст присутствует.
        Выходные данные
 Выведите все слова, которые встречаются наибольшее количество раз,
 при их следут приводить к нижнему регистру, каждое слово выводите
 на отдельной строке. Слова выводите в лексикографическом порядке.
 Размер входного файла не превосходит 100 Кб.
        Пример(ы)
  Sebastian Vettel is a German Formula One racing driver currently driving for Red Bull Racing
    racing

  a aa aaa aaaa
  a aa aaa bbbb
    a
    aa
    aaa
*/

import java.util.*;

public class Solution2056 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> arrayList = new ArrayList<>();
        do {
            String std = scanner.next();
            arrayList.add(std.toLowerCase());
        } while (scanner.hasNext());
        Collections.sort(arrayList);
        int sizeAr = arrayList.size();
        int[] arrCount = new int[sizeAr];
        for (int i = 0; i < sizeAr; i++) {
            int indWord = arrayList.indexOf(arrayList.get(i));
            arrCount[i] = indWord;
        }
        int cou = 0;
        for (int i = sizeAr - 1; i >= 0; i--) {
            if (arrCount[i] == i && cou == 0) {
                arrCount[i] = 1;
            } else if (arrCount[i] != i) {
                arrCount[i] = 0;
                cou++;
            } else if (arrCount[i] == i && cou != 0) {
                arrCount[i] = cou + 1;
                cou = 0;
            }
        }
        cou = 0;
        for (int i = 0; i < sizeAr; i++) {
            if (cou < arrCount[i]) {
                cou = arrCount[i];
            }
        }
        for (int i = 0; i < sizeAr; i++) {
            if (arrCount[i] == cou) {
                System.out.println(arrayList.get(i));
            }
        }
    }
}
