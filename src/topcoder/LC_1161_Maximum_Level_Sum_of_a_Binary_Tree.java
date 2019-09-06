/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 	Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
 *
 * Example 1:
 *
 * Input: [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 *
 * Note:
 *
 * The number of nodes in the given tree is between 1 and 10^4.
 * -10^5 <= node.val <= 10^5
 *
 * @author Chauncey
 * Runtime: 9 ms, faster than 83.85% of Java online submissions for Maximum Level Sum of a Binary Tree.
 * Memory Usage: 40.5 MB, less than 100.00% of Java online submissions for Maximum Level Sum of a Binary Tree.
 */
public class LC_1161_Maximum_Level_Sum_of_a_Binary_Tree
{
  public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public int maxLevelSum(TreeNode root) {

        if (root==null)
            return 0;

        LinkedList<TreeNode> curr = new LinkedList<>();
        curr.offer(root);
        int max = root.val;
        int ml = 1;
        int l = 0;
        while (!curr.isEmpty()) {
            LinkedList<TreeNode> prev = curr;
            curr = new LinkedList<>();
            int sum = 0;
            while (!prev.isEmpty()) {
                TreeNode node = prev.poll();
                sum += node.val;
                if (node.left!=null)
                    curr.offer(node.left);
                if (node.right!=null)
                    curr.offer(node.right);
            }
            l++;
            if (sum > max) {
                max = sum;
                ml = l;
            }
        }
        return ml;
    }

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1161_Maximum_Level_Sum_of_a_Binary_Tree solution = new LC_1161_Maximum_Level_Sum_of_a_Binary_Tree();
		//[1,2,-3,3,1]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(7);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(-8);
        System.out.println(solution.maxLevelSum(root)); //[true,false,false,true,true]
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
