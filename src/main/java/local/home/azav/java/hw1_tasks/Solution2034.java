package local.home.azav.java.hw1_tasks;

/*Задача N 2034. Наидлиннейший почти константный подмассив
Задан массив (a1, a2,..., an). Его подмассив — это последовательность одного
 или более его подряд идущих элементов. Очевидно, что подмассив можно задать
 парой целых чисел l,r (1 ≤ l ≤ r ≤ n), обозначающих подмассив с позиции
 l по r включительно.
   Подмассив будем называть почти константным, если любые два элементы в нем
 отличаются не более чем на единицу. Например, в массиве (3, 2, 1, 2, 3, 4)
 подмассив с позиции 2 по позицию 4 — почти константный, также таким является
 подмассив с позиции 4 по позицию 5,
 но подмассив с позиции 3 по позицию 5 таковым не является.

  Ваша задача найти самый длинный почти константный подмассив. Если таких несколько,
 то выведите такой, который начинается с минимальной позиции.
        Входные данные
В первой строке записано целое положительное число n (1 ≤ n ≤ 10000),
 где n — длина заданного массива. Вторая строка содержит последовательность
 a1, a2,..., an (1 ≤ ai ≤ 10000) — последовательность элементов заданного массива.
        Выходные данные
Выведите l, r для искомого подмассива. Числа выводите через пробел.*/

import java.util.*;

public class Solution2034 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int indMass = scanner.nextInt();
        int[] mass = new int[indMass];
        for (int i = 0; i < indMass; i++) {
            mass[i] = scanner.nextInt();
        }

        int[] massLength = new int[indMass];
        for (int i = 0; i < indMass; i++) {
            int length = 0;
            int curElem = mass[i];
            for (int j = i + 1; j < indMass; j++) {
                int per = curElem - mass[j];
                if (per == 0) {
                    length++;
                } else if (per == 1) {
                    int k = j;
                    while (k < indMass) {
                        if (mass[k] == (curElem - 1) || mass[k] == curElem) {
                            length++;
                            k++;
                        } else {
                            break;
                        }
                    }
                    massLength[i] = length;
                    break;
                } else if (per == -1) {
                    int k = j;
                    while (k < indMass) {
                        if (mass[k] == (curElem + 1) || mass[k] == curElem) {
                            length++;
                            k++;
                        } else {
                            break;
                        }
                    }
                    massLength[i] = length;
                    break;
                } else {
                    break;
                }
            }
            massLength[i] = length;
        }
        int li = massLength[0];
        int pos = 0;
        for (int i = 0; i < indMass; i++) {
            if (li < massLength[i]) {
                pos = i;
                li = massLength[i];
            }
        }
        System.out.println(pos + 1);
        System.out.println(pos + li + 1);
    }
}
