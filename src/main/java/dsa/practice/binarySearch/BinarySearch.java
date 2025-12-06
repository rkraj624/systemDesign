package dsa.practice.binarySearch;

@SuppressWarnings({"java:S1135","java:S120"})
public class BinarySearch {
    /*
    * TODO
    *   Peak Element
    *   Kth Missing Positive Number
    *   Painter's Partition and Split Array - Largest Sum
    *   Minimize Max Distance to Gas Station
    *   Median of two Sorted Arrays of Different Sizes
    *   Search a 2D Matrix II
    *   Find a Peak Element II
    *   Median in a Row Wise Sorted Matrix
    * */

    public static void main(String[] args) {
        int[] rotatedSortedArray = {9, 10, 11, 12, 0, 1, 2, 3, 4, 5, 6, 7, 8};
        findMin(rotatedSortedArray);
        findMax(rotatedSortedArray);
    }

    private static void findMin(int[] nums) {
        int low = 0, high = nums.length-1, ans = Integer.MAX_VALUE;
        while(low <= high){
            int mid = (low + high)/2;
            if(nums[low] <= nums[high]){
                ans = Math.min(ans, nums[low]);
            }
            if(nums[low] <= nums[mid]){
                ans = Math.min(ans, nums[low]);
                low = mid + 1;
            }else{
                ans = Math.min(ans, nums[mid]);
                high = mid - 1;
            }
        }
        System.out.println(ans);
    }

    private static void findMax(int[] nums) {
        int low = 0, high = nums.length-1, ans = Integer.MIN_VALUE;
        while(low <= high){
            int mid = (low + high)/2;
            if(nums[low] <= nums[high]){
                ans = Math.max(ans, nums[high]);
            }
            if(nums[low] <= nums[mid]){
                ans = Math.max(ans, nums[mid]);
                low = mid + 1;
            }else{
                ans = Math.max(ans, nums[high]);
                high = mid - 1;
            }
        }
        System.out.println(ans);
    }
}
