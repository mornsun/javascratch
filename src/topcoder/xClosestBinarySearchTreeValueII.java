package topcoder;

import java.util.*;

/**
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 

Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:

Consider implement these two helper functions:
getPredecessor(N), which returns the next smaller node to N.
getSuccessor(N), which returns the next larger node to N.
Try to assume that each node has a parent pointer, it makes the problem much easier.
Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
You would need two stacks to track the path in finding predecessor and successor node separately.

 * @author Chauncey
 *
 */
public class xClosestBinarySearchTreeValueII
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
		@Override
		public String toString() { return String.valueOf(val); }
	}
	private LinkedList<TreeNode> pre_stack = new LinkedList<TreeNode>();
	private LinkedList<TreeNode> suc_stack = new LinkedList<TreeNode>();
	private final TreeNode getPredecessor() {
		if (pre_stack.isEmpty()) return null;
		TreeNode pre_cur = pre_stack.pop();
		if (pre_cur.left != null) {
			TreeNode cur = pre_cur.left;
			pre_stack.push(cur);
			while (cur.right!=null) {
				cur = cur.right;
				pre_stack.push(cur);
			}
			return pre_cur;
		} else {
			return pre_cur;
		}
	}
	private final TreeNode getSuccessor() {
		if (suc_stack.isEmpty()) return null;
		TreeNode suc_cur = suc_stack.pop();
		if (suc_cur.right != null) {
			TreeNode cur = suc_cur.right;
			suc_stack.push(cur);
			while (cur.left!=null) {
				cur = cur.left;
				suc_stack.push(cur);
			}
			return suc_cur;
		} else {
			return suc_cur;
		}
	}

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
    	ArrayList<Integer> res = new ArrayList<Integer>(k);
    	if (null == root) return res;
    	TreeNode node = root;
    	double min = Double.MAX_VALUE;
    	TreeNode min_node = null;
    	pre_stack = new LinkedList<TreeNode>();
    	suc_stack = new LinkedList<TreeNode>();
    	while (node != null) {
    		if (target < node.val) {
				suc_stack.push(node);
    			while (node.left != null && target < node.left.val) {
    				node = node.left;
    				suc_stack.push(node);
    			}
    			double diff = Math.abs(node.val-target);
    			if (diff < min) {
    				min = diff;
    				min_node = node;
    			}
    			node = node.left;
    		} else {
    			pre_stack.push(node);
    			while (node.right != null && target > node.right.val) {
    				node = node.right;
        			pre_stack.push(node);
    			}
    			double diff = Math.abs(node.val-target);
    			if (diff < min) {
    				min = diff;
    				min_node = node;
    			}
    			node = node.right;
    		}
    	}
    	if (target < min_node.val) {
    		while (suc_stack.peek() != min_node) suc_stack.pop();
    	} else {
    		while (pre_stack.peek() != min_node) pre_stack.pop();
    	}
    	System.out.print(pre_stack);
    	System.out.println(suc_stack);
    	TreeNode pre_node = getPredecessor();
    	TreeNode suc_node = getSuccessor();
    	System.out.print(pre_node+":"+suc_node+":"+pre_stack);
    	System.out.println(suc_stack);
    	while (res.size() < k) {
    		if (pre_node == null && suc_node == null) break;
    		double suc_margin = suc_node==null ? Double.MAX_VALUE : suc_node.val-target;
    		double pre_margin = pre_node==null ? Double.MAX_VALUE : target-pre_node.val;
    		if (pre_margin < suc_margin) {
	        	res.add(pre_node.val);
	        	pre_node = getPredecessor();
    		} else {
	        	res.add(suc_node.val);
	        	suc_node = getSuccessor();
    		}
        	System.out.print(pre_node+":"+suc_node+":"+pre_stack);
        	System.out.println(suc_stack);
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
		TreeNode root = new TreeNode(5);
	    root.left = new TreeNode(3);
	    root.left.left = new TreeNode(1);
	    root.left.left.left = new TreeNode(0);
	    root.left.left.right = new TreeNode(2);
	    root.left.right = new TreeNode(4);
	    root.right = new TreeNode(7);
		xClosestBinarySearchTreeValueII solution = new xClosestBinarySearchTreeValueII();

		System.out.println(solution.closestKValues(root, 4.5, 100));
		//print_tree(root, 0);
		
	}

}
