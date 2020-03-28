package com.suneee;

import java.util.ArrayList;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("1");
        list.add(2);
        list.add(null);
        list.add(4);
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }
}
