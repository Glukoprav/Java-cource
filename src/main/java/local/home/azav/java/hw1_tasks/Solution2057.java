package local.home.azav.java.hw1_tasks;

/*
   Задача N 2057. Структуры данных. Множество
 Ваша задача — реализовать структуру данных, которая умеет хранить мультимножество натуральных чисел,
 т.е. в этой структуре могут одновременно храниться несколько равных элементов.
 Эта структура должна поддерживать две операции:
        добавить элемент x в множество
        удалить минимальный элемент в множестве и вернуть его значение (если минимальных элементов
 несколько, то удаляется только один из них)
        Входные данные
 Первая строка входных данных содержит число n (1 ≤ n ≤ 106) — количество операций.
 Далее в n строках даны описания операций над множеством. Описание представляет собой
 число — тип запроса (1 или 2) и число x (1 ≤ x ≤ 109) если это запрос первого типа.
        Выходные данные
 Для каждого запроса второго типа выведите результат на отдельной строке.
        input.txt
        6
        1 3
        1 2
        1 1
        2
        2
        2
        output.txt
        1
        2
        3

        input.txt
        8
        1 1
        1 1
        2
        1 1
        1 2
        2
        2
        2
        output.txt
        1
        1
        1
        2
*/

import java.util.*;

public class Solution2057 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeMap<Long, Integer> treeMap = new TreeMap<>();
        int operCou = scanner.nextInt();
        for (int i = 1; i <= operCou; i++) {
            int oper = scanner.nextInt();
            if (oper == 1) {
                long inum = scanner.nextInt();
                inum = inum * 2200L + i;
                treeMap.put(inum, i);
            } else {
                long delkey = treeMap.firstKey();
                int val = treeMap.get(delkey);
                treeMap.remove(delkey);
                System.out.println((delkey - val) / 2200);
            }
        }
    }
}
