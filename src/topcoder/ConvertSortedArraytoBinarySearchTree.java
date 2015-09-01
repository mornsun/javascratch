package topcoder;

import java.util.*;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

Hide Tags Tree Depth-first Search
Hide Similar Problems (M) Convert Sorted List to Binary Search Tree

 * @author Chauncey
 *
 */
public class ConvertSortedArraytoBinarySearchTree
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	private final TreeNode dfsSortedArrayToBST(int[] nums, int lo, int hi) {
		int m = lo + ((hi-lo)>>1);
		TreeNode node = new TreeNode(nums[m]);
		if (lo <= m-1) node.left = dfsSortedArrayToBST(nums, lo, m-1);
		if (m+1 <= hi) node.right = dfsSortedArrayToBST(nums, m+1, hi);
		return node;
	}

    public TreeNode sortedArrayToBST(int[] nums) {
    	if (nums == null || nums.length==0) return null;
    	return dfsSortedArrayToBST(nums, 0, nums.length-1);
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
		ConvertSortedArraytoBinarySearchTree solution = new ConvertSortedArraytoBinarySearchTree();
	    TreeNode root = solution.sortedArrayToBST(new int[]{1,2,3,4,5,6,7,8});
		print_tree(root, 0);
	}

}
