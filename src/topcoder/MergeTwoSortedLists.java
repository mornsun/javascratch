/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author cherry
 *
 */
public class MergeTwoSortedLists
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

    public static ListNode mergeKLists(ListNode[] lists) {
    	if (null == lists || lists.length == 0)
    		return null;
    	ListNode list = lists[0];
        for (int i=1; i<lists.length; ++i) {
        	list = mergeTwoLists(list, lists[i]);
        }
        return list;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(3);
		l1.next.next = new ListNode(5);
		ListNode l2 = new ListNode(-1);
		l2.next = new ListNode(4);
		l2.next.next = new ListNode(6);
		ListNode[] lists = new ListNode[2];
		lists[0] = l1;
		lists[1] = l2;
		ListNode l = mergeKLists(lists);
		while (l != null) {
			System.out.println(l.val);
			l = l.next;
		}
	}

}
