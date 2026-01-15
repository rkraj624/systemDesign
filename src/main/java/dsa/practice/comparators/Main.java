package dsa.practice.comparators;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(10);
        list.add(2);
        list.add(20);
        list.add(3);
        list.add(0);

        list.sort((a,b)-> b-a);
        System.out.println(list);
        list.sort((a,b)-> a-b);
        System.out.println(list);
        list.sort((a,b)->b.compareTo(a));
        System.out.println(list);
    }
}