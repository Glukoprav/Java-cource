package local.home.azav.java;


//Задача N 2025. Массивы. Запросы минимума на подмассиве.  Версия итоговая!
//import java.util.*;
//public class Solution {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        int ind_mass = scanner.nextInt();
//        int[] mass = new int[ind_mass];
//        for (int i= 0; i<ind_mass; i++) {
//            mass[i] = scanner.nextInt();
//        }
//        int num_zap = scanner.nextInt();
//        for (int i = 0; i < num_zap; i++) {
//            int li = scanner.nextInt();
//            int ri = scanner.nextInt();
//            int min_num = mass[li-1];
//            for (int j = li; j <= ri; j++) {
//                if (min_num > mass[j-1]) {
//                    min_num = mass[j-1];
//                }
//            }
//            System.out.println(min_num);
//        }
//    }
//}

//Задача N 2034. Наидлиннейший почти константный подмассив. Версия 2.
//import java.util.*;
//public class Solution {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        int ind_mass = scanner.nextInt();
//        int[] mass = new int[ind_mass];
//        for (int i= 0; i<ind_mass; i++) {
//            mass[i] = scanner.nextInt();
//        }
//
//        int[] mass_length = new int[ind_mass];
//        for (int i = 0; i < ind_mass; i++) {
//            int length = 0;
//            int cur_elem = mass[i];
//            for (int j = i + 1; j < ind_mass; j++) {
//                int per = cur_elem - mass[j];
//                if (per == 0) {
//                    length++;
//                } else if (per == 1) {
//                    int k = j;
//                    while (k < ind_mass) {
//                        if (mass[k] == (cur_elem - 1) | mass[k] == cur_elem) {
//                            length++;
//                            k++;
//                        } else {
//                            break;
//                        }
//                    }
//                    mass_length[i] = length;
//                    break;
//                } else if (per == -1) {
//                    int k = j;
//                    while (k < ind_mass) {
//                        if (mass[k] == (cur_elem + 1) | mass[k] == cur_elem) {
//                            length++;
//                            k++;
//                        } else {
//                            break;
//                        }
//                    }
//                    mass_length[i] = length;
//                    break;
//                } else {
//                    break;
//                }
//            }
//            mass_length[i] = length;
//        }
//        int li = mass_length[0];
//        int pos = 0;
//        for (int i = 0; i < ind_mass; i++) {
//            if (li < mass_length[i]) {
//                pos = i;
//                li = mass_length[i];
//            }
//        }
//        System.out.println(pos + 1);
//        System.out.println(pos + li+1);
//    }
//}


//Задача N 2034. Наидлиннейший почти константный подмассив. Версия 1.
//import java.util.*;
//public class Solution {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        int ind_mass = scanner.nextInt();
//        int[] mass = new int[ind_mass];
//        for (int i= 0; i<ind_mass; i++) {
//            mass[i] = scanner.nextInt();
//        }
//
//        int[] mass_length = new int[ind_mass];
//        for (int i = 0; i < ind_mass; i++) {
//            int length = 0;
//            int cur_elem = mass[i];
//            for (int j = i + 1; j < ind_mass; j++) {
//                int per = cur_elem - mass[j];
//                if (per == 0 || per == 1 || per == -1) {
//                    int k = j;
//                    while (k < ind_mass) {
//                        if (mass[k] == mass[j] | mass[k] == cur_elem) {
//                            length++;
//                            k++;
//                        } else {
//                            break;
//                        }
//                    }
//                    mass_length[i] = length;
//                    break;
//                } else {
//                    break;
//                }
//            }
//            mass_length[i] = length;
//        }
//        int li = mass_length[0];
//        int pos = 0;
//        for (int i = 0; i < ind_mass; i++) {
//            if (li < mass_length[i]) {
//                pos = i;
//                li = mass_length[i];
//            }
//        }
//        System.out.println(pos + 1);
//        System.out.println(pos + li+1);
//    }
//}

//Задача N 2037. Строки. Слишком короткие слова. Версия 2.
import java.util.Scanner;
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder strin = new StringBuilder(scanner.next());
        int min_word = scanner.nextInt();
        if (min_word == 0) {
            System.out.println(strin);
        } else {
            char ch;
            int posLeft = 0, posRight = 0;
            while (strin.charAt(posRight) == ',') {
                posRight++;
            }
            strin.delete(posLeft,posRight);
            posLeft = 0;
            posRight = 0;
            int length = strin.length();
            while (posRight < length) {
                ch = strin.charAt(posRight);
                if (ch != ',') {
                    posRight++;
                } else if (ch == ',' & (posRight - posLeft) >= min_word) {
                    posRight++;
                    posLeft = posRight;
                } else if (ch == ',' & (posRight == posLeft)) {
                    strin.delete(posLeft,posRight+1);
                    length = strin.length();
                } else if (ch == ',' & (posRight - posLeft) < min_word) {
                    strin.delete(posLeft,posRight+1);
                    length = strin.length();
                    posRight = posLeft;
                }
            }
            length = strin.length();
            if (length > 0 && strin.charAt(length-1) == ',') {
                strin.delete(length-1,length);
            }
            System.out.println(strin);
        }
    }
}

//Задача N 2037. Строки. Слишком короткие слова
//Дан текст, состоящий только из маленьких латинских букв и запятых.
// Последовательность подряд идущих букв, окруженную запятыми (началом, концом строки),
// назовем словом. Слово может быть пустым.
//    Ваша задача — удалить из текста все слова короче k символов.
//        Входные данные
// Первая строка входных данных — это текст, состоящий только из строчных
// латинских букв и запятых. Его длина не превосходит 1000 символов.
// На второй строке находится единственное число k — минимальная допустимая длина
// слова (0 ≤ k ≤ 1000).
//        Выходные данные
// Выведите текст в таком же формате, как он задан — слова следует разделять запятыми.
//     input.txt                       output.txt
//    ,a,ab,abc,abcd                  ab,abc,abcd
//    2
//,pam,,param,,param,,pam,,pam,        param,param
//4



//Задача N 2034. Наидлиннейший почти константный подмассив. Версия 2.
/*
import java.util.*;
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // читаем массив
        int ind_mass = scanner.nextInt();   // длина массива
        int[] mass = new int[ind_mass];
        for (int i= 0; i<ind_mass; i++) {
            mass[i] = scanner.nextInt();
        }
        System.out.println(Arrays.toString(mass));    //!! убрать!!

        // ищем подмассив
        int[] mass_length = new int[ind_mass];     // массив длин подмассивов для каждой позиции массива
        // сканируем для каждого элемента
        for (int i = 0; i < ind_mass; i++) {
            int length = 0;                        // текущая длина подмассива
            int cur_elem = mass[i];                // текущий элемент массива
            for (int j = i + 1; j < ind_mass; j++) {
                int per = cur_elem - mass[j];      // разница со следующим
                if (per == 0) {
                    length++;
                } else if (per == 1) {
                    int k = j;
                    while (k < ind_mass) {
                        if (mass[k] == (cur_elem - 1) | mass[k] == cur_elem) {
                            length++;
                            k++;
                        } else {
                            break;
                        }
                    }
                    mass_length[i] = length;
                    break;
                } else if (per == -1) {
                    int k = j;
                    while (k < ind_mass) {
                        if (mass[k] == (cur_elem + 1) | mass[k] == cur_elem) {
                            length++;
                            k++;
                        } else {
                            break;
                        }
                    }
                    mass_length[i] = length;
                    break;
                } else {
                    break;
                }
            }
            mass_length[i] = length;
            System.out.println("i=" + i + " cur_elem=" + cur_elem + " length=" + length);    //!! убрать!!
        }
        //отбираем самый длинный левый из массива длин
        int li = mass_length[0];                   // длина подмассива для элемента
        int pos = 0;                               // позиция начала
        for (int i = 0; i < ind_mass; i++) {
            if (li < mass_length[i]) {
                pos = i;
                li = mass_length[i];
            }
        }
        System.out.println(Arrays.toString(mass_length));    //!! убрать!!
        //выводим границы подмассива
        System.out.println("li pos=" + (pos + 1));      //!! укоротить!!
        System.out.println("ri pos=" + (pos + li+1));   //!! укоротить!!
    }
}
*/


//Задача N 2034. Наидлиннейший почти константный подмассив. Версия 1.
//import java.util.*;
//public class Solution {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        // читаем массив
//        int ind_mass = scanner.nextInt();   // длина массива
//        int[] mass = new int[ind_mass];
//        for (int i= 0; i<ind_mass; i++) {
//            mass[i] = scanner.nextInt();
//        }
//        System.out.println(Arrays.toString(mass));    //!! убрать!!
//
//        // ищем подмассив
//        int[] mass_length = new int[ind_mass];     // массив длин подмассивов для каждой позиции массива
//        // сканируем для каждого элемента
//        for (int i = 0; i < ind_mass; i++) {
//            int length = 0;                        // текущая длина подмассива
//            int cur_elem = mass[i];                // текущий элемент массива
//            for (int j = i + 1; j < ind_mass; j++) {
//                int per = cur_elem - mass[j];      // разница со следующим
//                if (per == 0 || per == 1 || per == -1) {
//                    int k = j;
//                    while (k < ind_mass) {
//                        if (mass[k] == mass[j] | mass[k] == cur_elem) {
//                            length++;
//                            k++;
//                        } else {
//                            break;
//                        }
//                    }
//                    mass_length[i] = length;
//                    break;
//                } else {
//                    break;
//                }
//            }
//            mass_length[i] = length;
//            System.out.println("i=" + i + " cur_elem=" + cur_elem + " length=" + length);    //!! убрать!!
//        }
//        //отбираем самый длинный из массива длин
//        int li = mass_length[0];                   // длина подмассива для элемента
//        int pos = 0;                               // позиция начала
//        for (int i = 0; i < ind_mass; i++) {
//            if (li < mass_length[i]) {
//                pos = i;
//                li = mass_length[i];
//            }
//        }
//        System.out.println(Arrays.toString(mass_length));    //!! убрать!!
//        //выводим границы подмассива
//        System.out.println("li pos=" + (pos + 1));      //!! укоротить!!
//        System.out.println("ri pos=" + (pos + li+1));   //!! укоротить!!
//    }
//}

//Задача N 2034. Наидлиннейший почти константный подмассив
//Задан массив (a1, a2,..., an). Его подмассив — это последовательность одного
// или более его подряд идущих элементов. Очевидно, что подмассив можно задать
// парой целых чисел l,r (1 ≤ l ≤ r ≤ n), обозначающих подмассив с позиции
// l по r включительно.
//   Подмассив будем называть почти константным, если любые два элементы в нем
// отличаются не более чем на единицу. Например, в массиве (3, 2, 1, 2, 3, 4)
// подмассив с позиции 2 по позицию 4 — почти константный, также таким является
// подмассив с позиции 4 по позицию 5,
// но подмассив с позиции 3 по позицию 5 таковым не является.
//
//  Ваша задача найти самый длинный почти константный подмассив. Если таких несколько,
// то выведите такой, который начинается с минимальной позиции.
//        Входные данные
//В первой строке записано целое положительное число n (1 ≤ n ≤ 10000),
// где n — длина заданного массива. Вторая строка содержит последовательность
// a1, a2,..., an (1 ≤ ai ≤ 10000) — последовательность элементов заданного массива.
//        Выходные данные
//Выведите l, r для искомого подмассива. Числа выводите через пробел.


//Задача N 2025. Массивы. Запросы минимума на подмассиве
//import java.util.*;
//public class Solution {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        // читаем массив
//        int ind_mass = scanner.nextInt();   // размер массива
//        int[] mass = new int[ind_mass];
//        for (int i= 0; i<ind_mass; i++) {
//            mass[i] = scanner.nextInt();
//        }
//        // делаем запросы
//        int num_zap = scanner.nextInt();    // кол-во запросов
//        for (int i = 0; i < num_zap; i++) {
//            int li = scanner.nextInt();     // левая граница запроса
//            int ri = scanner.nextInt();     // правая граница запроса
//            int min_num = mass[li-1];
//            for (int j = li; j <= ri; j++) {
//                if (min_num > mass[j-1]) {
//                    min_num = mass[j-1];
//                }
//            }
//            System.out.println(min_num);
//        }
//    }
//}
//Задача N 2025. Массивы. Запросы минимума на подмассиве
//Рассмотрим простой вариант задачи RMQ (Range Minimum Query, запрос минимума на подмассиве).
//
//   Задан массив из n целых чисел (a1, a2,..., an) и m запросов
// вида (li, ri) (1 ≤ li ≤ ri ≤ n). Ваша задача — на каждый запрос вывести
// минимальный среди элементов с индексами от li до ri включительно.
//        Входные данные
//  В первой строке входного файла дано число n (1 ≤ n ≤ 100) — количество
// чисел в массиве. Во второй строке через пробел записаны целые
// числа a1, a2,..., an (1 ≤ ai ≤ 100). В третьей строке входного файла
// дано число m (1 ≤ m ≤ 100) — количество запросов.
// Далее в m строках даны запросы по одному на строке в виде двух целых
// чисел li, ri, разделенных пробелом (1 ≤ li ≤ ri ≤ n).
//        Выходные данные
// Выведите m чисел — ответы на запросы. Числа разделяйте пробелом или
// переводом строки. На запросы следуюет отвечать в том порядке,
// в котором они заданы во входных данных.







//Задача N 2019.  Ветвления, циклы. Треугольная полка.
//import java.util.Scanner;
//public class Solution {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        int book = scanner.nextInt();
//        int polka = 1;
//        int sum = 1;
//        while (book > sum) {
//            polka++;
//            sum += polka;
//        }
//        System.out.println(polka);
//    }
//}
//  Задача N 2019.  Ветвления, циклы. Треугольная полка.
//  Книжная полка имеет треугольную форму: на ее верхней полке помещается одна книга,
//  на второй — две, на третьей — три и т.д. (на i-ой помещается i книг).
//  Все книги занумерованы по полкам сверху вниз, а на одной полке — слева направо.
//  То есть единственная книга на верхней полке имеет номер 1,
//  на второй полке — книги 2 и 3, а на третьей — 4, 5 и 6.
//  Задан номер книги n, необходимо вывести номер полки, на которой она находится.
//     Входные данные
//  В единственной строке входных данных записано целое число n (1 ≤ n ≤ 108).
//     Выходные данные
//  Выведите номер полки, на которой находится книга с номером n.


// Задача N 2009.  Ветвления, циклы. Сумма на степенях двоек
// import java.util.Scanner;
//public class Solution {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        int count = scanner.nextInt();
//        int sum = 0;
//        int step = 1;
//        for (int i = 1; i <= count; i++) {
//            int innum = scanner.nextInt();
//            if (i == step) {
//                sum += innum;
//                step <<= 1;
//            }
//        }
//        System.out.println(sum);
//    }
//}
//  Задача N 2009.  Ветвления, циклы. Сумма на степенях двоек
//  Задана последовательность n целых чисел a1, a2,..., an.
//    Выведите сумму всех ее элементов, стоящих на позициях,
//    которые являются степенями двойки: 1, 2, 4, 8, 16 и т.д.
//
//        Входные данные
//   В первой строке задано целое число n (1 ≤ n ≤ 10000).
//   Вторая строка содержит последовательность целых чисел a1, a2,..., an.
//
//        Выходные данные
//        Выведите искомую сумму.
