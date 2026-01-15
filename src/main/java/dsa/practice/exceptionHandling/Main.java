package dsa.practice.exceptionHandling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }


        try {
            doOperation();
        } catch (CustomRuntimeException | CustomCompileTimeException e) {
            throw new RuntimeException(e);
        }
    }

    private static void doOperation() throws CustomRuntimeException, CustomCompileTimeException {
        try {
            int a = 10;
            int b = 0;
            int c = a/b;
        } catch (Exception e) {
            throw new CustomRuntimeException(e.getMessage());
        }
    }
}
