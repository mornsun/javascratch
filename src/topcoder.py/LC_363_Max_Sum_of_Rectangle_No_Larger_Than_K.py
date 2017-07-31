#!/usr/bin/env python
#coding=utf8

'''
Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?

Related Topics 
Binary Search, Dynamic Programming, Queue 


@author: Chauncey
beat %
'''

import heapq
import datetime
import time
import sys

class Solution(object):
    def maxSumSubmatrix(self, matrix, k):
        """
        :type matrix: List[List[int]]
        :type k: int
        :rtype: int
        """
        if not matrix or not matrix[0]:
          return 0

        ymax = len(matrix)
        xmax = len(matrix[0])
        memo = [[0] * (xmax+1) for _ in xrange(ymax+1)]

        for y in xrange(ymax):
          s = 0
          for x in xrange(xmax):
            s += matrix[y][x]
            memo[y+1][x+1] = memo[y][x+1] + s
        



if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.maxSumSubmatrix([
  [1,  0, 1],
  [0, -2, 3]
], 2) #2
    
    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]