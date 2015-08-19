/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.

Hide Tags Linked List
Hide Similar Problems (H) Reverse Nodes in k-Group

 * @author Chauncey
 *
 */
public class SwapNodesinPairs
{
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
		 }
	
    public static ListNode swapPairs(ListNode head) {
        ListNode r = null;
        ListNode p = head;
        while (p!= null && p.next != null) {
            ListNode q = p.next.next;
            p.next.next = p;
            if (r == null) {
                head = p.next;
                r = p;
            } else {
                r.next = p.next;
                r = p;
            }
            p.next = q;
            p = q;
        }
        return head;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		l1.next.next.next = new ListNode(4);
		//l1.next.next.next.next = new ListNode(5);
		//l2.next.next = new ListNode(6);
		ListNode l = swapPairs(l1);
		while (l != null) {
			System.out.println(l.val);
			l = l.next;
		}
	}

}
