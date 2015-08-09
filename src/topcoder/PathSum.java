/**
 * 
 */
package topcoder;

import java.util.*;

/**
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
