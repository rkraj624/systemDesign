package dsa.practice;

import dsa.practice.trees.BinaryTree;

import java.util.Arrays;

public class Constant {

    public static int[] getArray() {
        return new int[]{19,5,8,28,3,1,10,7,4,2};
    }

    public static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public static void printArray(int[] array){
        System.out.println(Arrays.toString(array));
    }
    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode() {
        }
        public TreeNode(int val) {
            this.val = val;
        }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static TreeNode buildTree(int[] array, int i) {
        TreeNode curr = null;
        if(i < array.length){
            if(array[i] == -1) {
                return null;
            }else{
                curr = new TreeNode(array[i]);
            }
            curr.left = buildTree(array, 2*i + 1);
            curr.right = buildTree(array, 2*i + 2);
        }
        return curr;
    }
}
