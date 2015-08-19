/**
 * 
 */
package topcoder;

import java.util.*;

import topcoder.xBinaryTreePostorderTraversal.TreeNode;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?

confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

Hide Tags Tree Hash Table Stack
Hide Similar Problems (M) Validate Binary Search Tree (M) Binary Tree Preorder Traversal (H) Binary Tree Postorder Traversal (M) Binary Search Tree Iterator (M) Kth Smallest Element in a BST

 * @author Chauncey
 *
 */
public class xBinaryTreeInorderTraversal
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
		res.add(root.val);
		if (root.right != null) {
			dfs(root.right, res);
		}
	}
    public static List<Integer> inorderTraversalRecursive(TreeNode root) {
    	LinkedList<Integer> res = new LinkedList<Integer>();
    	if (root == null) return res;
    	dfs(root, res);
        return res;
    }
    public static List<Integer> inorderTraversal1(TreeNode root) {
    	LinkedList<Integer> res = new LinkedList<Integer>();
    	if (root == null) return res;
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	stack.push(root);
    	TreeNode p = root.left;
    	while (!stack.empty()) {
    		while (p != null) {
    			stack.push(p);
    			p = p.left;
    		}
    		TreeNode q = stack.pop();
    		res.add(q.val);
    		if (q.right != null) {
    			stack.push(q.right);
    			p = q.right.left;
    		}
    	}
        return res;
    }
    public static List<Integer> inorderTraversal(TreeNode root) {
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
		root.right = new TreeNode(2);
		root.right.left = new TreeNode(3);
		System.out.println(inorderTraversalRecursive(root));
		System.out.println(inorderTraversal(root));
		
	}

}
