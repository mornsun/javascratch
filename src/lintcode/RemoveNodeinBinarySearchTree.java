package lintcode;

import java.util.*;

/**
 * Given a root of Binary Search Tree with unique value for each node.  Remove the node with given value. If there is no such a node with given value in the binary search tree, do nothing. You should keep the tree still a binary search tree after removal.

Have you met this question in a real interview? Yes
Example
Given binary search tree:

          5

       /    \

    3          6

 /    \

2       4

Remove 3, you can either return:

          5

       /    \

    2          6

      \

         4

or :

          5

       /    \

    4          6

 /   

2

Tags Expand 
LintCode Copyright Binary Search Tree


Related Problems Expand 
Easy Insert Node in a Binary Search Tree 41 %

 * @author Chauncey
 *
 */
public class RemoveNodeinBinarySearchTree
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
     * @param root: The root of the binary search tree.
     * @param value: Remove the node with given value.
     * @return: The root of the binary search tree after removal.
     */
    public TreeNode removeNode(TreeNode root, int value) {
    	if (root == null) return null;
    	//find node
    	TreeNode dummy = new TreeNode(0);
    	dummy.left = root;
    	TreeNode parent = dummy;
    	TreeNode node = root;
    	while (node != null) {
    		if (value == node.val) {
    			break;
    		} else if (value < node.val) {
    			parent = node;
    			node = node.left;
    		} else {
    			parent = node;
    			node = node.right;
    		}
    	}
    	if (node == null) return root; // not find
    	//delete and rotate sub tree
    	if (node.left == null) {
    		update_parent(parent, node, node.right);
    	} else if (node.right == null) {
    		update_parent(parent, node, node.left);
    	} else if (node.left.right == null) {
    		node.left.right = node.right;
    		update_parent(parent, node, node.left);
    	} else if (node.right.left == null) {
    		node.right.left = node.left;
    		update_parent(parent, node, node.right);
    	} else {
    		TreeNode next = node.left.right;
    		TreeNode next_parent = node.left;
    		while (next.right != null) {
    			next_parent = next;
    			next = next.right;
    		}
    		next_parent.right = next.left;
    		next.left = node.left;
    		next.right = node.right;
    		update_parent(parent, node, next);
    	}
    	return dummy.left;
    }
    private final void update_parent(TreeNode parent, TreeNode node, TreeNode upnode) {
		if (node == parent.left) {
			parent.left = upnode;
		} else {
			parent.right = upnode;
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
		root.right = new TreeNode(6);
		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(4);
		RemoveNodeinBinarySearchTree solution = new RemoveNodeinBinarySearchTree();
		root = solution.removeNode(root, 3);
		print_tree(root, 0);
		
	}

}
