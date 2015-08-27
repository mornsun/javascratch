/**
 * 
 */
package topcoder;

import java.util.*;

import topcoder.BinaryTreePreorderTraversal.TreeNode;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].

Credits:
Special thanks to @amrsaqr for adding this problem and creating all test cases.

Hide Tags Tree Depth-first Search Breadth-first Search
Hide Similar Problems (M) Populating Next Right Pointers in Each Node

 * @author Chauncey
 *
 */
public class BinaryTreeRightSideView
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
    public List<Integer> rightSideView(TreeNode root) {
    	List<Integer> res = new ArrayList<Integer>(100);
        if (null == root) return res;
    	ArrayList<TreeNode> q_parent = new ArrayList<TreeNode>(1024);
        ArrayList<TreeNode> q_children = new ArrayList<TreeNode>(1024);
        q_children.add(root);
        while (!q_children.isEmpty()) {
        	res.add(q_children.get(q_children.size()-1).val);
        	ArrayList<TreeNode> tmp = q_children;
        	q_children = q_parent;
        	q_parent = tmp;
        	q_children.clear();
        	for (TreeNode node : q_parent) {
        		if (node.left != null) q_children.add(node.left);
        		if (node.right != null) q_children.add(node.right);
        	}
        }
        return res;
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
		BinaryTreeRightSideView solution = new BinaryTreeRightSideView();

		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(2);
		root.right.left = new TreeNode(3);
		System.out.println(solution.rightSideView(root));
		
	}

}
