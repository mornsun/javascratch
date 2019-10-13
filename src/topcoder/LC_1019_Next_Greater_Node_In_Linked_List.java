/**
 * 
 */
package topcoder;

import java.util.LinkedList;

/**
 * 	We are given a linked list with head as the first node.  Let's number the nodes in the list: node_1, node_2, node_3, ... etc.
 *
 * Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i, node_j.val > node_i.val, and j is the smallest possible choice.  If such a j does not exist, the next larger value is 0.
 *
 * Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).
 *
 * Note that in the example inputs (not outputs) below, arrays such as [2,1,5] represent the serialization of a linked list with a head node value of 2, second node value of 1, and third node value of 5.
 *
 * Example 1:
 *
 * Input: [2,1,5]
 * Output: [5,5,0]
 * Example 2:
 *
 * Input: [2,7,4,3,5]
 * Output: [7,0,5,5,0]
 * Example 3:
 *
 * Input: [1,7,5,1,9,2,5,1]
 * Output: [7,9,9,9,0,5,0,0]
 *
 * Note:
 *
 * 1 <= node.val <= 10^9 for each node in the linked list.
 * The given list has length in the range [0, 10000].
 *
 * Hint 1:
 * We can use a stack that stores nodes in monotone decreasing order of value. When we see a node_j with a larger value, every node_i in the stack has next_larger(node_i) = node_j .
 *
 * LinkedList, Stack
 *
 * @author Chauncey
 * Runtime: 11 ms, faster than 96.64% of Java online submissions for Next Greater Node In Linked List.
 * Memory Usage: 41.2 MB, less than 97.30% of Java online submissions for Next Greater Node In Linked List.
 */
public class LC_1019_Next_Greater_Node_In_Linked_List
{
	public int[] nextLargerNodes(ListNode head) {

		if (head==null) return new int[0];
		int[] res = new int[10000];
		LinkedList<int[]> stk = new LinkedList<>();
		ListNode curr = head;
		int n=0;
		while (curr != null) {
			while (!stk.isEmpty()) {
				if (curr.val <= stk.peek()[0]) {
					stk.push(new int[]{curr.val, n});
					break;
				}
				int[] top = stk.pop();
				res[top[1]] = curr.val;
			}
			if (stk.isEmpty())
				stk.push(new int[]{curr.val, n});
			curr = curr.next;
			n++;
		}

		int[] r = new int[n];
		for(int i=0; i<n; ++i) r[i] = res[i];
		return r;

	}

	/**
	 * Definition for a binary tree node.
	 */
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1019_Next_Greater_Node_In_Linked_List solution = new LC_1019_Next_Greater_Node_In_Linked_List();
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
