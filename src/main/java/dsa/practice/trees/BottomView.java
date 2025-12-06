package dsa.practice.trees;

import dsa.practice.Constant;

import java.util.*;

public class BottomView {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5,6,7};
        Constant.TreeNode node = Constant.buildTree(array, 0);
        bottomView(node);
    }
    public static class Pair{
        Constant.TreeNode node;
        int height;

        public Pair(Constant.TreeNode node, int height) {
            this.node = node;
            this.height = height;
        }
    }
    private static void bottomView(Constant.TreeNode node) {
        if(node == null) return;
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(node, 0));
        while (!queue.isEmpty()){
            Pair pair = queue.poll();
            int height = pair.height;
            Constant.TreeNode currNode = pair.node;

            map.put(height, currNode.val);

            if(currNode.left != null){
                queue.offer(new Pair(currNode.left, height-1));
            }
            if(currNode.right != null){
                queue.offer(new Pair(currNode.right, height+1));
            }
        }
        for (Integer key : map.keySet()){
            ans.add(map.get(key));
        }
        System.out.println(ans);
    }
}
