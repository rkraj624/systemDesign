package dsa.practice.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MathsConcept {

    public static void main(String[] args) {
        System.out.println(countDigits(83928793));
        System.out.println(reverseNumber(123));
        System.out.println(isPalindrome(1211));
        System.out.println(isArmStrongNumber(153));
        System.out.println(listAllDivisor(3));
        System.out.println(isPrimeNumber(3));
        System.out.println(findGCD(52,10));
    }

    private static int findGCD(int a, int b){
        while (a > 0 && b > 0){
            if(a > b) {
                a = a % b;
            }else{
                b = b % a;
            }
        }
        return a == 0 ? b : a;
    }

    private static boolean isPrimeNumber(int num){
        List<Integer> allDivisor = listAllDivisor(num);
        return allDivisor.size() == 2;
    }

    private static List<Integer> listAllDivisor(int num){
        List<Integer> result = new ArrayList<>();
        int sqrt = (int)Math.sqrt(num);
        for(int i = 1; i <= sqrt ; i++){
            if(num % i == 0){
                result.add(i);
                if(i != num/i){
                    result.add(num/i);
                }
            }
        }
        result.sort((a,b)-> a-b);
        return result;
    }

    private static boolean isArmStrongNumber(int num){
        int result = 0, originalNum = num;
        while(num > 0){
            int rem = num%10;
            result += rem*rem*rem;
            num/=10;
        }
        return result == originalNum;
    }

    private static boolean isPalindrome(int num){
        return reverseNumber(num) == num;
    }

    private static int reverseNumber(int num){
        int revNum = 0;
        while(num > 0){
            int rem = num%10;
            revNum = revNum*10 + rem;
            num/=10;
        }
        return revNum;
    }

    private static int countDigits(int num){
        int count = 0;
        while(num > 0){
            count++;
            num /=10;
        }
        return count;
    }

}
