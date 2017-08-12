#!/usr/bin/env python
#coding=utf8

'''
Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.

The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.


After the rain, water are trapped between the blocks. The total volume of water trapped is 4.

Related Topics 
Breadth-first Search, Heap 
Similar Questions 
Trapping Rain Water 

@author: Chauncey
beat 86.40%
'''

import heapq
import datetime
import time
import sys

class Solution(object):
    def trapRainWater(self, heightMap):
        """
        :type heightMap: List[List[int]]
        :rtype: int
        """
        if not heightMap or not heightMap[0]:
          return 0
        row = len(heightMap)
        col = len(heightMap[0])
        heap = []
        visited = [[False]*col for _ in xrange(row)]

        for y in xrange(row):
          for x in xrange(col):
            if x==0 or y==0 or x==col-1 or y==row-1:
              heapq.heappush(heap, (heightMap[y][x], y, x))
              visited[y][x] = True

        ans = 0
        while heap:
          height, y, x = heapq.heappop(heap)
          for i, j in ((y-1, x), (y+1, x), (y, x-1), (y, x+1)):
            if 0<i<row-1 and 0<j<col-1 and not visited[i][j]:
              ans += max(0, height-heightMap[i][j])
              heapq.heappush(heap, (max(height, heightMap[i][j]), i, j))
              visited[i][j] = True

        return ans


if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.trapRainWater([
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]) #4

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]