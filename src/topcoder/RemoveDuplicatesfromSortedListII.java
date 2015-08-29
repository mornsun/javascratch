package topcoder;

import java.util.*;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

Hide Tags Linked List

 * @author Chauncey
 *
 */
public class RemoveDuplicatesfromSortedListII
{
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
		 }
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null) return head;
        ListNode prev=null;
        ListNode node=head;
        while (node.next != null) {
        	if (node.next.val == node.val) {
        	} else if (head == node || (prev!=null && prev.next==node)) {
        		prev = node;
        	} else {
        		if (prev == null) head = node.next;
        		else prev.next = node.next;
        	}
        	node = node.next;
        }
        if (head != node && (prev==null || prev.next!=node)) {
    		if (prev == null) head = null;
    		else prev.next = null;
        }
        return head;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		RemoveDuplicatesfromSortedListII solution = new RemoveDuplicatesfromSortedListII();
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		l1.next.next.next = new ListNode(3);
		l1.next.next.next.next = new ListNode(5);
		//l2.next.next = new ListNode(6);
		ListNode l = solution.deleteDuplicates(l1);
		while (l != null) {
			System.out.println(l.val);
			l = l.next;
		}
	}

}
