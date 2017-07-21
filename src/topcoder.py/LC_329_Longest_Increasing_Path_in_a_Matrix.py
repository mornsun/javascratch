#!/usr/bin/env python
#coding=utf8

'''
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

Depth-first Search, Topological Sort, Memoization

@author: Chauncey
'''

import heapq
import datetime
import time

class Solution(object):
    def longestIncreasingPath(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: int
        """
        if matrix is None or len(matrix)==0 or len(matrix[0])==0:
            return 0

        m = len(matrix)
        n = len(matrix[0])

        memo = [[-1 for __ in xrange(n)] for _ in xrange(m)]

        max_len = 0
        for x in xrange(m):
            for y in xrange(n):
                max_len = max(max_len, memo[x][y] if memo[x][y]>=0 else self.findLongestPath(matrix, memo, x, y))

        return max_len

    def findLongestPath(self, matrix, memo, x, y):
        m = len(matrix)
        n = len(matrix[0])
        max_len = 0

        if y-1 >= 0 and matrix[x][y-1] > matrix[x][y]:
            max_len = max(max_len, memo[x][y-1] if memo[x][y-1]>=0 else self.findLongestPath(matrix, memo, x, y-1))
        if y+1 < n and matrix[x][y+1] > matrix[x][y]:
            max_len = max(max_len, memo[x][y+1] if memo[x][y+1]>=0 else self.findLongestPath(matrix, memo, x, y+1))
        if x-1 >= 0 and matrix[x-1][y] > matrix[x][y]:
            max_len = max(max_len, memo[x-1][y] if memo[x-1][y]>=0 else self.findLongestPath(matrix, memo, x-1, y))
        if x+1 < m and matrix[x+1][y] > matrix[x][y]:
            max_len = max(max_len, memo[x+1][y] if memo[x+1][y]>=0 else self.findLongestPath(matrix, memo, x+1, y))
            
        memo[x][y] = max_len + 1
        return max_len + 1
        

if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.longestIncreasingPath([
  [9,9,4],
  [6,6,8],
  [2,1,1]
]) #4
    print solution.longestIncreasingPath([
  [3,4,5],
  [3,2,6],
  [2,2,1]
]) #4
    
    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]