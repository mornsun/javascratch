#!/usr/bin/env python
#coding=utf8

'''
Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:
Given nums = [-2, 5, -1], lower = -2, upper = 2,
Return 3.
The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.

 Divide and Conquer Binary Search Tree
Hide Similar Problems (H) Count of Smaller Numbers After Self (H) Reverse Pairs

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
    def countRangeSum1(self, nums, lower, upper):
        """
        :type nums: List[int]
        :type lower: int
        :type upper: int
        :rtype: int
        """
        if nums == None or len(nums) == 0 or lower > upper:
            return 0

        n = len(nums)
        sums = [0]
        for i in xrange(n):
            sums.append(sums[-1]+nums[i])
        cnt = 0
        for i in xrange(n):
            for j in xrange(i+1, n+1):
                if sums[j]-sums[i]>=lower and sums[j]-sums[i]<=upper:
                    cnt += 1
        return cnt
        
    def countRangeSum(self, nums, lower, upper):
        """
        :type nums: List[int]
        :type lower: int
        :type upper: int
        :rtype: int
        """
        if nums == None or len(nums) == 0 or lower > upper:
            return 0

        n = len(nums)
        sums = [0]
        for i in xrange(n):
            sums.append(sums[-1]+nums[i])
        #print sums

        root_smaller_than_lower = TreeNode(sums[0])
        root_smaller_than_upper = TreeNode(sums[0])
        smaller_than_lower = 0
        smaller_than_upper = 0
        for i in xrange(1, n+1): #xrange(n-1, -1, -1):
            smaller_than_lower += self.addNode(root_smaller_than_lower, lower, sums[i], 0)
            smaller_than_upper += self.addNode(root_smaller_than_upper, upper+1, sums[i], 0)
            #self.print_tree(root, 0)
        #print upper, lower
        print smaller_than_upper, smaller_than_lower
        return smaller_than_upper - smaller_than_lower

    def addNode(self, root, diff, num, prevcnt):

        if num - root.val < diff:
            if root.left is None:
                root.left = TreeNode(num)
                ret = prevcnt
            else:
                ret = self.addNode(root.left, diff, num, prevcnt)
            root.lcnt += 1
        else:
            if root.right is None:
                root.right = TreeNode(num)
                ret = prevcnt + root.lcnt + (0 if num-root.val == diff else 1)
            else:
                ret = self.addNode(root.right, diff, num, prevcnt + root.lcnt + (0 if num-root.val == diff else 1))
            root.rcnt += 1

        return ret
    
    def print_tree(self, node, deep):
        indent = ''
        for i in xrange(deep):
            indent += ' '

        if node is None:
            print "%s|-" % (indent)
        else:
            print "%s|-(%d,%d,%d)" % (indent, node.val, node.lcnt, node.rcnt)
            self.print_tree(node.left, deep+1)
            self.print_tree(node.right, deep+1)


if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.countRangeSum([-2, 5, -1], -2, 2) #3 [0, -2, 3, 2]
    print solution.countRangeSum([2147483647,-2147483648,-1,0], -1, 0) #4 [0, 2147483647, -1, -2, -2]
    print solution.countRangeSum([0,-3,-3,1,1,2], 3, 5) #2 [0, 0, -3, -6, -5, -4, -2]


    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]