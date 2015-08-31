/**
 * 
 */
package topcoder;

import java.util.*;

import topcoder.BinaryTreeRightSideView.TreeNode;

/**
 * Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.

Hide Tags Tree Depth-first Search

 * @author Chauncey
 *
 */
public class FlattenBinaryTreetoLinkedList
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
    public void flatten(TreeNode root) {
        if (root == null) return;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
            	//_iterlist.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode node = cur.left;
                while (node.right != null && node.right != cur) {
                    node = node.right;
                }
                if (node.right == null) {
                	//System.out.println(node.val+":"+cur.val);
                    node.right = cur;
                    cur = cur.left;
                } else {
                	//_iterlist.add(cur.val);
                	//System.out.println(node.val+"-"+cur.val);
                    node.right = cur.right;
                    TreeNode next = cur.right;
                    cur.right = cur.left;
                    cur.left = null;
                    cur = next;
                }
            }
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
		FlattenBinaryTreetoLinkedList solution = new FlattenBinaryTreetoLinkedList();
		TreeNode root = new TreeNode(5);
	    root.left = new TreeNode(3);
	    root.left.left = new TreeNode(1);
	    root.left.left.left = new TreeNode(0);
	    root.left.left.right = new TreeNode(2);
	    root.left.right = new TreeNode(4);
	    root.right = new TreeNode(7);
		solution.flatten(root);
		print_tree(root, 0);
		
	}

}
