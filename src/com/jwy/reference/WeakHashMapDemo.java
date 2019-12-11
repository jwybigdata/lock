package com.jwy.reference;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: jwy
 * @Date: 2019/12/11/10:07
 * @Description:WeakHashMap
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        my_HashMap();
        System.out.println("===============");
        my_WeakHashMap();
    }



    public  static void my_HashMap(){
        Map<Integer,String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "hashmap";

        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map);

    }

    private static void my_WeakHashMap() {
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "WeakHashMap";
        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map+"\t"+map.size());
    }
}
