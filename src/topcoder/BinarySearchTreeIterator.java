package topcoder;

import java.util.*;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.

Hide Tags Tree Stack
Hide Similar Problems (M) Binary Tree Inorder Traversal (M) Flatten 2D Vector

 * @author Chauncey
 *
 */
public class BinarySearchTreeIterator
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	private LinkedList<Integer> _iterlist = new LinkedList<Integer>();
	
    public BinarySearchTreeIterator(TreeNode root) {
        if (root == null) return;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
            	_iterlist.add(cur.val);
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
                	_iterlist.add(cur.val);
                    node.right = null;
                    cur = cur.right;
                }
            }
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
    	return !_iterlist.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        return _iterlist.pollFirst();
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
		BinarySearchTreeIterator solution = new BinarySearchTreeIterator(root);

		while (solution.hasNext()) System.out.println(solution.next());
		print_tree(root, 0);
		
	}

}
