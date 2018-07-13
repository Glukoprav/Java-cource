package local.home.azav.java.hw16_jit_gc;

import java.util.HashMap;
import java.util.Map;

public class MainJit {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (int i = 0; i < 100000  ; i++) {
            map.put(i,"value "+i);
        }
        //System.out.println(map.values().toString());
    }
}
