package local.home.azav.java.hw4_1_countmap;

public class CountMapImlProba {
    public static void main(String[] args) {

        CountMap<Integer> map = new CountMapIml<>();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);
        int count = map.getCount(5); // 2
        int count1 = map.getCount(6); // 1
        int count2 = map.getCount(10); // 3
        System.out.println("5 > " + count);
        System.out.println("6 > " + count1);
        System.out.println("10 > " + count2);
        System.out.println("remove 5 > " + map.remove(5));
        count = map.getCount(5);
        System.out.println("5 > " + count);

        CountMap<String> maps = new CountMapIml<>();
        maps.add("str1");
        maps.add("str1");
        maps.add("str2");
        maps.add("str3");
        maps.add("str2");
        maps.add("str1");
        count = maps.getCount("str2"); // 2
        count1 = maps.getCount("str3"); // 1
        count2 = maps.getCount("str1"); // 3
        System.out.println("str2 > " + count);
        System.out.println("str3 > " + count1);
        System.out.println("str1 > " + count2);
    }
}
