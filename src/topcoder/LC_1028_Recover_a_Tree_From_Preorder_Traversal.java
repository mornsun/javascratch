/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 	We run a preorder depth first search on the root of a binary tree.
 *
 * At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  (If the depth of a node is D, the depth of its immediate child is D+1.  The depth of the root node is 0.)
 *
 * If a node has only one child, that child is guaranteed to be the left child.
 *
 * Given the output S of this traversal, recover the tree and return its root.
 *
 * Example 1:
 *
 * Input: "1-2--3--4-5--6--7"
 * Output: [1,2,5,3,4,6,7]
 *
 * Example 2:
 *
 * Input: "1-2--3---4-5--6---7"
 * Output: [1,2,5,3,null,6,null,4,null,7]
 *
 * Example 3:
 *
 * Input: "1-401--349---90--88"
 * Output: [1,401,null,349,88,90]
 *
 * Note:
 *
 * The number of nodes in the original tree is between 1 and 1000.
 * Each node will have a value between 1 and 10^9.
 *
 * Tree, Depth-first Search

 * @author Chauncey
 * Runtime: 4 ms, faster than 87.57%
 */
public class LC_1028_Recover_a_Tree_From_Preorder_Traversal
{
	/**
	 * Definition for a binary tree node.
	 */
	public class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}

	public TreeNode recoverFromPreorder(String S) {
		if (S==null || S.length()==0)
			return null;

		int[] meta = new int[]{0, 0};
		return this.helper(S, 0, 0, meta);
	}

	private TreeNode helper(String S, int idx, int depth, int[] meta)
	{
		if (idx == S.length())
			return null;

		int pos = idx;
		while(S.charAt(pos)=='-') pos++;
		if (pos - idx != depth)
			return null;

		idx = pos;
		pos = S.indexOf("-", idx);
		int id = 0;
		if (pos == -1) {
			pos = S.length();
		}
		id = Integer.parseInt(S.substring(idx, pos));
		TreeNode node = new TreeNode(id);
		node.left = helper(S, pos, depth+1, meta);
		if (node.left != null) {
			pos = meta[0];
			node.right = this.helper(S, pos, depth+1, meta);
			if (node.right != null)
				pos = meta[0];
		}

		meta[0] = pos;
		return node;
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

		LC_1028_Recover_a_Tree_From_Preorder_Traversal solution = new LC_1028_Recover_a_Tree_From_Preorder_Traversal();
		print_tree(solution.recoverFromPreorder("1-2--3--4-5--6--7"), 0);
		print_tree(solution.recoverFromPreorder("1-2--3---4-5--6---7"), 0);
		print_tree(solution.recoverFromPreorder("1-401--349---90--88"), 0);

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
