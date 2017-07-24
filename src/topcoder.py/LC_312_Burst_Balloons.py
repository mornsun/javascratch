#!/usr/bin/env python
#coding=utf8

'''
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
You are asked to burst all the balloons.
If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i.
After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

Related Topics 
Dynamic Programming Divide and Conquer

@author: Chauncey
beat 68.93%
'''

import heapq
import datetime
import time
import sys

class Solution(object):
    def maxCoins(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if not nums:
            return 0
        a = [1] + nums + [1]
        n = len(a)
        f = [[0] * n for _ in xrange(n)]

        for gap in xrange(2, n):
            for left in xrange(0, n-gap):
                right = left + gap
                for i in xrange(left+1, right):
                    f[left][right] = max(f[left][right], a[left] * a[i] * a[right] + f[left][i] + f[i][right])

        return f[0][n-1]

        

if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.maxCoins([39,27,92,51,36,7,30,98,75,41,50,3,45,6,29,58,73,22,63,88,56,20,36,63,91,23,55,50,63,70,19,37,11,32,19,99,18,55,97,82,8,48,72,76,15,8,86,48,59,41,65,75,62,67,98,61,38,34,68,19,25,6,15,72,92,78,89,49,37,78,59,22,26,72,13,43,35,99,59,47,99,35,79,41,80,64,64,93,85,21,50,13,60,97,29,13,49,70,1,4,38,78,83,14,15,31,57,68,29,54,7,52,15,29,42,26,76,50,15,78,80,97,13,99,39,74,61,81,0,90,19,45,15,61,45,58,29,36,88,0,2,94,11,77,25,89,72,91,56,63,90,10,74,24,9,70,66,25,82,48,91,92,62,82,82,99,19,34,85,39,78,91,49,63,30,7,22,13,78,63,87,53,14,55,1,36,2,65,74,36,25,27,23,67,72,14,3,65,46,10,58])
    #0
    print solution.maxCoins([3, 1, 5, 8]) #167
    
    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]