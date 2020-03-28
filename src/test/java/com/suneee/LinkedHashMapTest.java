package com.suneee;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class LinkedHashMapTest {
    public static void main(String[] args) {
        LinkedHashMap map = new LinkedHashMap();
        map.put(11,11);
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
       // Object o = map.get(null);
       // System.out.println(o);
        Iterator it=map.keySet().iterator();
        while(it.hasNext()){
            String key;
            Object value;
            key=it.next().toString();
            value=map.get(key);
            System.out.println(key+"--"+value);
        }
    }
}
