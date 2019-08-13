package topcoder;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 *
 * Runtime: 1 ms, faster than 24.75% of Java online submissions for Binary Tree Coloring Game.
 * Memory Usage: 34.8 MB, less than 100.00% of Java online submissions for Binary Tree Coloring Game.
 */
class LC_1145_Binary_Tree_Coloring_Game {
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {

        if (root == null || n<=2 || x>n || x<1)
          return false;

 int[] cnts = new int[]{0,0,0};
        dfsearch(root, x, cnts);

        if (n-cnts[1]-cnts[2]-1>n/2 || cnts[1]>n/2 || cnts[2]>n/2) return true;
        else return false;
    }

private int dfsearch(TreeNode root, int x, int[] cnts) {

if (root == null)
  return 0;

int l = dfsearch(root.left, x, cnts);
int r = 0;
if (cnts[0] != 1)
  r = dfsearch(root.right, x, cnts);

if (root.val == x)
{
  cnts[0] = 1;
  cnts[1] = l;
  cnts[2] = r;
}

return l + r + 1;
}
    
    
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

        LC_1145_Binary_Tree_Coloring_Game solution = new LC_1145_Binary_Tree_Coloring_Game();
        TreeNode root = new TreeNode(1);
        root.left= new TreeNode(2);
        root.right= new TreeNode(3);
        System.out.println(solution.btreeGameWinningMove(root, 3, 2));
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}
}