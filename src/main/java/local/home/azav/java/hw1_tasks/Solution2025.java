package local.home.azav.java.hw1_tasks;

/*
Задача N 2025. Массивы. Запросы минимума на подмассиве
Рассмотрим простой вариант задачи RMQ (Range Minimum Query, запрос минимума на подмассиве).

   Задан массив из n целых чисел (a1, a2,..., an) и m запросов
 вида (li, ri) (1 ≤ li ≤ ri ≤ n). Ваша задача — на каждый запрос вывести
 минимальный среди элементов с индексами от li до ri включительно.
        Входные данные
  В первой строке входного файла дано число n (1 ≤ n ≤ 100) — количество
 чисел в массиве. Во второй строке через пробел записаны целые
 числа a1, a2,..., an (1 ≤ ai ≤ 100). В третьей строке входного файла
 дано число m (1 ≤ m ≤ 100) — количество запросов.
 Далее в m строках даны запросы по одному на строке в виде двух целых
 чисел li, ri, разделенных пробелом (1 ≤ li ≤ ri ≤ n).
        Выходные данные
 Выведите m чисел — ответы на запросы. Числа разделяйте пробелом или
 переводом строки. На запросы следуюет отвечать в том порядке,
 в котором они заданы во входных данных.
*/

import java.util.*;

public class Solution2025 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int indMass = scanner.nextInt();
        int[] mass = new int[indMass];
        for (int i = 0; i < indMass; i++) {
            mass[i] = scanner.nextInt();
        }
        int numZap = scanner.nextInt();
        for (int i = 0; i < numZap; i++) {
            int li = scanner.nextInt();
            int ri = scanner.nextInt();
            int minNum = mass[li - 1];
            for (int j = li; j <= ri; j++) {
                if (minNum > mass[j - 1]) {
                    minNum = mass[j - 1];
                }
            }
            System.out.println(minNum);
        }
    }
}