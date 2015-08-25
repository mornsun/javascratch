package topcoder;

import java.util.*;

/**
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

Hide Tags Tree Dynamic Programming
Hide Similar Problems (M) Unique Binary Search Trees (M) Different Ways to Add Parentheses

 * @author Chauncey
 *
 */
public class UniqueBinarySearchTreesII
{
	/**
	 * Definition for a binary tree node.
	 */
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	public List<TreeNode> generateTrees(int lo, int hi) {
		LinkedList<TreeNode> res = new LinkedList<TreeNode>();
		if (hi-lo == 0) {
			TreeNode node = new TreeNode(lo);
			res.add(node);
			return res;
		} else if (hi-lo <0) {
			res.add(null);
			return res;
		}
		for (int i=lo; i<=hi; ++i) {
			for (TreeNode nodelesser : generateTrees(lo,i-1)) {
				for (TreeNode nodegreater : generateTrees(i+1,hi)) {
					TreeNode node = new TreeNode(i);
					node.left = nodelesser;
					node.right = nodegreater;
					res.add(node);
				}
			}
		}
		return res;
	}

    public List<TreeNode> generateTrees(int n) {
        if (n==0) {
        	LinkedList<TreeNode> res = new LinkedList<TreeNode>();
        	res.add(null);
        	return res;
        }
        return generateTrees(1,n);
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
		UniqueBinarySearchTreesII solution = new UniqueBinarySearchTreesII();
		
		for (TreeNode root : solution.generateTrees(3)) {
			print_tree(root, 0);
		}
	}

}
