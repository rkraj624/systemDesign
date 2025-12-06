package dsa.practice.sorting;
import dsa.practice.Constant;

import java.util.Arrays;

import static dsa.practice.Constant.swap;

public class Sorting {
    public static void main(String[] args) {
        selectionSort(Constant.getArray());
        bubbleSort(Constant.getArray());
        insertionSort(Constant.getArray());
    }
    private static void selectionSort(int[] array) {
        int n = array.length;
        for(int i = 0; i < n-1; i++){
            int min = i;
            for(int j = i; j < n; j++){
                if(array[j] < array[min]){
                    min = j;     // find minimum & swap with starting index
                }
            }
            swap(array, i, min);
        }
        System.out.println(Arrays.toString(array));
    }
    private static void bubbleSort(int[] array){
        int n = array.length;
        for(int i = 0; i < n; i++){
            boolean swaped = false;
            for(int j = 0; j < n-i-1; j++){
                if(array[j] > array[j+1]){    // push the max element to last
                    swap(array, j, j+1);
                    swaped = true;
                }
            }
            if(!swaped) break;
        }
        System.out.println(Arrays.toString(array));
    }

    private static void insertionSort(int[] array){
        int n = array.length;
        for(int i = 0; i < n; i++){
            int j = i;
            while(j > 0 && array[j-1] > array[j]){
                swap(array, j, j-1);
                j--;
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
