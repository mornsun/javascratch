/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author Chauncey
 *
 */
public class SumRoottoLeafNumbers
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	private final static int dfs(TreeNode root, int num, int sum)
	{
    	if (root == null) {
    		return 0;
    	}
		num = num*10+root.val;
    	if (root.left == null && root.right == null) { //leaf
    		sum += num;
    	} else {
	    	if (root.left != null) {
	        	sum = dfs(root.left, num, sum);
	    	}
	    	if (root.right != null) {
	        	sum = dfs(root.right, num, sum);
	    	}
    	}
    	return sum;
	}
    public static int sumNumbers(TreeNode root) {
    	if (root == null) return 0;
    	int num = 0;
    	int sum = 0;
    	return dfs(root, num, sum);
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		System.out.println(sumNumbers(root));
		
	}

}
