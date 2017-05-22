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

class Solution(object):
    def countRangeSum(self, nums, lower, upper):
        """
        :type nums: List[int]
        :type lower: int
        :type upper: int
        :rtype: int
        """
        n = len(nums)
        if nums == None or n == 0 or lower > upper:
            return 0

        sums = [0]
        for i in xrange(n):
            sums.append(sums[-1]+nums[i])
        cnt = 0
        for i in xrange(n):
            for j in xrange(i+1, n+1):
                if sums[j]-sums[i]>=lower and sums[j]-sums[i]<=upper:
                    cnt += 1
        return cnt
        

if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.countRangeSum([-2, 5, -1], -2, 2) #3

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]