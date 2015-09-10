package lintcode;

import java.util.*;

/**
 * Given an integer array with no duplicates. A max tree building on this array is defined as follow:

The root is the maximum number in the array
The left subtree and right subtree are the max trees of the subarray divided by the root number.
Construct the max tree by the given array.

Have you met this question in a real interview? Yes
Example
Given [2, 5, 6, 0, 3, 1], the max tree constructed by this array is:

    6
   / \
  5   3
 /   / \
2   0   1
Challenge
O(n) time and memory.

Tags Expand 
LintCode Copyright Stack Cartesian Tree


Related Problems Expand 
Hard Largest Rectangle in Histogram 24 %
Medium Min Stack 29 %

 * @author Chauncey
 *
 */
public class MaxTree
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
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] A) {
    	if (A==null || A.length==0) return null;
    	LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
    	TreeNode node = new TreeNode(A[0]);
    	stack.push(node);
    	for (int i=1; i<A.length; ++i) {
    		node = new TreeNode(A[i]);
    		TreeNode top = stack.peek();
    		if (node.val > top.val) {
	    		while (node.val > top.val) {
	    			stack.pop();
	    			TreeNode prev = stack.peek();
	    			if (prev != null && node.val > prev.val) {
	    				prev.right = top;
	    				top = prev;
	    			} else {
	    				break;
	    			}
	    		}
	    		node.left = top;
    		}
    		stack.push(node);
    	}
    	TreeNode root = stack.peek();
    	while (!stack.isEmpty()) {
    		stack.pop();
    		TreeNode prev = stack.peek();
    		if (prev != null) {
    			prev.right = root;
    			root = prev;
    		}
    	}
    	return root;
    }
    
    public TreeNode maxHeap(int[] A) {
    	if (A==null || A.length==0) return null;
    	int first_leaf = A.length/2;
    	for (int i=first_leaf-1; i>=0; --i) {
    		max_heapify(A, i);
    	}
    	TreeNode[] nodes = new TreeNode[A.length];
    	nodes[0] = new TreeNode(A[0]);
    	for (int i=0; i<first_leaf; ++i) {
        	int left = (i<<1)+1;
        	int right = (i<<1)+2;
        	if (left < A.length) nodes[i].left = nodes[left] = new TreeNode(A[left]);
        	if (right < A.length) nodes[i].right = nodes[right] = new TreeNode(A[right]);
    	}
    	return nodes[0];
    }
    private final void max_heapify(int[] A, int i) {
    	int left = (i<<1)+1;
    	int right = (i<<1)+2;
    	int largest = i;
    	if (left < A.length && A[left] > A[largest]) {
    		largest = left;
    	}
    	if (right < A.length && A[right] > A[largest]) {
    		largest = right;
    	}
    	if (largest != i) { //swap
    		int tmp = A[largest];
    		A[largest] = A[i];
    		A[i] = tmp;
        	max_heapify(A, largest);
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
		MaxTree solution = new MaxTree();
		TreeNode root = solution.maxTree(new int[]{2, 5, 6, 0, 3, 1});
		print_tree(root, 0);
		
	}

}
