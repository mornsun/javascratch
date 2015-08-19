/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

Hide Tags Tree Depth-first Search
Hide Similar Problems (M) Path Sum II (H) Binary Tree Maximum Path Sum (M) Sum Root to Leaf Numbers

 * @author Chauncey
 *
 */
public class PathSum
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
    public static boolean dfs(TreeNode root, int sum) {
    	if (root.left == null && root.right == null) { //leaf
    		if (sum-root.val == 0) return true;
    		else return false;
    	}
    	if (root.left != null) {
        	if (dfs(root.left, sum-root.val))
        		return true;
    	}
    	if (root.right != null) {
        	if (dfs(root.right, sum-root.val))
        		return true;
    	}
    	return false;
    }
	
    public static boolean hasPathSum(TreeNode root, int sum) {
    	if (root == null) {
    		return false;
    	}
    	
    	return dfs(root, sum);
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right = new TreeNode(8);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.right = new TreeNode(1);
		System.out.println(hasPathSum(root, 22));
		
	}

}
