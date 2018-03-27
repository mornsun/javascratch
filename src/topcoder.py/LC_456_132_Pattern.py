#!/usr/bin/env python
#coding=utf8

'''
Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].

Related Topics 
Stack

@author: Chauncey
beat 50。45%
'''

import heapq
import datetime
import time
import sys
import collections
import operator
import bisect

class Solution(object):
    def find132pattern(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        if not nums or len(nums)<3:
            return False

        minis = [nums[0]]
        for i in xrange(1, len(nums)):
            minis.append(min(nums[i], minis[-1]))

        stack = [nums[-1]]
        for i in xrange(len(nums)-2, -1, -1):
            if nums[i] > minis[i]:
                while stack and stack[-1] <= minis[i]:
                    stack.pop()
                if stack and stack[-1] < nums[i]:
                    return True
                stack.append(nums[i])

        return False

        


if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.find132pattern([1, 2, 3, 4]) #False
    print solution.find132pattern([3, 1, 4, 2]) #True
    print solution.find132pattern([-1, 3, 2, 0]) #True
    print solution.find132pattern([3, 5, 0, 3, 4]) #True
    print solution.find132pattern([1,0,1,-4,-3]) #False
    print solution.find132pattern([2,4,3,1]) #True

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()