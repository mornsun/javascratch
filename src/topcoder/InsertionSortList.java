/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Sort a linked list using insertion sort.

Hide Tags Linked List Sort
Hide Similar Problems (M) Sort List

 * @author Chauncey
 *
 */
public class InsertionSortList
{
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
		 }
    public ListNode insertionSortList(ListNode head) {
    	if (head==null) return head;
    	ListNode cur = head.next;
    	head.next = null;
    	while (cur != null) {
    		ListNode cnext = cur.next;
    		if (cur.val <= head.val) {
    			cur.next = head;
    			head = cur;
    		} else {
        		ListNode node = head;
	    		while (node.next != null) {
	    			if (cur.val <= node.next.val) {
		    			cur.next = node.next;
		    			node.next = cur;
	    				break;
	    			}
	    			node = node.next;
	    		}
	    		if (node.next == null) {
	    			node.next = cur;
	    			cur.next = null;
	    		}
    		}
    		cur = cnext;
    	}
    	return head;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		InsertionSortList solution = new InsertionSortList();
		ListNode l = new ListNode(2);
		l.next = new ListNode(5);
		l.next.next = new ListNode(4);
		l.next.next.next = new ListNode(1);
		l.next.next.next.next = new ListNode(3);
		//l.next.next.next.next.next = l.next;
		l = solution.insertionSortList(l);
		while (l != null) {
			System.out.print(l.val+"->");
			l = l.next;
		}
		System.out.println();
	}


}
