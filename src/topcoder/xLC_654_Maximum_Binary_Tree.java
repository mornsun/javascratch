/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 	Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 *
 * The root is the maximum number in the array.
 * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 * Construct the maximum tree by the given array and output the root node of this tree.
 *
 * Example 1:
 * Input: [3,2,1,6,0,5]
 * Output: return the tree root node representing the following tree:
 *
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *        \
 *         1
 * Note:
 * The size of the given array will be in the range [1,1000].
 *
 * @author Chauncey
 * Runtime: 6 ms, faster than 18.15% of Java online submissions for Maximum Binary Tree.
 * Memory Usage: 39.7 MB, less than 58.70% of Java online submissions for Maximum Binary Tree.
 *
 * Runtime: 6 ms, faster than 18.15% of Java online submissions for Maximum Binary Tree.
 * Memory Usage: 38.9 MB, less than 84.78% of Java online submissions for Maximum Binary Tree.
 */
public class xLC_654_Maximum_Binary_Tree
{
	/**
	 * Definition for a binary tree node.
	 */
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	public TreeNode constructMaximumBinaryTree(int[] nums) {

		if (nums==null || nums.length==0) return null;
		LinkedList<TreeNode> stk = new LinkedList<>();

		for (int num : nums) {
			TreeNode r = new TreeNode(num);
			while (!stk.isEmpty() && stk.peek().val<num) {
				r.left = stk.pop();
			}
			if (!stk.isEmpty()) {
				stk.peek().right = r;
			}
			stk.push(r);
		}
		return stk.peekLast();
	}

	public TreeNode constructMaximumBinaryTree1(int[] nums) {

		if (nums==null || nums.length==0) return null;
		LinkedList<TreeNode> stk = new LinkedList<>();

		for (int num : nums) {
			TreeNode r = new TreeNode(num);
			if (stk.isEmpty() || stk.peek().val>num) {
				stk.push(r);
			} else {
				TreeNode c = stk.pop();
				while (!stk.isEmpty() && stk.peek().val<num) {
					TreeNode p = stk.pop();
					p.right = c;
					c = p;
				}
				r.left = c;
				stk.push(r);
			}
		}
		TreeNode c = stk.pop();
		while (!stk.isEmpty()) {
			TreeNode p = stk.pop();
			p.right = c;
			c = p;
		}
		return c;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_654_Maximum_Binary_Tree solution = new xLC_654_Maximum_Binary_Tree();
		System.out.println(solution.constructMaximumBinaryTree(new int[]{3,2,1,6,0,5})); //
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
