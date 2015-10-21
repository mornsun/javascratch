/**
 * 
 */
package lintcode;

import java.util.*;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed. Only constant memory is allowed.

Have you met this question in a real interview? Yes
Example
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Tags Expand 
Linked List


Related Problems Expand 
Easy Swap Nodes in Pairs 32 %
Medium Rotate List 26 %
Medium Reverse Linked List II 29 %
Easy Reverse Linked List 41 %

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
	
    /**
     * @param head a ListNode
     * @param k an integer
     * @return a ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // Write your code here
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
	
	private final ListNode canReverseSublist(ListNode head, int n)
	{
	    if (n < 2 || head == null) return null;
	    ListNode p = head;
	    for (int i=n-1; i>0; --i) {
	        p = p.next;
            if (p == null) return null;
	    }
	    return p;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ReverseNodesinkGroup solution = new ReverseNodesinkGroup();
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		ListNode tmp = l1.next.next;
		l1.next.next.next = new ListNode(4);
		l1.next.next.next.next = new ListNode(5);
		//l2.next.next = new ListNode(6);
		ListNode l = solution.reverseKGroup(l1,3);
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
