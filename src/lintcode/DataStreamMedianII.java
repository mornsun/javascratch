package lintcode;

import java.util.*;

import lintcode.RemoveNodeinBinarySearchTree.TreeNode;

/**
 * Numbers keep coming, return the median of K numbers at every time a new number added.

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
public class DataStreamMedianII
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
    public void remove(int value) {
        if (_root == null) return;
        //find node
        TreeNode dummy = new TreeNode(0);
        dummy.left = _root;
        TreeNode parent = dummy;
        TreeNode node = _root;
        while (node != null) {
            if (value == node.val) {
                break;
            } else if (value < node.val) {
                parent = node;
                --node.descendents;
                node = node.left;
            } else {
                parent = node;
                --node.descendents;
                node = node.right;
            }
        }
        if (node == null) return; // not find
        //delete and rotate sub tree
        if (node.left == null) {
            update_parent(parent, node, node.right);
        } else if (node.right == null) {
            update_parent(parent, node, node.left);
        } else if (node.left.right == null) {
            node.left.right = node.right;
            node.left.descendents = node.descendents-1;
            update_parent(parent, node, node.left);
        } else if (node.right.left == null) {
            node.right.left = node.left;
            node.right.descendents = node.descendents-1;
            update_parent(parent, node, node.right);
        } else {
            TreeNode next = node.left.right;
            TreeNode next_parent = node.left;
            --next_parent.descendents;
            while (next.right != null) {
                next_parent = next;
                --next_parent.descendents;
                next = next.right;
            }
            next_parent.right = next.left;
            next.left = node.left;
            next.right = node.right;
            next.descendents = node.descendents-1;
            update_parent(parent, node, next);
        }
        _root = dummy.left;
    }
    private final void update_parent(TreeNode parent, TreeNode node, TreeNode upnode) {
        if (node == parent.left) {
            parent.left = upnode;
        } else {
            parent.right = upnode;
        }
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
    public int[] medianII(int[] nums, int k) {
        if (nums == null || nums.length==0) return null;
        _root = null;
        int[] res = new int[nums.length];
        for (int i=0; i<nums.length; ++i) {
            if (i<k) {
                add(nums[i]);
                res[i] = get(i/2).val;
                //System.out.println(res[i]+"("+i+":"+k+")");
            } else {
                remove(nums[i-k]);
                add(nums[i]);
                res[i] = get((k-1)/2).val;
                System.out.println(res[i]+"("+i+":"+nums[i]+":"+nums[i-k]+")");
                print_tree(_root, 0);
            }
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
        DataStreamMedianII solution = new DataStreamMedianII();

        int[] nums = solution.medianII(new int[]{1,18,3,16,5,14,7,12,9,10,11,8,13,6,15,4,17,2,19}, 10);
        for (int num : nums) {
            System.out.print(num+",");
        }
        System.out.println();
        /*nums = solution.medianII(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}, 5);
        for (int num : nums) {
            System.out.print(num+",");
        }
        System.out.println();
        nums = solution.medianII(new int[]{19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1}, 10);
        for (int num : nums) {
            System.out.print(num+",");
        }
        System.out.println();*/
    }

}
