#!/usr/bin/env python
#coding=utf8

'''
Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.

Example 1:
nums = [1, 3], n = 6
Return 1.

Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
So we only need 1 patch.

Example 2:
nums = [1, 5, 10], n = 20
Return 2.
The two patches can be [2, 4].

Example 3:
nums = [1, 2, 2], n = 5
Return 0.

@author: Chauncey
beat 92.56%
'''

import heapq
import datetime
import time
import sys

class Solution(object):
    def minPatches(self, nums, n):
        """
        :type nums: List[int]
        :type n: int
        :rtype: int
        """
        if n<=0:
          return 0
        if nums is None:
          nums = []

        miss = 1
        index = 0
        patch = 0
        while miss<=n:
          if index>=len(nums) or miss<nums[index]:
            miss <<= 1
            patch += 1
            continue
          if miss>=nums[index]:
            miss += nums[index]
            index += 1
            continue
        return patch
        

if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.minPatches([1, 3], 6) #1
    print solution.minPatches([1, 5, 10], 20) #2
    print solution.minPatches([1, 2, 2], 5) #0
    print solution.minPatches([], 7) #3
    
    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]