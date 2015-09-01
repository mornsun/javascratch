package topcoder;

import java.util.*;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

Hide Tags Depth-first Search Linked List
Hide Similar Problems (M) Convert Sorted Array to Binary Search Tree

 * @author Chauncey
 *
 */
public class ConvertSortedListtoBinarySearchTree
{
	/**
	 * Definition for singly-linked list.
	 */
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	/**
	 * Definition for a binary tree node.
	 */
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	private final TreeNode dfsSortedListToBST(ListNode head, ListNode tail) {
		if (head == tail) return null;
    	ListNode quick = head;
    	ListNode slow = head;
    	while (quick.next != tail && quick.next.next != tail) {
    		quick = quick.next.next;
    		slow = slow.next;
    	}
		TreeNode node = new TreeNode(slow.val);
		node.left = dfsSortedListToBST(head, slow);
		node.right = dfsSortedListToBST(slow.next, tail);
		return node;
	}

    public TreeNode sortedListToBST(ListNode head) {
    	if (head == null) return null;
    	ListNode quick = head;
    	ListNode slow = head;
    	while (quick.next != null && quick.next.next != null) {
    		quick = quick.next.next;
    		slow = slow.next;
    	}
		TreeNode node = new TreeNode(slow.val);
		node.left = dfsSortedListToBST(head, slow);
		node.right = dfsSortedListToBST(slow.next, null);
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
		ConvertSortedListtoBinarySearchTree solution = new ConvertSortedListtoBinarySearchTree();
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);
		head.next.next.next.next.next.next = new ListNode(7);
		head.next.next.next.next.next.next.next = new ListNode(8);
	    TreeNode root = solution.sortedListToBST(head);
		print_tree(root, 0);
	}

}
