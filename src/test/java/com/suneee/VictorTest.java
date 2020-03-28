package com.suneee;

import java.util.Vector;

public class VictorTest {
    public static void main(String[] args) {
        Vector list = new Vector();
        list.add("1");
        list.add(2);
        list.add(null);
        list.add(4);
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }
}
