package lintcode;

import java.util.*;

import topcoder.BinarySearchTreeIterator.TreeNode;

/**
 * Have you met this question in a real interview? Yes
Example
  1         1
 / \       / \
2   3  => 3   2
   /       \
  4         4
Challenge
Do it in recursion is acceptable, can you do it without recursion?

Tags Expand 
Binary Tree

 * @author Chauncey
 *
 */
public class InvertBinaryTree
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
		@Override
		public String toString() { return String.valueOf(val); }
	}
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void invertBinaryTree_recusive(TreeNode root) {
    	if (null == root) return;
    	TreeNode tmp = root.left;
    	root.left = root.right;
    	root.right = tmp;
    	invertBinaryTree_recusive(root.right);
    	invertBinaryTree_recusive(root.left);
    }
    public void invertBinaryTree_DFS(TreeNode root) {
	    if (root == null) return;
	    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
	    stack.push(root);
	    while (!stack.isEmpty()) {
	    	TreeNode node = stack.pop();
	    	if (node.left!=null) stack.push(node.left);
	    	if (node.right!=null) stack.push(node.right);
	    	TreeNode tmp = node.left;
	    	node.left = node.right;
	    	node.right = tmp;
	    }
    }
    public void invertBinaryTree_BFS(TreeNode root) {
	    if (root == null) return;
	    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
	    queue.offer(root);
	    while (!queue.isEmpty()) {
	    	TreeNode node = queue.poll();
	    	if (node.left!=null) queue.offer(node.left);
	    	if (node.right!=null) queue.offer(node.right);
	    	TreeNode tmp = node.left;
	    	node.left = node.right;
	    	node.right = tmp;
	    }
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
	    root.left = new TreeNode(3);
	    root.left.left = new TreeNode(1);
	    root.left.left.left = new TreeNode(0);
	    root.left.left.right = new TreeNode(2);
	    root.left.right = new TreeNode(4);
	    root.right = new TreeNode(7);
		InvertBinaryTree solution = new InvertBinaryTree();

		print_tree(root, 0);
		solution.invertBinaryTree_BFS(root);
		print_tree(root, 0);
		
	}

}
