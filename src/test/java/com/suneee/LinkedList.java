package com.suneee;

import java.util.List;

public class LinkedList {
    public static void main(String[] args) {
        List list = new java.util.LinkedList();
        list.add("1");
        list.add(2);
        list.add(null);
        list.add(4);
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }
}
