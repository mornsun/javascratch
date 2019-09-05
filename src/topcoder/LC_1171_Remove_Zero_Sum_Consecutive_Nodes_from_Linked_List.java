/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 	Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.
 * After doing so, return the head of the final linked list.  You may return any such answer.
 * (Note that in the examples below, all sequences are serializations of ListNode objects.)
 *
 * Example 1:
 *
 * Input: head = [1,2,-3,3,1]
 * Output: [3,1]
 * Note: The answer [1,2,1] would also be accepted.
 * Example 2:
 *
 * Input: head = [1,2,3,-3,4]
 * Output: [1,2,4]
 * Example 3:
 *
 * Input: head = [1,2,3,-3,-2]
 * Output: [1]
 *
 * Constraints:
 *
 * The given linked list will contain between 1 and 1000 nodes.
 * Each node in the linked list has -1000 <= node.val <= 1000.
 *
 * Related Topic:
 * Linked List

 * @author Chauncey
 * Runtime: 7 ms, faster than 24.45% of Java online submissions for Remove Zero Sum Consecutive Nodes from Linked List.
 * Memory Usage: 37.8 MB, less than 100.00% of Java online submissions for Remove Zero Sum Consecutive Nodes from Linked List.
 */
public class LC_1171_Remove_Zero_Sum_Consecutive_Nodes_from_Linked_List
{
    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        if (head==null)
            return null;
        if (head.next==null) {
            if (head.val==0)
                return null;
            else
                return head;
        }

        boolean flag = true;
        while (flag) {
            ListNode curr = head;
            HashMap<Integer, ListNode> m = new HashMap<>();
            int sum = 0;
            flag = false;
            while (curr != null) {
                sum += curr.val;
                if (sum==0) {
                    head = curr.next;
                    flag = true;
                    break;
                }
                ListNode node = m.get(sum);
                if (node != null) {
                    node.next = curr.next;
                    flag = true;
                    break;
                }
                m.put(sum, curr);
                curr = curr.next;
            }
        }
        return head;
    }

    public static void print_list(ListNode head) {
        while (head!=null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println();
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1171_Remove_Zero_Sum_Consecutive_Nodes_from_Linked_List solution = new LC_1171_Remove_Zero_Sum_Consecutive_Nodes_from_Linked_List();
		//[1,2,-3,3,1]
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(-3);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(1);
        print_list(solution.removeZeroSumSublists(head)); //[3,1]
		//[1,2,3,-3,4]
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(-3);
        head.next.next.next.next = new ListNode(4);
        print_list(solution.removeZeroSumSublists(head)); //[1,2,4]
        //[1,2,3,-3,-2]
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(-3);
        head.next.next.next.next = new ListNode(-2);
        print_list(solution.removeZeroSumSublists(head)); //[1]
        head = new ListNode(1);
        print_list(solution.removeZeroSumSublists(head)); //[1]
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
