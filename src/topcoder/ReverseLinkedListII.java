/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.

Hide Tags Linked List
Hide Similar Problems (E) Reverse Linked List
 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List II.
 * Memory Usage: 34.5 MB, less than 100.00% of Java online submissions for Reverse Linked List II.
 *
 */
public class ReverseLinkedListII
{
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}

	public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode curr = head;
		head = dummy;
		while (m>1 && curr!=null) {
			head = curr;
			curr = curr.next;
			--m; --n;
		}
		if (curr.next==null) return dummy.next;
		ListNode to1 = curr.next;
		ListNode to2 = curr.next.next;
		while (n>1 && to1!=null) {
			to1.next = curr;
			curr = to1;
			to1 = to2;
			if (to2!=null) to2 = to2.next;
			--n;
		}
		head.next.next = to1;
		head.next = curr;
		return dummy.next;
	}

    public ListNode reverseBetween1(ListNode head, int m, int n) {
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

	public static void print_list(ListNode head) {
		while (head!=null) {
			System.out.print(head.val + "->");
			head = head.next;
		}
		System.out.println("NULL");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		ReverseLinkedListII solution = new ReverseLinkedListII();

		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		l1.next.next.next = new ListNode(4);
		l1.next.next.next.next = new ListNode(5);
		print_list(solution.reverseBetween(l1, 2, 4));
		l1 = new ListNode(3);
		l1.next = new ListNode(5);
		print_list(solution.reverseBetween(l1, 1, 1));

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
