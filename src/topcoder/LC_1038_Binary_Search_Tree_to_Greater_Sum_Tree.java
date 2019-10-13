/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 	Given the root of a binary search tree with distinct values, modify it so that every node has a new value equal to the sum of the values of the original tree that are greater than or equal to node.val.
 *
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * Example 1:
 *
 * Input: [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 *
 * Note:
 *
 * The number of nodes in the tree is between 1 and 100.
 * Each node will have value between 0 and 100.
 * The given tree is a binary search tree.
 *
 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Search Tree to Greater Sum Tree.
 * Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Binary Search Tree to Greater Sum Tree.
 */
public class LC_1038_Binary_Search_Tree_to_Greater_Sum_Tree
{
	public TreeNode bstToGst(TreeNode root) {

		if (root==null) return null;
		dfs(root, 0);
		return root;
	}

	private int dfs(TreeNode root, int addon) {
		if (root == null) return 0;
		int rv = dfs(root.right, addon) + root.val;
		root.val = rv + addon;
		int lv = dfs(root.left, root.val);
		return lv + rv;
	}

	/**
	 * Definition for a binary tree node.
	 */
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
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
		long startTime = System.currentTimeMillis();

		LC_1038_Binary_Search_Tree_to_Greater_Sum_Tree solution = new LC_1038_Binary_Search_Tree_to_Greater_Sum_Tree();
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(1);
		root.left.left = new TreeNode(0);
		root.left.right = new TreeNode(2);
		root.left.right.right = new TreeNode(3);
		root.right = new TreeNode(6);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(7);
		root.right.right.right = new TreeNode(8);
		print_tree(solution.bstToGst(root), 0); //9
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
