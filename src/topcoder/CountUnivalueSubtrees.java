package topcoder;

import java.util.*;

/**
 * Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:

Given binary tree,

1
    5
   / \
  1   5
 / \   \
5   5   5
return 4.

 * @author Chauncey
 *
 */
public class CountUnivalueSubtrees
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        int[] res = new int[1];
        dfs_count(root, res);
        return res[0];
    }

    private final boolean dfs_count(TreeNode root, int[] res) {
    	boolean unival = true;
    	if (root.left != null) {
    		if (dfs_count(root.left, res) == false || root.left.val != root.val) {
    			unival = false;
    		}
    	}
    	if (root.right != null) {
    		if (dfs_count(root.left, res) == false || root.right.val != root.val) {
    			unival = false;
    		}
    	}
    	if (unival) {
    		++res[0];
    	}
    	return unival;
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
		TreeNode root = new TreeNode(5);
	    root.left = new TreeNode(1);
	    root.left.left = new TreeNode(5);
	    root.left.right = new TreeNode(5);
	    root.right = new TreeNode(5);
	    root.right.right = new TreeNode(5);
		print_tree(root, 0);
		CountUnivalueSubtrees solution = new CountUnivalueSubtrees();
		System.out.println(solution.countUnivalSubtrees(root));
		
	}

}
