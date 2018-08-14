package local.home.azav.java.hw1_tasks;

/*  Задача N 2009.  Ветвления, циклы. Сумма на степенях двоек
  Задана последовательность n целых чисел a1, a2,..., an.
    Выведите сумму всех ее элементов, стоящих на позициях,
    которые являются степенями двойки: 1, 2, 4, 8, 16 и т.д.

        Входные данные
   В первой строке задано целое число n (1 ≤ n ≤ 10000).
   Вторая строка содержит последовательность целых чисел a1, a2,..., an.

        Выходные данные
        Выведите искомую сумму.*/

import java.util.Scanner;

public class Solution2009 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();
        int sum = 0;
        int step = 1;
        for (int i = 1; i <= count; i++) {
            int innum = scanner.nextInt();
            if (i == step) {
                sum += innum;
                step <<= 1;
            }
        }
        System.out.println(sum);
    }
}
