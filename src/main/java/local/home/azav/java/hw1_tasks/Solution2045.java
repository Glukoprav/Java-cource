package local.home.azav.java.hw1_tasks;

/*
Задача N 2045. Строки. Пунктуация
  Дан текст, состоящий из латинских букв, пробелов и знаков препинания
 (точка, запятая, восклицательный и вопросительный знаки). Слово — это
 последовательность подряд идущих латинских букв.
     Ваша задача — расставить пробелы по следующим правилам:
если между двумя словами нет знака препинания, тогда они должны разделяться ровно одним пробелом
перед каждым знаком препинания не должно быть пробелов
после каждого знака препинания должен быть ровно один пробел

  Гарантируется, что между двумя знаками препинания содержится хотя бы одно слово.
 Текст начинается и заканчивается латинской буквой.
        Входные данные
 Входные данные состоят из единственной строки — текста, длиной не более 10000 символов.
        Выходные данные
 Выведите отформатированный в соответствии с правилами текст.
   Пример(ы)
galileo galilei was an   italian physicist  ,mathematician,astronomer
                galileo galilei was an italian physicist, mathematician, astronomer
galileo  was  born  in  pisa
                galileo was born in pisa
*/

import java.util.Scanner;

public class Solution2045 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        StringBuilder strin = new StringBuilder(scanner.next());
        char ch;
        int posLeft = 0, posRight = 0;
        int length = strin.length();
        while (posRight < length) {
            ch = strin.charAt(posRight);
            if (ch != ',' && ch != ' ' && ch != '.' && ch != '!' && ch != '?') {
                posRight++;
            } else if (ch == ',' || ch == '.' || ch == '!' || ch == '?') {
                posRight++;
                strin.insert(posRight, ' ');
                posLeft = posRight;
                length = strin.length();
            } else if (ch == ' ') {
                posLeft = posRight;
                posRight++;
                ch = strin.charAt(posRight);
                if (ch == ',' || ch == '.' || ch == '!' || ch == '?' || ch == ' ') {
                    strin.delete(posLeft, posRight);
                    posRight--;
                    length = strin.length();
                } else if (ch != ',' && ch != ' ' && ch != '.' && ch != '!' && ch != '?') {
                    posRight++;
                }
            }
        }
        System.out.println(strin.toString());
    }
}
