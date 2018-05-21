package local.home.azav.java;


// Задача N 2057. Структуры данных. Множество
// Ваша задача — реализовать структуру данных, которая умеет хранить
// мультимножество натуральных чисел, т.е. в этой структуре могут одновременно
// храниться несколько равных элементов.
// Эта структура должна поддерживать две операции:
//    добавить элемент x в множество
//    удалить минимальный элемент в множестве и вернуть его
//  значение (если минимальных элементов несколько, то удаляется только один из них)
//        Входные данные
// Первая строка входных данных содержит число n (1 ≤ n ≤ 106) — количество операций.
// Далее в n строках даны описания операций над множеством.
// Описание представляет собой число — тип запроса (1 или 2) и число x (1 ≤ x ≤ 109)
// если это запрос первого типа.
//        Выходные данные
// Для каждого запроса второго типа выведите результат на отдельной строке.
//
//      Пример(ы)
//        input.txt
//        6
//        1  3
//        1  2
//        1  1
//        2
//        2
//        2
//        output.txt
//        1
//        2
//        3
//
//        input.txt
//        8
//        1  1
//        1  1
//        2
//        1  1
//        1  2
//        2
//        2
//        2
//        output.txt
//        1
//        1
//        1
//        2
// Задача N 2057. Структуры данных. Множество
import java.util.*;
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeMap<Long,Integer> treeMap = new TreeMap<>();
        int oper_cou = scanner.nextInt();
        for (int i = 1; i <= oper_cou; i++) {
            int oper = scanner.nextInt();
            if (oper == 1) {
                long inum = scanner.nextInt();
                inum = inum*2200L+i;
                treeMap.put(inum,i);
            } else {
                long delkey = treeMap.firstKey();
                int val = treeMap.get(delkey);
                treeMap.remove(delkey);
                System.out.println((delkey-val)/2200);
            }
        }
    }
}

// Задача N 2056. Структуры данных. Самое популярное слово. Версия 3.
//import java.util.*;
//public class Solution {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        ArrayList<String> arrayList = new ArrayList<>();
//        do {
//            arrayList.add(scanner.next().toLowerCase());
//        } while (scanner.hasNext());
//        Collections.sort(arrayList);
//        int sizeAr = arrayList.size();
//        int[] arrCount = new int[sizeAr];
//        for (int i = 0; i < sizeAr; i++) {
//            int indWord = arrayList.indexOf(arrayList.get(i));
//            arrCount[i] = indWord;
//        }
//        int cou = 0;
//        for (int i = sizeAr-1; i >= 0; i--) {
//            if (arrCount[i] == i && cou == 0) {
//                arrCount[i] = 1;
//            } else if (arrCount[i] != i) {
//                arrCount[i] = 0;
//                cou ++;
//            } else if (arrCount[i] == i && cou != 0) {
//                arrCount[i] = cou + 1;
//                cou = 0;
//            }
//        }
//        cou = 0;
//        for (int i = 0; i < sizeAr; i++) {
//            if (cou < arrCount[i]) {
//                cou = arrCount[i];
//            }
//        }
//        for (int i = 0; i < sizeAr; i++) {
//            if (arrCount[i] == cou) {
//                System.out.println(arrayList.get(i));
//            }
//        }
//    }
//}
// Задача N 2056. Структуры данных. Самое популярное слово. Версия 1.
//import java.util.*;
//public class Solution {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
//        int oper_cou = scanner.nextInt();
//        for (int i = 1; i <= oper_cou; i++) {
//            int oper = scanner.nextInt();
//            if (oper == 1) {
//                int inum = scanner.nextInt()*2200+i;
//                treeMap.put(inum,i);
//            } else {
//                Integer ikey = 0;
//                Integer delkey = treeMap.ceilingKey(ikey);
//                Integer val = treeMap.get(delkey);
//                treeMap.remove(delkey);
//                System.out.println((delkey-val)/2200);
//            }
//        }
//    }
//}
// Задача N 2056. Структуры данных. Самое популярное слово. Версия 2.
//import java.util.*;
//public class Solution {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        ArrayList<Integer> arrList = new ArrayList<>(110);
//        int oper_cou = scanner.nextInt();
//        for (int i = 1; i <= oper_cou; i++) {
//            int oper = scanner.nextInt();
//            if (oper == 1) {
//                int inum = scanner.nextInt();
//                arrList.add(inum);
//            } else {
//                Collections.sort(arrList);
//                int ikey = arrList.get(0);
//                arrList.remove(0);
//                System.out.println(ikey);
//            }
//        }
//    }
//}


// Задача N 2056. Структуры данных. Самое популярное слово
// Дан текст, ваша задача — найти слово, которое встречается в тексте наибольшее
// количество раз. Текст состоит только из латинских букв, пробелов, переводов строк.
//   Слово — это последовательность подряд идущих латинских букв,
// регистр не имеет значения.
// Если искомых слов несколько, ваша задача — найти все такие слова.
//        Входные данные
// Входные данные состоят только из латинских букв, пробелов и символов
// перевода строки. Гарантируется, что хотя бы одно слово в текст присутствует.
//        Выходные данные
// Выведите все слова, которые встречаются наибольшее количество раз,
// при их следут приводить к нижнему регистру, каждое слово выводите
// на отдельной строке. Слова выводите в лексикографическом порядке.
// Размер входного файла не превосходит 100 Кб.
//        Пример(ы)
//  Sebastian Vettel is a German Formula One racing driver currently driving for Red Bull Racing
//    racing
//
//  a aa aaa aaaa
//  a aa aaa bbbb
//    a
//    aa
//    aaa
//import java.util.*;
//public class Solution {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        ArrayList<String> arrayList = new ArrayList<>();
//        do {
//            arrayList.add(scanner.next().toLowerCase());
//        } while (scanner.hasNext());
//        Collections.sort(arrayList);
//        int sizeAr = arrayList.size();
//        int[] arrCount = new int[sizeAr];
//        for (int i = 0; i < sizeAr; i++) {
//            int indWord = arrayList.indexOf(arrayList.get(i));
//            arrCount[i] = indWord;
//        }
//        int cou = 0;
//        for (int i = sizeAr-1; i >= 0; i--) {
//            if (arrCount[i] == i && cou == 0) {
//                arrCount[i] = 1;
//            } else if (arrCount[i] != i) {
//                arrCount[i] = 0;
//                cou ++;
//            } else if (arrCount[i] == i && cou != 0) {
//                arrCount[i] = cou + 1;
//                cou = 0;
//            }
//        }
//        cou = 0;
//        for (int i = 0; i < sizeAr; i++) {
//            if (cou < arrCount[i]) {
//                cou = arrCount[i];
//            }
//        }
//        for (int i = 0; i < sizeAr; i++) {
//            if (arrCount[i] == cou) {
//                System.out.println(arrayList.get(i));
//            }
//        }
//    }
//}


//Задача N 2037. Строки. Слишком короткие слова. Версия 2. Тест.
//public class Solution {
//    public static void main(String[] args) {
////        Solution sol = new Solution();
////        Scanner scanner = new Scanner(System.in);
////
////        StringBuilder strin = new StringBuilder(scanner.next());
////        int min_word = scanner.nextInt();
////        System.out.println(sol.sol2037(strin, min_word)) ;
//        Person pm1 = new Person(true, "Man1");
//        Person pw1 = new Person(false, "Woo1");
//        System.out.println("1 step");
//        System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
//        System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());
//
//        System.out.println("2 step");
//        if (pm1.marry(pw1)) {
//            System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
//            System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());
//        } else {
//            System.out.println("not marry");
//            System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
//            System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());
//        }
//
//        System.out.println("3 step");
//        if (pm1.marry(pw1)) {
//            System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
//            System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());
//        } else {
//            System.out.println("no");
//            System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
//            System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());
//        }
//
//        System.out.println("4 step");
//        Person pm2 = new Person(true, "Man2");
//        Person pw2 = new Person(false, "Woo2");
//        System.out.println(pm2.marry(pm1));
//        System.out.println(pm2.getName() + " on " + pm2.getNameSpouse());
//        System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());
//        System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
//        System.out.println(pw2.getName() + " on " + pw2.getNameSpouse());
//
//        System.out.println("5 step");
//        System.out.println(pm2.marry(pw1));
//        System.out.println(pm2.getName() + " on " + pm2.getNameSpouse());
//        System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());
//        System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
//        System.out.println(pw2.getName() + " on " + pw2.getNameSpouse());
//
//        System.out.println("6 step");
//        System.out.println(pw2.marry(pm1));
//        System.out.println(pm2.getName() + " on " + pm2.getNameSpouse());
//        System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());
//        System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
//        System.out.println(pw2.getName() + " on " + pw2.getNameSpouse());
//
//        System.out.println("7 step");
//        System.out.println(pw2.marry(pm2));
//        System.out.println(pm2.getName() + " on " + pm2.getNameSpouse());
//        System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());
//        System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
//        System.out.println(pw2.getName() + " on " + pw2.getNameSpouse());
//    }
//
//
//    //Задача N 2037. Строки. Слишком короткие слова. Версия 2. Итоговая.
//    public String sol2037(StringBuilder strin, int min_word) {
//        if (min_word == 0) {
//            return strin.toString();
//        } else {
//            char ch;
//            int posLeft = 0, posRight = 0;
//            while (strin.charAt(posRight) == ',') {
//                posRight++;
//                if (posRight == strin.length()) {
//                    break;
//                }
//            }
//            strin.delete(posLeft, posRight);
//            posLeft = 0;
//            posRight = 0;
//            int length = strin.length();
//            while (posRight < length) {
//                ch = strin.charAt(posRight);
//                if (ch != ',') {
//                    posRight++;
//                } else if (ch == ',' & (posRight - posLeft) >= min_word) {
//                    posRight++;
//                    posLeft = posRight;
//                } else if (ch == ',' & (posRight == posLeft)) {
//                    strin.delete(posLeft, posRight + 1);
//                    length = strin.length();
//                } else if (ch == ',' & (posRight - posLeft) < min_word) {
//                    strin.delete(posLeft, posRight + 1);
//                    length = strin.length();
//                    posRight = posLeft;
//                }
//            }
//            length = strin.length();
//            if (length > 0 && strin.charAt(length - 1) == ',') {
//                strin.delete(length - 1, length);
//            } else if (length > 0 && length < min_word) {
//                strin.delete(0, length);
//            } else if (length > 0 && length > min_word && (posRight - posLeft) < min_word) {
//                strin.delete(posLeft - 1, posRight);
//            }
//            return strin.toString();
//        }
//    }
//}

//    public String sol2037(StringBuilder strin, int min_word) {
//        if (min_word == 0) {
//            return strin.toString();
//        } else {
//            char ch;
//            int posLeft = 0, posRight = 0;
//            while (strin.charAt(posRight) == ',') {
//                posRight++;
//                if (posRight == strin.length()) {
//                    break;
//                }
//            }
//            strin.delete(posLeft,posRight);
//            posLeft = 0;
//            posRight = 0;
//            int length = strin.length();
//            while (posRight < length) {
//                ch = strin.charAt(posRight);
//                if (ch != ',') {
//                    posRight++;
//                } else if (ch == ',' & (posRight - posLeft) >= min_word) {
//                    posRight++;
//                    posLeft = posRight;
//                } else if (ch == ',' & (posRight == posLeft)) {
//                    strin.delete(posLeft,posRight+1);
//                    length = strin.length();
//                } else if (ch == ',' & (posRight - posLeft) < min_word) {
//                    strin.delete(posLeft,posRight+1);
//                    length = strin.length();
//                    posRight = posLeft;
//                }
//            }
//            length = strin.length();
//            if (length > 0 && strin.charAt(length-1) == ',') {
//                strin.delete(length-1,length);
//            }
//            return strin.toString();
//        }
//    }


//Задача N 2037. Строки. Слишком короткие слова. Версия 1.
//import java.util.Scanner;
//public class Solution_old {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        String strin = scanner.next();
//        StringBuffer strout = new StringBuffer(1010);
//        int min_word = scanner.nextInt();
//        if (min_word == 0) {
//            strout.append(strin);
//        } else {
//            int count = 0;
//            int first = 0;
//            char ch;
//            StringBuffer ch_buf = new StringBuffer(1010);
//            while (strin.charAt(first) == ',') {
//                first++;
//            }
//            for (int i = first; i < strin.length();i++) {
//                ch = strin.charAt(i);
//                if (ch != ',') {
//                    ch_buf.insert(count,ch);
//                    count++;
//                } else if ((ch == ',') & (count < min_word)) {
//                    count = 0;
//                } else if (ch == ',' & count >= min_word) {
//                    if (strout.length() > 0) {
//                        strout.append(',');
//                    }
//                    strout.append(ch_buf.substring(0,count));
//                    count = 0;
//                }
//            }
//            if (count > 0 & count >= min_word) {
//                if (strout.length() > 0) {
//                    strout.append(',');
//                }
//                strout.append(ch_buf.substring(0,count));
//            }
//        }
//        System.out.println(strout);
//    }
//}


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
