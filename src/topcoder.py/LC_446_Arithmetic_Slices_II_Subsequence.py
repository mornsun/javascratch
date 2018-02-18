#!/usr/bin/env python
#coding=utf8

'''
A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequences:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.

A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] is arithmetic. In particular, this means that k ≥ 2.

The function should return the number of arithmetic subsequence slices in the array A.

The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 ≤ N ≤ 1000. The output is guaranteed to be less than 231-1.


Example:

Input: [2, 4, 6, 8, 10]

Output: 7

Explanation:
All arithmetic subsequence slices are:
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]


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
