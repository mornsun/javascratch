/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author cherry
 *
 */
public class BinaryTreePreorderTraversal
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
    public static List<Integer> preorderTraversal(TreeNode root) {
    	LinkedList<Integer> res = new LinkedList<Integer>();
    	if (root == null) return res;
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	stack.push(root);
    	while (!stack.empty()) {
    		TreeNode p = stack.pop();
    		res.add(p.val);
    		if (p.right != null) {
    			stack.push(p.right);
    		}
    		if (p.left != null) {
    			stack.push(p.left);
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
		root.right = new TreeNode(2);
		root.right.left = new TreeNode(3);
		System.out.println(preorderTraversal(root));
		
	}

}
