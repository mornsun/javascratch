/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author cherry
 *
 */
public class ReverseLinkedList
{
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
		 }
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        //System.out.println(head.val+":"+m+":"+n);
        if (m > 1 ) {
            head.next = reverseBetween(head.next, m-1, n-1);
            return head;
        } else if (m<n && m>=1 && n>=1){ // 1,2,3,4,5
            ListNode tail = head.next;
            ListNode next = reverseBetween(tail, 1, n-1);
            head.next = tail.next;
            tail.next = head;
            return next;
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
		ListNode l = reverseBetween(l1, 2, 4);
		while (l != null) {
			System.out.println(l.val);
			l = l.next;
		}
	}

}
