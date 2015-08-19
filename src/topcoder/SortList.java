/**
 * 
 */
package topcoder;

import java.util.*;

import topcoder.MergeTwoSortedLists.ListNode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.

Hide Tags Linked List Sort
Hide Similar Problems (E) Merge Two Sorted Lists (M) Sort Colors (M) Insertion Sort List

merge sort

 * @author Chauncey
 *
 */
public class SortList
{
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
		 }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	if (l1 == null || l2 ==null)
    		return l1==null?l2:l1;
    	ListNode list = null;
    	ListNode p = null;
    	while (l1 != null && l2 != null) {
    		ListNode q;
    		if (l1.val < l2.val) {
    			q = l1;
    			l1 = l1.next;
    		} else {
    			q = l2;
    			l2 = l2.next;
    		}
    		if (p == null) {
    			p = q;
    			list = q;
    		} else {
	    		p.next = q;
	    		p = p.next;
    		}
    	}
    	p.next = (l1 == null) ? l2 : l1;
        return list;
    }
    public static ListNode sortList(ListNode head) {
        if (null == head || null == head.next) return head;
        ListNode p_slow = head, p_fast = head;
        while (p_fast.next != null && p_fast.next.next != null) {
        	p_slow = p_slow.next;
        	p_fast = p_fast.next.next;
        }
        //cut half list
        p_fast = p_slow.next;
        p_slow.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(p_fast);
        return mergeTwoLists(l1,l2);
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(3);
		l1.next.next = new ListNode(2);
		l1.next.next.next = new ListNode(-1);
		l1.next.next.next.next = new ListNode(0);
		//l2.next.next = new ListNode(6);
		ListNode l = sortList(l1);
		while (l != null) {
			System.out.println(l.val);
			l = l.next;
		}
	}

}
