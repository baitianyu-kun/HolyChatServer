package com.baitianyu.HolyChatServer.test;

import java.util.ArrayList;

public class testArray {
    public static void main(String[] args) {
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("nihao");
        arrayList.add("enen");
        arrayList.remove(0);
        arrayList.add(0,"dui");

        for (int i=0;i<arrayList.size();i++)
        {
            System.out.println(arrayList.get(i));
        }
    }
}
