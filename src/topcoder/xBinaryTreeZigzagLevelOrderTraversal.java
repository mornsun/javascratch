package topcoder;

import java.util.*;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
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
Hide Tags Tree Breadth-first Search Stack
Hide Similar Problems (E) Binary Tree Level Order Traversal

 * @author Chauncey
 *
 */
public class xBinaryTreeZigzagLevelOrderTraversal
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		LinkedList<List<Integer>> res = new LinkedList<>();
		if (root==null) return res;
		LinkedList<TreeNode> q = new LinkedList<>();
		q.addLast(root);
		boolean toright = false;

		while (!q.isEmpty()) {
			LinkedList<TreeNode> prev = q;
			q = new LinkedList<>();
			LinkedList<Integer> r = new LinkedList<>();
			while(!prev.isEmpty()) {
				TreeNode node = prev.removeLast();
				r.add(node.val);
				if (toright) {
					if (node.right!=null) q.addLast(node.right);
					if (node.left!=null) q.addLast(node.left);
				} else {
					if (node.left!=null) q.addLast(node.left);
					if (node.right!=null) q.addLast(node.right);
				}
			}
			toright = !toright;
			res.add(r);
		}
		return res;

	}

    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
    	ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        boolean zig = true;
        LinkedList<TreeNode> parents = new LinkedList<TreeNode>();
        LinkedList<TreeNode> children = new LinkedList<TreeNode>();
        children.push(root);
        while (!children.isEmpty()) {
        	LinkedList<TreeNode> tmp = children;
        	children = parents;
        	parents = tmp;
        	ArrayList<Integer> path = new ArrayList<Integer>();
        	if (zig) {
	        	while (!parents.isEmpty()) {
	        		TreeNode node = parents.pop();
	        		if (node.left != null) children.push(node.left);
	        		if (node.right != null) children.push(node.right);
	        		path.add(node.val);
	        	}
        	} else {
	        	while (!parents.isEmpty()) {
	        		TreeNode node = parents.pop();
	        		if (node.right != null) children.push(node.right);
	        		if (node.left != null) children.push(node.left);
	        		path.add(node.val);
	        	}
        	}
        	res.add(path);
        	zig = !zig;
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
		xBinaryTreeZigzagLevelOrderTraversal solution = new xBinaryTreeZigzagLevelOrderTraversal();
		TreeNode root = new TreeNode(5);
	    root.left = new TreeNode(3);
	    root.left.left = new TreeNode(1);
	    root.left.left.left = new TreeNode(0);
	    root.left.left.right = new TreeNode(2);
	    root.left.right = new TreeNode(4);
	    root.right = new TreeNode(7);
		//print_tree(root, 0);
		System.out.println(solution.zigzagLevelOrder(root));
		
		root = new TreeNode(1);
	    root.left = new TreeNode(2);
	    root.right = new TreeNode(3);
	    root.left.left = new TreeNode(4);
	    root.right.right = new TreeNode(5);
		System.out.println(solution.zigzagLevelOrder(root));
		
	}

}
