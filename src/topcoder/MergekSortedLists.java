package topcoder;

import java.util.*;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Hide Tags Divide and Conquer Linked List Heap
Hide Similar Problems (E) Merge Two Sorted Lists (M) Ugly Number II

Binary merge: sum( log(k)*l[i] )
Entropy coding: sum( log(1/P[i])*l[i] )

 * @author Chauncey
 *
 */
public class MergekSortedLists
{
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
		 }
	private static Comparator<ListNode[]> listComp =  new Comparator<ListNode[]>(){  
        public int compare(ListNode[] o1, ListNode[] o2) {
        	if (o1[0] == null) {
        		return -1;
        	} else if (o2[0] == null) {
        		return 1;
        	} else if(o1[0].val < o2[0].val) {
                return -1;
            } else if(o1[0].val > o2[0].val) {  
                return 1;
            } else {
                return 0;
            }
        }
    };
    public static ListNode[] mergeTwoLists(ListNode l1, ListNode l2) {
    	if (l1 == null || l2 ==null) {
    		if (l1==null)
    			return new ListNode[]{new ListNode(0), l2};
    		else
    			return new ListNode[]{new ListNode(0), l1};
    	}
    	ListNode list = null;
    	ListNode p = null;
    	int cnt = 0;
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
    		++cnt;
    	}
    	p.next = (l1 == null) ? l2 : l1;
        return new ListNode[]{new ListNode(cnt), list};
    }

    public static ListNode mergeKLists(ListNode[] lists) {
    	if (null == lists || lists.length == 0)
    		return null;
    	PriorityQueue<ListNode[]> pqueue = new PriorityQueue<ListNode[]>((lists.length>>1)+1, listComp);
	    for (int i=0; i<lists.length; i+=2) {
	    	if (i+1 == lists.length) {
	        	pqueue.add(mergeTwoLists(lists[i], null));
	        } else {
	        	pqueue.add(mergeTwoLists(lists[i], lists[i+1]));
	        }
	    }
	    while (pqueue.size() != 1) {
	    	ListNode[] l1 = pqueue.poll();
	    	ListNode[] l2 = pqueue.poll();
	    	ListNode[] list = mergeTwoLists(l1[1],l2[1]);
	    	int len = Math.max(l1[0].val, l2[0].val);
	    	if (list[0].val > len) len = list[0].val;
	    	pqueue.add(new ListNode[]{new ListNode(len), list[1]});
	    }
        return pqueue.poll()[1];
    }
    
    /*public static ListNode mergeKLists(ListNode[] lists) {
    	if (null == lists || lists.length == 0)
    		return null;
    	int last = lists.length-1;
    	while (last != 0) {
	        for (int i=0; i<=last; i+=2) {
	        	if (i == last) {
	        		lists[(i>>1)] = lists[i];
	        	} else {
	        		lists[(i>>1)] = mergeTwoLists(lists[i], lists[i+1]);
	        	}
	        }
	        last >>= 1;
    	}
        return lists[0];
    }*/
	    
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
