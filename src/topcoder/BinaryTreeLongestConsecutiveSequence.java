package topcoder;

import java.util.*;

/**
 * Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,

   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.

   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.

 * @author Chauncey
 *
 */
public class BinaryTreeLongestConsecutiveSequence
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public int longestConsecutive(TreeNode root) {
		if (null == root) return 0;
		return dfs_longest(root, 0);
    }
	
	private final int dfs_longest(TreeNode root, int downlen) {
		int left_len = root.left == null ? 1 : root.left.val==root.val+1 ? dfs_longest(root.left, downlen+1)+1 : dfs_longest(root.left, 0);
		int right_len = root.right == null ? 1 : root.right.val==root.val+1 ? dfs_longest(root.right, downlen+1)+1 : dfs_longest(root.right, 0);
		return Math.max(left_len, right_len);
	}
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		BinaryTreeLongestConsecutiveSequence solution = new BinaryTreeLongestConsecutiveSequence();
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(2);
		root.right.right = new TreeNode(4);
		root.right.right.right = new TreeNode(5);
		System.out.println(solution.longestConsecutive(root));
		root = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(2);
		root.right.left.left = new TreeNode(1);
		System.out.println(solution.longestConsecutive(root));
	}

}
