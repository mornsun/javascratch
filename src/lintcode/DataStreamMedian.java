package lintcode;

import java.util.*;

/**
 * Numbers keep coming, return the median of numbers at every time a new number added.

Have you met this question in a real interview? Yes
Example
For numbers coming list: [1, 2, 3, 4, 5], return [1, 1, 2, 2, 3].

For numbers coming list: [4, 5, 1, 3, 2, 6, 0], return [4, 4, 4, 3, 3, 3, 3].

For numbers coming list: [2, 20, 100], return [2, 2, 20].

Challenge
Total run time in O(nlogn).

Clarification
What's the definition of Median? - Median is the number that in the middle of a sorted array. If there are n numbers in a sorted array A, the median is A[(n - 1) / 2]. For example, if A=[1,2,3], median is 2. If A=[1,19], median is 1.

Tags Expand 
LintCode Copyright Heap Priority Queue


Related Problems Expand 
Hard Sliding Window Median 14 %
Easy Median 22 %
Hard Median of two Sorted Arrays 21 %

 * @author Chauncey
 *
 */
public class DataStreamMedian
{
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        int descendents = 1;
        TreeNode(int x) { val = x; }
        @Override
        public String toString() { return String.valueOf(val)+":"+String.valueOf(descendents); }
    }
    private TreeNode _root;
    private final void add(int v) {
        TreeNode addnode = new TreeNode(v);
        if (_root == null) _root = addnode;
        TreeNode node = _root;
        while (node != addnode) {
            if (v < node.val) {
                if (node.left == null)
                    node.left = addnode;
                ++node.descendents;
                node = node.left;
            } else {
                if (node.right == null)
                    node.right = addnode;
                ++node.descendents;
                node = node.right;
            }
        }
        //print_tree(_root, 0);
        //System.out.println();
    }
    private final int find(int v) {
        if (_root == null) return 0;
        TreeNode node = _root;
        int nprior = 0;
        while (node != null) {
            //System.out.println(node+" "+nprior);
            if (v <= node.val) {
                node = node.left;
            } else {
                if (node.left == null) ++nprior;
                else nprior += node.left.descendents+1;
                node = node.right;
            }
        }
        return nprior;
    }
    private final TreeNode get(int idx) {
        if (_root == null) return null;
        TreeNode node = _root;
        while (node != null) {
            //System.out.println(node+" "+nprior);
            int nprior = node.left == null ? 0 : node.left.descendents;
            if (idx < nprior) {
                node = node.left;
            } else {
                idx -= nprior;
                if (idx == 0) return node;
                node = node.right;
                --idx;
            }
        }
        return null;
    }
    /**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
        if (nums == null || nums.length==0) return null;
        _root = null;
        int[] res = new int[nums.length];
        for (int i=0; i<nums.length; ++i) {
            add(nums[i]);
            res[i] = get(i/2).val;
        }
        return res;
    }
    
    private static final void print_tree(TreeNode node, int deep) {
        StringBuilder indent = new StringBuilder();
        for (int i=0; i<deep; ++i) {
            indent.append(' ');
        }
        if (node == null) {
            System.out.println(indent+"|-");
        } else {
            System.out.println(indent+"|-"+node);
            if (node.left != null) {
                print_tree(node.left, deep+1);
            }
            if (node.right != null) {
                print_tree(node.right, deep+1);
            }
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        DataStreamMedian solution = new DataStreamMedian();

        int[] nums = solution.medianII(new int[]{1, 2, 3, 4, 5});
        for (int num : nums) {
            System.out.print(num+",");
        }
        System.out.println();
        nums = solution.medianII(new int[]{4, 5, 1, 3, 2, 6, 0});
        for (int num : nums) {
            System.out.print(num+",");
        }
        System.out.println();
        nums = solution.medianII(new int[]{2, 20, 100});
        for (int num : nums) {
            System.out.print(num+",");
        }
        System.out.println();
    }

}
