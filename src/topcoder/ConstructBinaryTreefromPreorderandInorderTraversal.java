/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

Hide Tags Tree Array Depth-first Search
Hide Similar Problems (M) Construct Binary Tree from Inorder and Postorder Traversal

 * @author Chauncey
 *
 */
public class ConstructBinaryTreefromPreorderandInorderTraversal
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (null == preorder || null == inorder || preorder.length==0 || preorder.length != inorder.length)
        	return null;
        return buildTree(preorder, 0, inorder, 0, inorder.length-1);
    }
    private final TreeNode buildTree(int[] preorder, int ps, int[] inorder, int is, int ie) {
    	int val = preorder[ps];
    	TreeNode root = new TreeNode(val);
    	//System.out.println(ps+":"+val+":"+is+":"+ie);
    	if (is == ie) return root;
    	int ri = is;
    	for (; ri<=ie; ++ri) {
    		if (inorder[ri] == val) break;
    	}
    	if (is < ri)
    		root.left = buildTree(preorder, ps+1, inorder, is, ri-1);
    	if (ri < ie)
    		root.right = buildTree(preorder, ps+ri-is+1, inorder, ri+1, ie);
    	return root;
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
		ConstructBinaryTreefromPreorderandInorderTraversal solution = new ConstructBinaryTreefromPreorderandInorderTraversal();
		
		print_tree(solution.buildTree(new int[]{1,2,4,5,3}, new int[]{4,2,5,1,3}), 0);
		
	}

}
