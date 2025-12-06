package dsa.practice.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@SuppressWarnings({"java:S1135","java:S106"})
public class BinaryTree {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
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
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5,6,7};
        TreeNode root = buildTree(array,0);
        preOrderTraversal(root);
        System.out.println();
        inOrderTraversal(root);
        System.out.println();
        postOrderTraversal(root);
        System.out.println("Level Order Traversal");
        levelOrderTraversal(root);
    }

    private static void levelOrderTraversal(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> list = new ArrayList<>();
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode treeNode = queue.poll();
                if(treeNode != null){
                    if(treeNode.left != null ) queue.offer(treeNode.left);
                    if(treeNode.right != null ) queue.offer(treeNode.right);
                    temp.add(treeNode.val);
                }
            }
            list.add(temp);
        }
        System.out.println(list);
    }

    private static void postOrderTraversal(TreeNode root) {
        if(root == null) return;
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.val+" ");
    }

    private static void inOrderTraversal(TreeNode root) {
        if(root == null) return;
        postOrderTraversal(root.left);
        System.out.print(root.val+" ");
        postOrderTraversal(root.right);
    }

    private static void preOrderTraversal(TreeNode root) {
        if(root == null) return;
        System.out.print(root.val+" ");
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
    }

    private static TreeNode buildTree(int[] array, int i) {
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
