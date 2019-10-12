/**
 * 
 */
package topcoder;

/**
 * 	Given the root of a binary tree, consider all root to leaf paths: paths from the root to any leaf.  (A leaf is a node with no children.)
 *
 * A node is insufficient if every such root to leaf path intersecting this node has sum strictly less than limit.
 *
 * Delete all insufficient nodes simultaneously, and return the root of the resulting binary tree.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
 * Output: [1,2,3,4,null,null,7,8,9,null,14]
 *
 * Example 2:
 *
 * Input: root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
 * Output: [5,4,8,11,null,17,4,7,null,null,null,5]
 *
 * Example 3:
 *
 * Input: root = [1,2,-3,-5,null,4,null], limit = -1
 * Output: [1,null,-3,4]
 *
 * Note:
 *
 * The given tree will have between 1 and 5000 nodes.
 * -10^5 <= node.val <= 10^5
 * -10^9 <= limit <= 10^9
 *
 * @author Chauncey
 * Runtime: 2 ms, faster than 84.12% of Java online submissions for Letter Tile Possibilities.
 * Memory Usage: 34.3 MB, less than 100.00% of Java online submissions for Letter Tile Possibilities.
 */
public class LC_1080_Insufficient_Nodes_in_Root_to_Leaf_Paths
{
	/**
	 * Definition for a binary tree node.*/
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	public TreeNode sufficientSubset(TreeNode root, int limit) {

		if (dfs(root, limit)) return root;
		return null;

	}

	private boolean dfs(TreeNode root, int limit) {

		if (root==null) return limit<=0;

		limit -= root.val;
		if (root.left==null && root.right==null)
			return limit<=0;
		if (root.left==null)
			return dfs(root.right, limit);
		if (root.right==null)
			return dfs(root.left, limit);
		boolean resl = dfs(root.left, limit);
		boolean resr = dfs(root.right, limit);
		if (!resl && !resr) return false;
		if (!resl) root.left = null;
		else if (!resr) root.right = null;
		return true;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1080_Insufficient_Nodes_in_Root_to_Leaf_Paths solution = new LC_1080_Insufficient_Nodes_in_Root_to_Leaf_Paths();
        //System.out.println(solution.sufficientSubset(null)); //8
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
