/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.

Hide Tags Linked List Two Pointers
Hide Similar Problems (E) Rotate Array

 * @author Chauncey
 *
 */
public class RotateList
{
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
		 }
    public static ListNode rotateRight(ListNode head, int k) {
    	if (head == null || k==0) return head;
    	ListNode p = head;
    	ListNode r = null;
    	int cnt = k-1;
    	while (p.next != null) {
    		if (cnt != 0) {
    			--cnt;
    		} else {
    			if (r == null) {
    				r = head;
    			} else {
    				r = r.next;
    			}
    		}
    		p = p.next;
    		if (p.next == null && cnt != 0) { //k exceeds
    			p = head;
    			cnt = cnt % (k-cnt) - 1;
    			if (cnt < 0) return head;
    		}
    	}
    	if (r != null) {
    		p.next = head;
    		head = r.next;
    		r.next = null;
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
		l1.next.next.next.next = new ListNode(5);
		//l2.next.next = new ListNode(6);
		ListNode l = rotateRight(l1, 7);
		while (l != null) {
			System.out.println(l.val);
			l = l.next;
		}
	}

}
