package dsa.practice.trees;

import java.util.*;

public class VerticalOrderTraversal {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5,6,7};
        TreeNode root = buildTree(array, 0);
        verticalOrderTraversal(root);
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
    public static void verticalOrderTraversal(TreeNode root){
        if(root == null) return;
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> res = new TreeMap<>();
        Queue<Tuple> queue = new LinkedList<>();
        queue.offer(new Tuple(root, 0, 0));
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                Tuple tuple = queue.poll();
                TreeNode node = tuple.node;
                int x = tuple.x;
                int y = tuple.y;
                if(!res.containsKey(x)){
                    res.put(x, new TreeMap<>());
                }
                if(!res.get(x).containsKey(y)){
                    res.get(x).put(y, new PriorityQueue<>());
                }
                res.get(x).get(y).offer(node.val);

                if(node.left != null){
                    queue.offer(new Tuple(node.left, x-1, y+1));
                }
                if(node.right != null){
                    queue.offer(new Tuple(node.right, x+1, y+1));
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        System.out.println(res);
        for(TreeMap<Integer, PriorityQueue<Integer>> ys : res.values()){
            ans.add(new ArrayList<>());
            for(PriorityQueue<Integer> nodes : ys.values()){
                while(!nodes.isEmpty()){
                    ans.get(ans.size()-1).add(nodes.poll());
                }
            }
        }

    }

    public static class TreeNode{
        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class Tuple{
        TreeNode node;
        int x;
        int y;

        public Tuple() {
        }

        public Tuple(TreeNode node) {
            this.node = node;
        }

        public Tuple(TreeNode node, int x, int y) {
            this.node = node;
            this.x = x;
            this.y = y;
        }
    }

}
