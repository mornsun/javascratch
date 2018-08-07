package lintcode;

import java.util.*;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Have you met this question in a real interview? Yes
Example
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

Tags Expand 
Linked List


Related Problems Expand 
Naive Remove Linked List Elements 24 %

 * @author Chauncey
 *
 */
public class RemoveDuplicatesfromSortedListII
{
    /**
     * Definition for ListNode
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * @param ListNode head is the head of the linked list
     * @return: ListNode head of the linked list
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        while (curr != null) {
            if (curr.next != null && curr.val == curr.next.val) {
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                curr = curr.next;
                prev.next = curr;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        return dummy.next;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        RemoveDuplicatesfromSortedListII solution = new RemoveDuplicatesfromSortedListII();
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(3);
        head = solution.deleteDuplicates(head);
        for (ListNode curr=head; curr!=null; curr=curr.next) {
            System.out.print(curr.val+"->");
        }
        System.out.println();
        //1->2->3->3->4->4->5
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);
        head = solution.deleteDuplicates(head);
        for (ListNode curr=head; curr!=null; curr=curr.next) {
            System.out.print(curr.val+"->");
        }
        System.out.println();
    }

}
