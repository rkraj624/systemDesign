package dsa.practice.math.recursion;


import java.util.Arrays;

public class ArrayReverse {
    public static void main(String[] args) {
        reverseArray(new int[]{1,2,3,4,5}, 0, 5);
        System.out.println(isPalindrome("MADlM", 0, 5));
    }

    private static boolean isPalindrome(String str, int i, int len){
        if(i >= len/2) return true;
        if(str.charAt(i) != str.charAt(len-i-1)) return false;
        return isPalindrome(str, i+1,len);
    }

    private static void reverseArray(int[] array, int i, int len) {
        if(i >= len/2) {
            System.out.println(Arrays.toString(array));
            return;
        }
        swap(array, i, len-i-1);
        reverseArray(array, i+1, len);
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
