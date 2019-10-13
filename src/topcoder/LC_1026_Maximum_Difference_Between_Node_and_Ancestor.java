/**
 * 
 */
package topcoder;

/**
 * 	Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
 *
 * (A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)
 *
 * Example 1:
 *
 * Input: [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation:
 * We have various ancestor-node differences, some of which are given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 *
 * Note:
 *
 * The number of nodes in the tree is between 2 and 5000.
 * Each node will have value between 0 and 100000.
 *
 * Hint 1:
 * For each subtree, find the minimum value and maximum value of its descendants.
 *
 * Tree, DFS
 *
 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Difference Between Node and Ancestor.
 * Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Maximum Difference Between Node and Ancestor.
 */
public class LC_1026_Maximum_Difference_Between_Node_and_Ancestor
{
	public int maxAncestorDiff(TreeNode root) {

		if (root==null) return 0;
		return dfs(root, root.val, root.val);
	}

	private int dfs(TreeNode root, int min, int max) {

		if (root==null) return Math.abs(max-min);
		max = Math.max(max, root.val);
		min = Math.min(min, root.val);
		return Math.max(dfs(root.left, min, max), dfs(root.right, min, max));
	}

	/**
	 * Definition for a binary tree node.
	 */
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
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
		long startTime = System.currentTimeMillis();

		LC_1026_Maximum_Difference_Between_Node_and_Ancestor solution = new LC_1026_Maximum_Difference_Between_Node_and_Ancestor();
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(1);
		root.left.left = new TreeNode(0);
		root.left.right = new TreeNode(2);
		root.left.right.right = new TreeNode(3);
		root.right = new TreeNode(6);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(7);
		root.right.right.right = new TreeNode(8);
		System.out.println(solution.maxAncestorDiff(root)); //
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
