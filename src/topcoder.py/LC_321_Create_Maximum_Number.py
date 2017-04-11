#!/usr/bin/env python
#coding=utf8

'''
Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two.
The relative order of the digits from the same array must be preserved.
Return an array of the k digits.
You should try to optimize your time and space complexity.

Example 1:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]

Example 2:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
return [6, 7, 6, 0, 4]

Example 3:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
return [9, 8, 9]

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.

Subscribe to see which companies asked this question.

Hide Tags Dynamic Programming Greedy
Hide Similar Problems (M) Remove K Digits

dp[x][y][z] = max(dp[x-1][y][z-1]*10+nums1[x-1], dp[x][y-1][z-1]*10+nums2[y-1], dp[x-1][y][z], dp[x][y-1][z]);

@author: Chauncey
'''

import heapq
import datetime
import time

class Solution(object):
    def maxNumber1(self, nums1, nums2, k):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :type k: int
        :rtype: List[int]
        """
        l1 = len(nums1)
        l2 = len(nums2)
        dp = [[[[] for ___ in xrange(k+1)] for __ in xrange(l2+1)] for _ in xrange(l1+1)]

        for z in xrange(1, k+1):
            for y in xrange(1, l2+1):
                n1 = list(dp[0][y-1][z-1])
                n1.append(nums2[y-1])
                dp[0][y][z] = max(n1, dp[0][y-1][z]);
            for x in xrange(1, l1+1):
                n1 = list(dp[x-1][0][z-1])
                n1.append(nums1[x-1])
                dp[x][0][z] = max(n1, dp[x-1][0][z]);
                for y in xrange(1, l2+1):
                    n1 = list(dp[x-1][y][z-1])
                    n1.append(nums1[x-1])

                    n2 = list(dp[x][y-1][z-1])
                    n2.append(nums2[y-1])

                    dp[x][y][z] = max(n1, n2, dp[x-1][y][z], dp[x][y-1][z]);
                    #print '[%d][%d][%d]' % (x, y, z), dp[x][y][z],
                #print ' ',
            #print
        return dp[l1][l2][k]


    def maxNumber(self, nums1, nums2, k):
        def prep(nums, k):
            drop = len(nums) - k
            out = []
            for num in nums:
                while drop and out and out[-1] < num:
                    out.pop()
                    drop -= 1
                out.append(num)
            return out[:k]

        def merge(a, b):
            return [max(a, b).pop(0) for _ in a+b]

        return max(merge(prep(nums1, i), prep(nums2, k-i))
                   for i in range(k+1)
                   if i <= len(nums1) and k-i <= len(nums2))


if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.maxNumber([3, 4, 6, 5], [9, 1, 2, 5, 8, 3], 5) #[9, 8, 6, 5, 3]
    print solution.maxNumber([6, 7], [6, 0, 4], 5) #[6, 7, 6, 0, 4]
    print solution.maxNumber([3, 9], [8, 9], 3) #[9, 8, 9]
    print solution.maxNumber([1,5,8,1,4,0,8,5,0,7,0,5,7,6,0,5,5,2,4,3,6,4,6,6,3,8,1,1,3,1,3,5,4,3,9,5,0,3,8,1,4,9,8,8,3,4,6,2,5,4,1,1,4,6,5,2,3,6,3,5,4,3,0,7,2,5,1,5,3,3,8,2,2,7,6,7,5,9,1,2],
    [7,8,5,8,0,1,1,6,1,7,6,9,6,6,0,8,5,8,6,3,4,0,4,6,7,8,7,7,7,5,7,2,5,2,1,9,5,9,3,7,3,9,9,3,1,4,3,3,9,7,1,4,4,1,4,0,2,3,1,3,2,0,2,4,0,9,2,0,1,3,9,1,2,2,6,6,9,3,6,0], 80)
    #[9, 9, 9, 9, 9, 9, 9, 9, 9, 8, 8, 7, 7, 6, 6, 5, 5, 4, 3, 6, 4, 6, 6, 3, 8, 1, 1, 3, 1, 3, 5, 4, 3, 9, 5, 0, 3, 8, 1, 4, 9, 8, 8, 3, 4, 6, 2, 5, 4, 1, 1, 4, 6, 5, 2, 3, 6, 3, 5, 4, 3, 0, 7, 2, 5, 1, 5, 3, 3, 8, 2, 2, 7, 6, 7, 5, 9, 1, 2, 0]
    #time.sleep(1.0)

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]