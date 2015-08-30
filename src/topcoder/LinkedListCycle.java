/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.

Hide Tags Linked List

 * @author Chauncey
 *
 */
public class LinkedListCycle
{
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
		 }
    public boolean hasCycle(ListNode head) {
    	if (head==null) return false;
    	ListNode cur = head;
    	ListNode prev = head;
    	while (cur.next != null && cur.next.next!=null) {
    		prev = prev.next;
    		cur = cur.next.next;
    		if (prev == cur) return true;
    	}
    	return false;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		LinkedListCycle solution = new LinkedListCycle();
		ListNode l = new ListNode(1);
		l.next = new ListNode(2);
		l.next.next = new ListNode(3);
		l.next.next.next = new ListNode(4);
		l.next.next.next.next = new ListNode(5);
		l.next.next.next.next.next = l.next;
		System.out.println(solution.hasCycle(l));
//		while (l != null) {
//			System.out.print(l.val+"->");
//			l = l.next;
//		}
//		System.out.println();
	}


}
