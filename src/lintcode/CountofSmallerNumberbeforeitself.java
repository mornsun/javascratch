package lintcode;

import java.util.*;

/**
 * Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000) . For each element Ai in the array, count the number of element before this element Ai is smaller than it and return count number array.

Have you met this question in a real interview? Yes
Example
For array [1,2,7,8,5], return [0,1,2,3,2]

Note
We suggest you finish problem Segment Tree Build, Segment Tree Query II and Count of Smaller Number before itself I first.

Tags Expand 
LintCode Copyright Binary Tree Segment Tree


Related Problems Expand 
Medium Count of Smaller Number 18 %

 * @author Chauncey
 *
 */
public class CountofSmallerNumberbeforeitself
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
   /**
     * @param A: An integer array
     * @return: Count the number of element before this element 'ai' is 
     *          smaller than it and return count number array
     */ 
    public ArrayList<Integer> countOfSmallerNumberII(int[] A) {
        _root = null;
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (null == A || A.length == 0) return res;
        for (int query : A) {
            res.add(find(query));
            add(query);
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
        CountofSmallerNumberbeforeitself solution = new CountofSmallerNumberbeforeitself();

        //System.out.println(solution.countOfSmallerNumberII(new int[]{1,2,7,8,5}));
        System.out.println(solution.countOfSmallerNumberII(new int[]{26,78,27,100,33,67,90,23,66,5,38,7,35,23}));
        System.out.println(solution.countOfSmallerNumberII(new int[]{26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41}));
        //0,1,1,3,2,3,5,0,4,0,5,1,6,2,9,2,14,10,17,14,16,7,16,7,22,2,0,25,1,20,29,15,4,6,28,20,20,16,37,18
        
    }

}
