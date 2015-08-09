/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author Chauncey
 *
 */
public class ReverseNodesinkGroup
{
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
		 }
	
	private static ListNode canReverseSublist(ListNode head, int n)
	{
	    if (n < 2 || head == null) return null;
	    ListNode p = head;
	    for (int i=n-1; i>0; --i) {
	        p = p.next;
            if (p == null) return null;
	    }
	    return p;
	}
	
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode prehead = null;
        ListNode p = head;
        ListNode tail;
        while ((tail = canReverseSublist(p, k)) != null) {
            ListNode q = p.next;
            p.next = tail.next;
            if (prehead == null) {
                head = tail;
                prehead = p;
            } else {
                prehead.next = tail;
                prehead = p;
            }
            while (q != tail) {
                ListNode r = p;
                p = q;
                q = p.next;
                p.next = r;
            }
            tail.next = p;
            p = prehead.next;
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
		ListNode tmp = l1.next.next;
		l1.next.next.next = new ListNode(4);
		l1.next.next.next.next = new ListNode(5);
		//l2.next.next = new ListNode(6);
		ListNode l = reverseKGroup(l1,3);
		while (l != null) {
			System.out.print(l.val+"->");
			l = l.next;
		}
		System.out.println();
        while (tmp != null) {
            System.out.print(tmp.val+"->");
            tmp = tmp.next;
        }
	}

}
