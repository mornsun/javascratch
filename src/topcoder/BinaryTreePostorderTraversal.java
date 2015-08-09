/**
 * 
 */
package topcoder;

import java.util.*;

import topcoder.BinaryTreeInorderTraversal.TreeNode;

/**
 * @author Chauncey
 *
 */
public class BinaryTreePostorderTraversal
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	private final static void dfs(TreeNode root, LinkedList<Integer> res)
	{
		if (root.left != null) {
			dfs(root.left, res);
		}
		if (root.right != null) {
			dfs(root.right, res);
		}
		res.add(root.val);
	}
    public static List<Integer> postorderTraversalRecursive(TreeNode root) {
    	LinkedList<Integer> res = new LinkedList<Integer>();
    	if (root == null) return res;
    	dfs(root, res);
        return res;
    }
    public static List<Integer> postorderTraversal(TreeNode root) {
    	LinkedList<Integer> res = new LinkedList<Integer>();
    	if (root == null) return res;
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	stack.push(root);
		TreeNode r = null;
    	while (!stack.empty()) {
    		TreeNode p = stack.lastElement();
    		if ((p.left == null && p.right == null)
    				|| (r != null && (p.right == r) || (p.left == r && p.right == null))) {
    			r = stack.pop();
        		res.add(r.val);
    		} else {
	    		if (p.right != null) {
	    			stack.push(p.right);
	    		}
	    		if (p.left != null) {
	    			stack.push(p.left);
	    		}
    		}
    	}
        return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		//root.right.left = new TreeNode(3);
		System.out.println(postorderTraversal(root));
		
	}

}
