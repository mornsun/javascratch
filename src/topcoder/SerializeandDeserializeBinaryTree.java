package topcoder;

import java.util.*;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

Credits:
Special thanks to @Louis1992 for adding this problem and creating all test cases.

Hide Tags Tree Design
Hide Similar Problems (M) Encode and Decode Strings

 * @author Chauncey
 *
 */
public class SerializeandDeserializeBinaryTree
{
	public static class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
    	StringBuilder sb = new StringBuilder();
        if (null == root) return sb.toString();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        sb.append(root.val).append(',');
        while (!queue.isEmpty()) {
        	TreeNode curr = queue.poll();
        	if (curr.left != null) {
        		queue.add(curr.left);
        		sb.append(curr.left.val).append(',');
        	} else {
        		sb.append("null").append(',');
        	}
        	if (curr.right != null) {
        		queue.add(curr.right);
        		sb.append(curr.right.val).append(',');
        	} else {
        		sb.append("null").append(',');
        	}
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize1(String data) {
        if (null == data || data.length() == 0) return null;
        String[] vals = data.split(",");
        if (vals.length <= 1) return null;
        TreeNode root = new TreeNode(Integer.valueOf(vals[0]));
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        for (int i=1; i<vals.length-2; i+=2) {
        	TreeNode curr = queue.poll();
        	TreeNode left = "null".equals(vals[i]) ? null : new TreeNode(Integer.valueOf(vals[i]));
        	curr.left = left;
        	if (left != null) {
        		queue.offer(left);
        	}
        	TreeNode right = "null".equals(vals[i+1]) ? null : new TreeNode(Integer.valueOf(vals[i+1]));
        	curr.right = right;
        	if (right != null) {
        		queue.offer(right);
        	}
        }
        return root;
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (null == data || data.length() == 0) return null;
        StringTokenizer st = new StringTokenizer(data.substring(0, data.length()-1), ",");
        if (st.countTokens() == 0) return null;
        TreeNode root = new TreeNode(Integer.valueOf(st.nextToken()));
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while ( st.hasMoreElements() ){
        	TreeNode curr = queue.poll();
        	String lstr = st.nextToken();
        	String rstr = st.nextToken();
        	TreeNode left = "null".equals(lstr) ? null : new TreeNode(Integer.valueOf(lstr));
        	curr.left = left;
        	if (left != null) {
        		queue.offer(left);
        	}
        	TreeNode right = "null".equals(rstr) ? null : new TreeNode(Integer.valueOf(rstr));
        	curr.right = right;
        	if (right != null) {
        		queue.offer(right);
        	}
        }
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
		SerializeandDeserializeBinaryTree solution = new SerializeandDeserializeBinaryTree();

		TreeNode root = new TreeNode(5);
	    root.left = new TreeNode(3);
	    root.left.left = new TreeNode(1);
	    root.left.left.left = new TreeNode(0);
	    root.left.left.right = new TreeNode(2);
	    root.left.right = new TreeNode(4);
	    root.right = new TreeNode(7);
		print_tree(root, 0);
		root = solution.deserialize(solution.serialize(root));
		print_tree(root, 0);
	}

}
