/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
Hide Tags Tree Depth-first Search
Hide Similar Problems (E) Path Sum (E) Binary Tree Paths

 * @author Chauncey
 *
 */
public class PathSumII
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
    public static void dfs(TreeNode root, int sum, List<List<Integer>> res, LinkedList<Integer> path) {
    	if (root == null) return;
    	if (root.left == null && root.right == null) { //leaf
    		if (sum-root.val == 0) {
    			path.addLast(root.val);
				LinkedList<Integer> new_path = new LinkedList<Integer>(path);
				res.add(new_path);
				path.pollLast();
    		}
    	} else {
	    	if (root.left != null) {
				path.addLast(root.val);
	        	dfs(root.left, sum-root.val, res, path);
				path.pollLast();
	    	}
	    	if (root.right != null) {
				path.addLast(root.val);
	        	dfs(root.right, sum-root.val, res, path);
				path.pollLast();
	    	}
    	}
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
    	List<List<Integer>> res = new LinkedList<List<Integer>>();
    	LinkedList<Integer> path = new LinkedList<Integer>();
    	
    	dfs(root, sum, res, path);
    	return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right = new TreeNode(8);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.left = new TreeNode(5);
		root.right.right.right = new TreeNode(1);
		System.out.println(pathSum(root, 22));
		
	}

}
