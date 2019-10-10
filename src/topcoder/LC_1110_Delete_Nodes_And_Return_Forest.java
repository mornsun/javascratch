/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 	Given the root of a binary tree, each node in the tree has a distinct value.
 *
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 *
 * Return the roots of the trees in the remaining forest.  You may return the result in any order.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 *
 * Constraints:
 *
 * The number of nodes in the given tree is at most 1000.
 * Each node has a distinct value between 1 and 1000.
 * to_delete.length <= 1000
 * to_delete contains distinct values between 1 and 1000.
 *
 * @author Chauncey
 * Runtime: 17 ms, faster than 57.22% of Java online submissions for Path with Maximum Gold.
 * Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Path with Maximum Gold.
 */
public class LC_1110_Delete_Nodes_And_Return_Forest
{
	/**
	 * Definition for a binary tree node.
	 * */
	 public class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
	 }

	public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {

		List<TreeNode> res = new ArrayList<TreeNode>();
		if (root==null) return res;
		if (to_delete==null || to_delete.length==0) {
			res.add(root);
			return res;
		}

		HashSet<Integer> set = new HashSet<>();
		for (int n : to_delete)
			set.add(n);

		if (!DfsHelper(res, root, set)) res.add(root);
		return res;

	}

	private boolean DfsHelper(List<TreeNode> res, TreeNode root, HashSet<Integer> set)
	{
		if (root==null) return false;
		if (DfsHelper(res, root.left, set)) root.left = null;
		if (DfsHelper(res, root.right, set)) root.right = null;
		if (set.contains(root.val)) {
			if (root.left!=null) {
				res.add(root.left);
				root.left = null;
			}
			if (root.right!=null) {
				res.add(root.right);
				root.right = null;
			}
			return true;
		}
		return false;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1110_Delete_Nodes_And_Return_Forest solution = new LC_1110_Delete_Nodes_And_Return_Forest();
        System.out.println(); //
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
