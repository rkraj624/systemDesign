package dsa.practice.trees;

import dsa.practice.Constant;

import java.util.ArrayList;
import java.util.List;

public class RightLeftView {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5,6,7};
        Constant.TreeNode node = Constant.buildTree(array, 0);
        List<Integer> ans = new ArrayList<>();
        leftView(node, 0, ans);
        System.out.println("Left View -> "+ans);
        ans.clear();
        rightView(node, 0, ans);
        System.out.println("Right View -> "+ans);
    }

    private static void rightView(Constant.TreeNode node, int h, List<Integer> ans) {
        if (node == null) return;
        if(ans.size() == h){
            ans.add(node.val);
        }
        rightView(node.right, h+1, ans);
        rightView(node.left, h+1, ans);
    }

    private static void leftView(Constant.TreeNode node, int h, List<Integer> ans) {
        if (node == null) return;
        if(ans.size() == h){
            ans.add(node.val);
        }
        leftView(node.left, h+1, ans);
        leftView(node.right, h+1, ans);
    }
}
