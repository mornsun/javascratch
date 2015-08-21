package topcoder;

import java.util.*;

/**
 * Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.

Hide Tags Tree Depth-first Search
Hide Similar Problems (E) Path Sum (M) Sum Root to Leaf Numbers

 * @author Chauncey
 *
 */
public class BinaryTreeMaximumPathSum
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
    public static void dfs(TreeNode root, int[] res ) {
    	if (root == null) {
    		res[0] = 0; res[1] = 0;
    		return;
    	}
    	if (root.left == null && root.right == null) { //leaf
    		res[0] = root.val;
    		res[1] = root.val;
    	} else {
    		int max0 = root.val;
    		int max1 = root.val;
    		int total = root.val;
	    	if (root.left != null) {
	    		int[] resL = new int[2];
	        	dfs(root.left, resL);
	        	if (root.val+resL[0] > max0) {
	        		max0 = root.val+resL[0];
	        	}
	        	if (resL[1] > max1) {
	        		max1 = resL[1];
	        	}
	        	total += resL[0];
	    	}
	    	if (root.right != null) {
	    		int[] resR = new int[2];
	        	dfs(root.right, resR);
	        	if (root.val+resR[0] > max0) {
	        		max0 = root.val+resR[0];
	        	}
	        	if (resR[1] > max1) {
	        		max1 = resR[1];
	        	}
	        	total += resR[0];
	    	}
	    	if (total>max1) {
	    		max1 = total;
	    	}
	    	res[0] = max0;
	    	res[1] = max1>max0?max1:max0;
    	}
    	//System.out.println("IN:"+root.val+":"+res[0]+":"+res[1]);
    }

    public static int maxPathSum(TreeNode root) {
    	int[] res = new int[2];
    	dfs(root, res);
    	System.out.println("RES:"+res[0]+":"+res[1]);
    	return res[0] > res[1] ? res[0] : res[1];
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		/*TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right = new TreeNode(8);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.left = new TreeNode(5);
		root.right.right.right = new TreeNode(1);*/
		TreeNode root = new TreeNode(-1);
		root.right = new TreeNode(9);
		root.right.left = new TreeNode(-6);
		root.right.right = new TreeNode(3);
		root.right.right.right = new TreeNode(-2);
		System.out.println(maxPathSum(root));
		
	}

}
