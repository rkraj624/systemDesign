package dsa.practice.sorting;

import dsa.practice.Constant;

public class RecursiveInsertionSort {
    public static void main(String[] args) {
        int[] array = Constant.getArray();
        insertionSort(array, 0, array.length);
        Constant.printArray(array);
    }

    private static void insertionSort(int[] array, int i, int length) {
        if(i >= length) return;
        int j = i;
        while(j > 0 && array[j-1] > array[j]){
            Constant.swap(array, j-1, j);
            j--;
        }
        insertionSort(array,i+1, length);
    }
}
