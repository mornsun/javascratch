/**
 * 
 */
package topcoder;

import java.util.LinkedList;

/**
 * 	A binary tree is named Even-Odd if it meets the following conditions:
 *
 *     The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
 *     For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
 *     For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).
 *
 * Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.
 *
 * Example 1:
 *
 * Input: root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
 * Output: true
 * Explanation: The node values on each level are:
 * Level 0: [1]
 * Level 1: [10,4]
 * Level 2: [3,7,9]
 * Level 3: [12,8,6,2]
 * Since levels 0 and 2 are all odd and increasing, and levels 1 and 3 are all even and decreasing, the tree is Even-Odd.
 *
 * Example 2:
 *
 * Input: root = [5,4,2,3,3,7]
 * Output: false
 * Explanation: The node values on each level are:
 * Level 0: [5]
 * Level 1: [4,2]
 * Level 2: [3,3,7]
 * Node values in the level 2 must be in strictly increasing order, so the tree is not Even-Odd.
 *
 * Example 3:
 *
 * Input: root = [5,9,1,3,5,7]
 * Output: false
 * Explanation: Node values in the level 1 should be even integers.
 *
 * Example 4:
 *
 * Input: root = [1]
 * Output: true
 *
 * Example 5:
 *
 * Input: root = [11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17]
 * Output: true
 *
 * Constraints:
 *
 *     The number of nodes in the tree is in the range [1, 105].
 *     1 <= Node.val <= 106
 *
 * Hint 1: Use the breadth-first search to go through all nodes layer by layer.
 *
 * @author Chauncey
 * Runtime: 8 ms, faster than 97.15% of Java online submissions for Even Odd Tree.
 * Memory Usage: 55.3 MB, less than 5.10% of Java online submissions for Even Odd Tree.
 */
public class LC_1609_Even_Odd_Tree
{
	/**
	 * Definition for a binary tree node.	 */
	 public static class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode() {}
	     TreeNode(int val) { this.val = val; }
	     TreeNode(int val, TreeNode left, TreeNode right) {
	         this.val = val;
	         this.left = left;
	         this.right = right;
	     }
	 }

	public boolean isEvenOddTree(TreeNode root) {
		if (root == null)
			return true;

		LinkedList<TreeNode> q = new LinkedList<>();
		q.offer(root);
		int idx = 0;
		while (!q.isEmpty()) {
			LinkedList<TreeNode> pq = q;
			q = new LinkedList<>();
			TreeNode pnode = null;
			while (!pq.isEmpty()) {
				TreeNode node = pq.poll();
				if (idx%2==0) {
					if (node.val%2==0)
						return false;
					if (pnode!=null && node.val<=pnode.val)
						return false;
				} else {
					if (node.val%2!=0)
						return false;
					if (pnode!=null && node.val>=pnode.val)
						return false;
				}
				pnode = node;
				if (node.left != null)
					q.offer(node.left);
				if (node.right != null)
					q.offer(node.right);
			}
			idx++;
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1609_Even_Odd_Tree solution = new LC_1609_Even_Odd_Tree();
		TreeNode root = new TreeNode(5);
        System.out.println(solution.isEvenOddTree(root)); //0
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
