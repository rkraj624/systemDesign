package dsa.practice.sorting;

import dsa.practice.Constant;

import java.util.Arrays;

public class MergeSort {
/*
* @Time Complexity O(NlogN)
* @Space Complexity O(N) for array creation
* */
    public static void main(String[] args) {
        int[] array = Constant.getArray();
        mergeSort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }

    private static void mergeSort(int[] array, int low, int high) {
        if(low >= high) return;
        int mid = (low+high)/2;
        mergeSort(array, low, mid);
        mergeSort(array, mid+1, high);
        merge(array, low, mid, high);/**/
    }

    private static void merge(int[] array, int low, int mid, int high) {
        int[] finalSortedArray = new int[array.length];
        int left = low, right = mid+1;
        int i = 0;
        while (left <= mid && right <= high){
            if(array[left] < array[right]){
                finalSortedArray[i] = array[left];
                i++;
                left++;
            }else {
                finalSortedArray[i] = array[right];
                i++;
                right++;
            }
        }
        while (left <= mid){
            finalSortedArray[i] = array[left];
            i++;
            left++;
        }
        while (right <= high){
            finalSortedArray[i] = array[right];
            i++;
            right++;
        }
        /*
        * this needs to be understood again
        * */
        for(int idx = low; idx <= high; idx++){
            array[idx] = finalSortedArray[idx-low];
        }
        System.out.println(low+"  "+high+"  "+Arrays.toString(array));
    }
}
