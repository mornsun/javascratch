/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.

 Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.

 Each node of each tree in the answer must have node.val = 0.

 You may return the final list of trees in any order.

 Example 1:

 Input: 7
 Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
 Explanation:

 Note:

 1 <= N <= 20

 Related Topic
 Tree, Recursion

 * @author Chauncey
 * beat 63.24%
 */
public class LC_894_All_Possible_Full_Binary_Trees
{
    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int x){val = x;}
    }

    Map<Integer, List<TreeNode>> memo = new HashMap();

    public List<TreeNode> allPossibleFBT(int N) {
        if (N<0 || (N&1) == 0) {
            return new ArrayList<>();
        }
        List<TreeNode> ans = memo.get(N);
        if (ans != null) {
            return ans;
        }
        ans = new ArrayList<TreeNode>();
        if (N == 1) {
            ans.add(new TreeNode(0));
            memo.put(1, ans);
            return ans;
        }
        for (int i=1; i<N-1; i+=2) {
            for (TreeNode left : allPossibleFBT(i)) {
                for (TreeNode right : allPossibleFBT(N-1-i)) {
                    TreeNode node = new TreeNode(0);
                    node.left = left;
                    node.right = right;
                    ans.add(node);
                }
            }
        }
        memo.put(N, ans);
        return ans;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_894_All_Possible_Full_Binary_Trees solution = new LC_894_All_Possible_Full_Binary_Trees();
		System.out.println(solution.allPossibleFBT(7)); //
		
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
