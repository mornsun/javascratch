#!/usr/bin/env python
#coding=utf8

'''
Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.

Note: 1 ≤ k ≤ n ≤ 109.

Example:

Input:
n: 13   k: 2

Output:
10

Explanation:
The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.

@author: Chauncey
beat 24.02%
'''

import heapq
import datetime
import time
import sys
import collections

class Solution(object):
    def findKthNumber(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: int
        """
        


if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.findKthNumber(13, 2) #10

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()