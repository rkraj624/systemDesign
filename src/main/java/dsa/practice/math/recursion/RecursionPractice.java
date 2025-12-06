package dsa.practice.math.recursion;

public class RecursionPractice {
    public static void main(String[] args) {
        printNameNTimes(5);
        print1ToN(5);
        System.out.println();
        printNTo1(5);
        System.out.println();
        System.out.println(sum(5));
        sum(5,0);

    }
    private static void printNameNTimes(int n){
        if(n == 0) return;
        System.out.println("My Name is Ravi");
        printNameNTimes(n-1);
    }

    private static void printNTo1(int n){
        if(n == 0) return;
        System.out.print(n+" ");
        printNTo1(n-1);
    }

    private static void print1ToN(int n){
        if(n == 0) return;
        print1ToN(n-1);
        System.out.print(n+" ");
    }

    private static int sum(int n){
        if(n == 0) return 0;
        return n + sum(n-1);
    }

    private static void sum(int i, int sum){
        if(i == 0) {
            System.out.println(sum);
            return;
        }
        sum(i-1, i + sum);
    }
}
