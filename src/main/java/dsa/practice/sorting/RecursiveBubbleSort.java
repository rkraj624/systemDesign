package dsa.practice.sorting;

import dsa.practice.Constant;

public class RecursiveBubbleSort {
    public static void main(String[] args) {
        int[] array = Constant.getArray();
        bubbleSort(array, 0, array.length);
        Constant.printArray(array);
    }

    private static void bubbleSort(int[] array, int i, int length) {
        if(i >= length-1) return;
        boolean swapped = false;
        for(int j = 0; j < length-i-1; j++){
            if(array[j] > array[j+1]){
                Constant.swap(array, j, j+1);
                swapped = true;
            }
        }
        if(!swapped) return;
        bubbleSort(array, i+1, length);
    }
}
