package dsa.practice.sorting;

import dsa.practice.Constant;

import java.util.Arrays;

import static dsa.practice.Constant.swap;

public class QuickSort {
    /*
    * Divide & Conquer
    * */
    public static void main(String[] args) {
        int[] array = Constant.getArray();
        quickSort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }

    private static void quickSort(int[] array, int low, int high) {
        if(low < high){
            int pivotIdx = partition(array, low, high);
            quickSort(array, low, pivotIdx-1);
            quickSort(array, pivotIdx+1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivotEle = array[high];       // Step 1: Choose last element as pivot
        int j = low;                      // Step 2: j tracks position for smaller elements

        for (int i = low; i < high; i++) { // Step 3: Scan from low to high - 1
            if (array[i] < pivotEle) {
                swap(array, i, j);        // Step 4: Move smaller elements to front
                j++;
            }
        }

        swap(array, j, high);             // Step 5: Place pivot in correct position
        return j;                         // Step 6: Return pivot's final index
    }

}
