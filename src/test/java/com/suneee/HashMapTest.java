package com.suneee;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put(null,null);
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        Object o = map.get(null);
        System.out.println(o);
       /* Iterator it=map.keySet().iterator();
        while(it.hasNext()){
            String key;
            Object value;
            key=it.next().toString();
            value=map.get(key);
            System.out.println(key+"--"+value);
        }*/

    }
}
