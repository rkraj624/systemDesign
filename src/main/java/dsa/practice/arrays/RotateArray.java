package dsa.practice.arrays;

import dsa.practice.Constant;

import java.util.Arrays;

public class RotateArray {
    public static void main(String[] args) {
        int[] array = Constant.getArray();
        Arrays.sort(array);
//        leftRotateArrayBy1(array);
//        leftRotateArrayByK(array, 3);
        moveZeroes(new int[]{1,0});
    }

    private static void leftRotateArrayByK(int[] array, int k) {
        reverseArray(array,0, k-1);
        reverseArray(array, k, array.length-1);
        reverseArray(array, 0, array.length-1);
        Constant.printArray(array);
    }

    private static void reverseArray(int[] array,int i, int n) {
        for(; i < n; i++){
            Constant.swap(array, i, n);
            n--;
        }
    }

    private static void leftRotateArrayBy1(int[] array) {
        int temp = array[0];
        for(int i = 0; i < array.length-1; i++){
            array[i] = array[i+1];
        }
        array[array.length-1]= temp;
        Constant.printArray(array);
    }

    public static void moveZeroes(int[] nums) {
        int n = nums.length;
        int j = -1;

        for(int i = 0; i < n; i++){
            if(nums[i] == 0) {
                j = i;
                break;
            }
        }
        for(int i = j+1; i < n; i++){
            if(nums[i] != 0){
                Constant.swap(nums, i, j);
                j++;
            }
        }
        Constant.printArray(nums);
    }
}
