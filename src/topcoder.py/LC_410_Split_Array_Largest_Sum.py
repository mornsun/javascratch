#!/usr/bin/env python
#coding=utf8

'''
Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.
Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.

Related Topics 
Binary Search Dynamic Programming

f[n,m] = min(max(f[i,m-1], f[n,1]-f[i,1]))


@author: Chauncey
beat 86.40%
'''

import heapq
import datetime
import time
import sys

class Solution(object):
    def splitArray(self, nums, m):
        """
        :type nums: List[int]
        :type m: int
        :rtype: int
        """
        if not nums or m<=0:
          return 0

        n = len(nums)

        f = [[0] * m for _ in xrange(n)]
        #print n,len(f),m,len(f[0])

        f[0][0] = nums[0]
        for i in xrange(1, n):
          f[i][0] = f[i-1][0] + nums[i]
          for j in xrange(1, min(m,i+1)):
            maxi = sys.maxint
            for k in xrange(0, i):
              maxi = min(maxi, max(f[k][j-1], f[i][0]-f[k][0]))
              #print max(f[k][j-1], f[i][0]-f[k][0]),
            #print
            f[i][j] = maxi

        return f[n-1][m-1]
        


if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.splitArray([7,2,5,10,8], 2) #18
    print solution.splitArray([1,2147483647], 2) #2147483647

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]