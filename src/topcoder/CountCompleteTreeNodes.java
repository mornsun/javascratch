package topcoder;

import java.util.*;

/**
 * Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia: https://en.wikipedia.org/wiki/Binary_tree#Types_of_binary_trees
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Hide Tags Tree Binary Search

 * @author Chauncey
 *
 */
public class CountCompleteTreeNodes
{
	/**
	 * Definition for a binary tree node.
	 */
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        TreeNode node = root;
        int depth = 1;
        int res = 0;
        while (node.left != null) {
        	++depth;
        	node = node.left;
        }
        for (int i=1, num=1; i<depth; ++i, num<<=1) { //sum of depth-1 level
        	res += num;
        }
    	int root_depth = 1;
        while (root != null) {
        	node = root.right;
        	int d = root_depth;
        	int num = 1;
        	while (node != null) {
        		++d;
        		num <<= 1;
        		node = node.left;
        	}
        	num >>= 1;
        	if (d == depth) {
        		root = root.right;
        		res += num;
        	} else {
        		root = root.left;
        	}
        	++root_depth;
        }
        return res+1; //plus last checked node
    }
    
    private static final void print_tree(TreeNode node, int deep) {
    	StringBuilder indent = new StringBuilder();
    	for (int i=0; i<deep; ++i) {
    		indent.append(' ');
    	}
    	if (node == null) {
    		System.out.println(indent+"|-");
    	} else {
    		System.out.println(indent+"|-"+node.val);
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
		CountCompleteTreeNodes solution = new CountCompleteTreeNodes();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(3);
		root.right.right = new TreeNode(3);
		System.out.println(solution.countNodes(root));
	}

}
