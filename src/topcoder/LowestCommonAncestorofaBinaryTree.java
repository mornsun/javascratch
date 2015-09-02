package topcoder;

import java.util.*;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

Hide Tags Tree
Hide Similar Problems (E) Lowest Common Ancestor of a Binary Search Tree

 * @author Chauncey
 *
 */
public class LowestCommonAncestorofaBinaryTree
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p==null || q==null) return null;
        byte[] res = new byte[1];
        return dfs_common(root, p, q, res);
    }

    private final TreeNode dfs_common(TreeNode root, TreeNode p, TreeNode q, byte[] res) {
    	byte resl = 0, resr = 0;
    	if (root.left != null) {
    		res[0] = 0;
    		TreeNode common = dfs_common(root.left, p, q, res);
    		if (common != null) return common;
    		resl = res[0];
    	}
    	if (root.right != null) {
    		res[0] = 0;
    		TreeNode common = dfs_common(root.right, p, q, res);
    		if (common != null) return common;
    		resr = res[0];
    	}
    	res[0] = (byte)(resl + resr);
    	if (root == p) res[0] += 1;
    	if (root == q) res[0] += 2;
    	//System.out.println(res[0] + resl + resr);
    	if (res[0] == 3) {
    		return root;
    	}
    	return null;
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
		TreeNode root = new TreeNode(1);
	    root.left = new TreeNode(2);
	    root.left.left = new TreeNode(4);
	    root.left.right = new TreeNode(5);
	    root.right = new TreeNode(3);
	    root.right.right = new TreeNode(6);
		print_tree(root, 0);
		LowestCommonAncestorofaBinaryTree solution = new LowestCommonAncestorofaBinaryTree();
		TreeNode common = solution.lowestCommonAncestor(root, root.left.right, root.left.right);
		System.out.println(common==null ? null : common.val);
		
	}

}
