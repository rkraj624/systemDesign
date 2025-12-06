package dsa.practice.arrays;

import dsa.practice.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReplaceArray {
    public static void main(String[] args) {
//        leaders(new int[]{17,18,5,4,6,1});
        merge(new int[][]{{1,4},{4,5},{2,5}});
    }
    public static void replaceElements(int[] arr) {
        int n = arr.length;
        int max = arr[n-1], curr = 0;
        arr[n-1] = -1;
        for(int i = n-1; i > 0 ; i--){
            curr = arr[i-1];
            max = Math.max(arr[i], max);
            arr[i-1] = max;
            max = Math.max(max, curr);
        }
        Constant.printArray(arr);
    }
    public static void leaders(int arr[]) {
        ArrayList<Integer> res = new ArrayList<>();
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        for(int i = n-1; i >= 0; i--){
            if(arr[i] > max){
                max = arr[i];
                res.add(arr[i]);
            }
        }
        Collections.sort(res, (a,b) -> b-a);
        System.out.println(res);

    }

    public static void merge(int[][] nums) {
        Arrays.sort(nums, (a, b)->a[0]-b[0]);
        List<int[]> res = new ArrayList<>();
        int start = nums[0][0];
        int end = nums[0][1];
        for(int[] i : nums){
            if(i[0] <= end){
                end = Math.max(end, i[1]);
            }else{
                res.add(new int[]{start, end});
                start = i[0];
                end = i[1];
            }
        }
        System.out.println(res);
    }
}
