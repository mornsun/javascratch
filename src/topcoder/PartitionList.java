/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.

Hide Tags Linked List Two Pointers

 * @author Chauncey
 *
 */
public class PartitionList
{
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
		 }
    public ListNode partition1(ListNode head, int x) {
        if (head == null) return null;
        ListNode cur = head;
        ListNode less = null;
        ListNode greater = null;
        while (cur != null) {
    		ListNode next = cur.next;
        	if (cur.val < x) {
        		if (less == null) {
        			less = cur;
        			less.next = head;
        			head = cur;
        		} else {
        			cur.next = less.next;
        			less.next = cur;
        			less = cur;
        		}
        	} else {
        		if (greater == null) {
        			greater = cur;
        		} else {
        			greater.next = cur;
        			greater = cur;
        		}
        	}
        	cur = next;
        }
        if (greater != null) {
        	greater.next = null;
        }
        return head;
    }
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        ListNode less = null;
        ListNode greater = null;
        ListNode lesstail = null;
        ListNode greatertail = null;
        ListNode cur = head;
        while (cur != null) {
        	if (cur.val < x) {
        		if (less == null) {
        			less = cur;
        			lesstail = cur;
        		} else {
        			lesstail.next = cur;
        			lesstail = cur;
        		}
        	} else {
        		if (greater == null) {
        			greater = cur;
        			greatertail = cur;
        		} else {
        			greatertail.next = cur;
        			greatertail = cur;
        		}
        	}
        	cur = cur.next;
        }
        if (greater != null) {
        	greatertail.next = null;
        	head = greater;
        }
        if (less != null) {
        	lesstail.next = greater;
        	head = less;
        }
        return head;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		PartitionList solution = new PartitionList();
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);
		l1.next.next.next = new ListNode(2);
		l1.next.next.next.next = new ListNode(5);
		l1.next.next.next.next.next = new ListNode(2);
		ListNode l = solution.partition(l1.next.next.next.next, 3);
		int iter = 10;
		while (l != null && iter--!=0) {
			System.out.println(l.val);
			l = l.next;
		}
	}

}
