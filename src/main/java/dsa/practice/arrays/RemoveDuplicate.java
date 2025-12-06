package dsa.practice.arrays;

public class RemoveDuplicate {
    public static void main(String[] args) {
        removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4});

    }
    public static void removeDuplicates(int[] nums) {
        int j = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i-1] >= nums[i]) {
                continue;
            }
            else{
                j++;
                nums[j] = nums[i];
            }
        }
        for(int k= 0; k <= j; k++){
            System.out.print(nums[k]+" ");
        }
    }
}
