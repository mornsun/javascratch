#!/usr/bin/env python
#coding=utf8

'''
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7

Related Topics 
Linked List
Similar Questions 
Add Two Numbers


@author: Chauncey
beat 69.66%
'''

import heapq
import datetime
import time
import sys
import collections

# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        len1, len2 = 0, 0

        p = l1
        while p:
            p = p.next
            len1 += 1

        p = l2
        while p:
            p = p.next
            len2 += 1

        if len1 < len2:
            l1, l2 = l2, l1
            len1, len2 = len2, len1

        head = ListNode(0)
        curr = head
        p1 = l1
        p2 = l2

        for i in xrange(len1-len2):
            curr.next = ListNode(p1.val)
            p1 = p1.next
            curr = curr.next

        for i in xrange(len2):
            add = p1.val+p2.val
            if add < 10:
                curr.next = ListNode(add)
            else:
                curr.next = ListNode(add-10)
                curr.val += 1
            p1 = p1.next
            p2 = p2.next
            curr = curr.next

        flag = True
        while flag:
            flag = False
            p = head
            while p.next:
                if p.next.val >= 10:
                    p.next.val -= 10
                    p.val += 1
                    flag = True
                p = p.next
        return head if head.val else head.next

def print_list(head):
    while head:
        print '%d->' % head.val,
        head = head.next
    print

if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    l1 = ListNode(7)
    l1.next = ListNode(2)
    l1.next.next = ListNode(4)
    l1.next.next.next = ListNode(3)
    l2 = ListNode(5)
    l2.next = ListNode(6)
    l2.next.next = ListNode(4)
    print_list(solution.addTwoNumbers(l1, l2))
    #7807

    l1 = ListNode(9)
    l1.next = ListNode(9)
    l1.next.next = ListNode(9)
    l1.next.next.next = ListNode(9)
    l2 = ListNode(1)
    print_list(solution.addTwoNumbers(l2, l1))

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()