/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?

Hide Tags Linked List Two Pointers
Hide Similar Problems (M) Linked List Cycle II

 * @author Chauncey
 *
 */
public class ReorderList
{
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
		 }
    public void reorderList(ListNode head) {
    	if (head==null || head.next==null) return;
    	ListNode cur = head;
    	ListNode prev = head;
    	while (cur.next != null && cur.next.next!=null) {
    		prev = prev.next;
    		cur = cur.next.next;
    	}
    	ListNode midnode = prev;
    	prev = midnode;
    	cur = midnode.next;
    	midnode.next = null;
    	while (cur != null) {
    		ListNode next = cur.next;
    		cur.next = prev;
    		prev = cur;
    		cur = next;
    	}
    	midnode = prev;
    	prev = head;
    	cur = midnode;
    	while (prev != null && cur != null) {
    		ListNode pnext = prev.next;
    		ListNode cnext = cur.next;
    		prev.next = cur;
    		cur.next = pnext;
    		prev = pnext;
    		cur = cnext;
    	}
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ReorderList solution = new ReorderList();
		ListNode l = new ListNode(1);
		l.next = new ListNode(2);
		l.next.next = new ListNode(3);
		l.next.next.next = new ListNode(4);
		l.next.next.next.next = new ListNode(5);
		l.next.next.next.next.next = new ListNode(6);
		solution.reorderList(l);
		for (ListNode node = l; node != null; node = node.next) {
			System.out.print(node.val+"->");
		}
		System.out.println();
	}
}
