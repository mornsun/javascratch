/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?

Hide Tags Tree Stack
Hide Similar Problems (M) Binary Tree Inorder Traversal (M) Verify Preorder Sequence in Binary Search Tree

 * @author Chauncey
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
