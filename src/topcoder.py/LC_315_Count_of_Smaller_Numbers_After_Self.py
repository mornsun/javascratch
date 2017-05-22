#!/usr/bin/env python
#coding=utf8

'''
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].

Subscribe to see which companies asked this question.

Hide Tags Divide and Conquer Binary Indexed Tree Segment Tree Binary Search Tree
Hide Similar Problems (H) Count of Range Sum (M) Queue Reconstruction by Height (H) Reverse Pairs

@author: Chauncey
'''

import heapq
import datetime
import time

# Definition for a binary tree node\
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
        self.lcnt = 0
        self.rcnt = 0

class Solution(object):
    def countSmaller(self, nums):
        def sort(enum):
            half = len(enum) / 2
            if half:
                left, right = sort(enum[:half]), sort(enum[half:])
                for i in range(len(enum))[::-1]:
                    if not right or left and left[-1][1] > right[-1][1]:
                        smaller[left[-1][0]] += len(right)
                        enum[i] = left.pop()
                    else:
                        enum[i] = right.pop()
            return enum
        smaller = [0] * len(nums)
        sort(list(enumerate(nums)))
        return smaller

    def countSmaller1(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        if nums is None or len(nums) == 0: return []

        n = len(nums)
        root = TreeNode(nums[n-1])
        res = [0]
        for i in xrange(n-2, -1, -1):
            res.append(self.addNode(root, nums[i], 0))
        return res[::-1]


    def addNode(self, root, num, prevcnt):

        if num < root.val:
            if root.left is None:
                root.left = TreeNode(num)
                ret = prevcnt
            else:
                ret = self.addNode(root.left, num, prevcnt)
            root.lcnt += 1
        else:
            if root.right is None:
                root.right = TreeNode(num)
                ret = prevcnt + root.lcnt
            else:
                ret = self.addNode(root.right, num, prevcnt + root.lcnt)
            root.rcnt += 1

        return ret
        

if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.countSmaller1([5, 2, 6, 1]) #[2, 1, 1, 0]

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]