/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

 Example 1:
 Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

 Output: [7,4,1]

 Explanation:
 The nodes that are a distance 2 from the target node (with value 5)
 have values 7, 4, and 1.

 Note that the inputs "root" and "target" are actually TreeNodes.
 The descriptions of the inputs above are just serializations of these objects.

 Note:
 The given tree is non-empty.
 Each node in the tree has unique values 0 <= node.val <= 500.
 The target node is a node in the tree.
 0 <= K <= 1000.

 Related Topics
 Tree, DFS, BFS

 * @author Chauncey
 * beat 100%
 */
public class LC_863_All_Nodes_Distance_K_in_Binary_Tree
{
     public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if (root == null || target == null) {
            return null;
        }
        List<Integer> ans = new ArrayList<>();

        searchDFS(ans, root, target, K);
        return ans;
    }

    private static int searchDFS(List<Integer> ans, TreeNode root, TreeNode target, int K) {
        if (root == null)
            return -2;

        if (root == target) {
            if (K==0) {
                ans.add(root.val);
            } else {
                getValsInDepth(ans, root.left, K - 1);
                getValsInDepth(ans, root.right, K - 1);
                //System.out.println(root.left.val+":"+(K-1));
                //System.out.println(root.right.val+":"+(K-1));
            }
            return K-1;
        }
        int depth = searchDFS(ans, root.left, target, K);
        if (depth == -1) {
            return -1;
        } else if (depth == 0) {
            ans.add(root.val);
            return -1;
        } else if (depth > 0) {
            getValsInDepth(ans, root.right, depth-1);
            return depth-1;
        }
        depth = searchDFS(ans, root.right, target, K);
        if (depth == -1) {
            return -1;
        } else if (depth == 0) {
            ans.add(root.val);
            return -1;
        } else if (depth > 0) {
            getValsInDepth(ans, root.left, depth-1);
            return depth-1;
        }
        return -2;
    }

    private static void getValsInDepth(List<Integer> ans, TreeNode root, int depth) {
        if (root==null || depth<0) {
            return;
        }
        if (depth==0) {
            ans.add(root.val);
            return;
        }
        ArrayList<TreeNode> curr = new ArrayList<>();
        curr.add(root);
        while(!curr.isEmpty()) {
            if (depth == 0) {
                for (TreeNode node : curr) {
                    ans.add(node.val);
                }
                return;
            }
            ArrayList<TreeNode> next = new ArrayList<>();
            for (TreeNode node : curr) {
                if (node.left != null)
                    next.add(node.left);
                if (node.right != null)
                    next.add(node.right);
            }
            curr = next;
            depth--;
        }
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_863_All_Nodes_Distance_K_in_Binary_Tree solution = new LC_863_All_Nodes_Distance_K_in_Binary_Tree();
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
		System.out.println(solution.distanceK(root,root.left,2)); //[7,4,1]
        root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);
        root.left.right.right.right = new TreeNode(4);
        System.out.println(solution.distanceK(root,root.left.right.right,0)); //[3]

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
