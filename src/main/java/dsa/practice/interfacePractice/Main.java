package dsa.practice.interfacePractice;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        printLog(()-> System.out.println("Hello World"));

        Consumer<Integer> consumer = (Integer i)-> System.out.println(i);
        consumer.accept(10);

        Supplier<Integer> supplier = () -> 10;
        System.out.println(supplier.get());

        Function<Integer, Integer> function = (Integer i)-> i*2;
        System.out.println(function.apply(10));

        Predicate<Integer> predicate = (Integer i)-> i>10;
        System.out.println(predicate.test(10));

        System.out.println(isEven(10, (Integer i)-> i%2==0));
        System.out.println(isPrime(7, (Integer i)-> {
            for(int j=2; j<i; j++){
                if(i%j==0){
                    return false;
                }
            }
            return true;
        }));

        Predicate<Integer> isOdd = (Integer i)-> i%2!=0;
        Predicate<Integer> isPrime = (Integer i)->{
            for(int j=2; j<i; j++){
                if(i%j==0){
                    return false;
                }
            }
            return true;
        };

        System.out.println(isOdd.and(isPrime).test(7));

    }

    public static boolean isEven(int num, Predicate<Integer> predicate){
        return predicate.test(num);
    }

    public static boolean isPrime(int num, Predicate<Integer> predicate){
        return predicate.test(num);
    }

    public static void printLog(Logging logging){
        logging.log();
    }
}

interface Logging{
    void log();
}
