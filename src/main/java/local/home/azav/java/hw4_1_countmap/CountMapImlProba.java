package local.home.azav.java.hw4_1_countmap;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CountMapImlProba {
    private static final Logger LOG = Logger.getLogger(CountMapImlProba.class.getName());

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
        LOG.log(Level.INFO,"5 > {0}",count);
        LOG.log(Level.INFO, "6 > {0} ", count1);
        LOG.log(Level.INFO,"10 > {0}", count2);
        LOG.log(Level.INFO,"remove 5 > {0}", map.remove(5));
        count = map.getCount(5);
        LOG.log(Level.INFO,"5 > {0}", count);

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
        LOG.log(Level.INFO,"str2 > {0}", count);
        LOG.log(Level.INFO,"str3 > {0}", count1);
        LOG.log(Level.INFO,"str1 > {0}", count2);
    }
}
