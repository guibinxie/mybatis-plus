package com.suneee;

import java.util.Hashtable;
import java.util.Iterator;

public class HashTableTest {
    public static void main(String[] args) {
        Hashtable map = new Hashtable();
        map.put(11,null);
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        Object o = map.get(null);
         System.out.println(o);
        /*Iterator it=map.keySet().iterator();
        while(it.hasNext()){
            String key;
            Object value;
            key=it.next().toString();
            value=map.get(key);
            System.out.println(key+"--"+value);
        }*/
    }
}
