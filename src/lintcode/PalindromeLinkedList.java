/**
 * 
 */
package lintcode;

import java.util.*;

/**
 * Implement a function to check if a linked list is a palindrome.

Have you met this question in a real interview? Yes
Example
Given 1->2->1, return true

Challenge
Could you do it in O(n) time and O(1) space?

Tags Expand 
Linked List


Related Problems Expand 
Easy Valid Palindrome 21 %
Easy Reverse Linked List 41 %

 * @author Chauncey
 *
 */
public class PalindromeLinkedList
{
    public static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
    }

    /**
     * @param head a ListNode
     * @return a boolean
     */
    public boolean isPalindrome(ListNode head) {
        if (null == head) return true;
        if (null == head.next) return true;
        ListNode slow = head;
        ListNode quick = head;
        while (quick.next != null && quick.next.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        ListNode prev = head;
        ListNode curr = head.next;
        prev.next = null;
        while (prev != slow) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        if (quick.next == null && quick != head) {
            prev = prev.next;
        }
        while (curr != null) {
            if (curr.val != prev.val)
                return false;
            prev = prev.next;
            curr = curr.next;
        }
        return true;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        PalindromeLinkedList solution = new PalindromeLinkedList();
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(2);
        l1.next.next.next = new ListNode(1);
//        l1.next.next.next.next = new ListNode(2);
//        l1.next.next.next.next.next = new ListNode(1);
        System.out.print(solution.isPalindrome(l1));
    }

}
