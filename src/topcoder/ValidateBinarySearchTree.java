package topcoder;

import java.util.*;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.


OJ's Binary Tree Serialization:
The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
Hide Tags Tree Depth-first Search
Hide Similar Problems (M) Binary Tree Inorder Traversal

 * @author Chauncey
 *
 */
public class ValidateBinarySearchTree
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        TreeNode prev = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                if (prev != null && prev.val >= cur.val) {
                	return false;
                }
                prev = cur;
                cur = cur.right;
            } else {
                TreeNode node = cur.left;
                while (node.right != null && node.right != cur) {
                    node = node.right;
                }
                if (node.right == null) {
                    node.right = cur;
                    cur = cur.left;
                } else {
                    if (prev != null && prev.val >= cur.val) {
                    	return false;
                    }
                    node.right = null;
                    prev = cur;
                    cur = cur.right;
                }
            }
        }
        return true;
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
		print_tree(root, 0);
		ValidateBinarySearchTree solution = new ValidateBinarySearchTree();

		System.out.println(solution.isValidBST(root));
		
	}

}
