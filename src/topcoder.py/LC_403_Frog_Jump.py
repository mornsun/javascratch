#!/usr/bin/env python
#coding=utf8

'''
A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone.

Initially, the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.

Note:

The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 2^31.
The first stone's position is always 0.
Example 1:

[0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping 
1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
2 units to the 4th stone, then 3 units to the 6th stone, 
4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:

[0,1,2,3,4,8,9,11]

Return false. There is no way to jump to the last stone as 
the gap between the 5th and 6th stone is too large.

Related Topics 
Dynamic Programming 

f(x,k) = f(x-k,k) OR f(x-k,k-1) OR f(x-k,k+1)
      OR f(x-k-1,k+1) OR f(x-k-1,k+2) OR f(x-k-1,k)
      OR f(x-k+1,k-1) OR f(x-k+1,k) OR f(x-k+1,k-2)

@author: Chauncey
beat %
'''

import heapq
import datetime
import time
import sys

class Solution(object):
    def canCross(self, stones):
        """
        :type stones: List[int]
        :rtype: bool
        """
        if not stones or len(stones)<2 or stones[0]!=0 or stones[1]!=1:
          return False
        n = len(stones)
        f = [set() for _ in xrange(n)]
        fmax = [0] * n
        f[0].add(0)
        f[1].add(1)
        fmax[1] = max(f[1])
        left = 1
        for i in xrange(2, n):
          x = stones[i]
          for j in xrange (left, i):
            if x<=stones[j]+fmax[j]:
              left = j
              break
          for j in xrange (left, i):
            for k in f[j]:
              if x==stones[j]+k:
                f[i].add(k)
              elif x==stones[j]+k-1:
                f[i].add(k-1)
              elif x==stones[j]+k+1:
                f[i].add(k+1)
          fmax[i] = max(f[i]) if len(f[i])>0 else 0

        return len(f[n-1])>0


if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.canCross([0,1,3,5,6,8,12,17]) #true
    print solution.canCross([0,1,2,3,4,8,9,11]) #false
    print solution.canCross([0,1,2147483647]) #false
    print solution.canCross([0,1,3,6,10,15,19,21,24,26,30,33]) #true
    
    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]