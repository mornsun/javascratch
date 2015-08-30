/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?

Hide Tags Linked List Two Pointers
Hide Similar Problems (M) Linked List Cycle

 * @author Chauncey
 *
 */
public class LinkedListCycleII
{
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
		 }
    public ListNode detectCycle(ListNode head) {
    	if (head==null) return null;
    	ListNode cur = head;
    	ListNode prev = head;
    	while (cur.next != null && cur.next.next!=null) {
    		prev = prev.next;
    		cur = cur.next.next;
    		if (prev == cur) {
    			ListNode slow = head;
    			while (slow != cur) {
    				slow = slow.next;
    				cur = cur.next;
    			}
    			return cur;
    		}
    	}
    	return null;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		LinkedListCycleII solution = new LinkedListCycleII();
		ListNode l = new ListNode(1);
		l.next = new ListNode(2);
		l.next.next = new ListNode(3);
		l.next.next.next = new ListNode(4);
		l.next.next.next.next = new ListNode(5);
		l.next.next.next.next.next = l.next.next;
		ListNode node = solution.detectCycle(l);
		System.out.println(node==null?"null":node.val);
//		while (l != null) {
//			System.out.print(l.val+"->");
//			l = l.next;
//		}
//		System.out.println();
	}


}
