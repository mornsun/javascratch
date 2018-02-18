#!/usr/bin/env python
#coding=utf8

'''
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]

@author: Chauncey
beat 15.19%
'''

import heapq
import datetime
import time
import sys
import collections

class Solution(object):
    def findDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        res = []
        if not nums:
            return res

        for i in xrange(len(nums)):
            while nums[i] != i + 1 and nums[i] != 0:
                idx = nums[i] - 1
                if nums[idx] == nums[i]:
                    nums[i] = 0
                    res.append(nums[idx])
                else:
                    nums[idx], nums[i] = nums[i], nums[idx]
                #print nums
        return res


if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.findDuplicates([4,3,2,7,8,2,3,1])
    #[2,3]

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()