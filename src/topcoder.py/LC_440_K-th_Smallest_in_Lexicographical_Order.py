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
beat 5.56%
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
        if k<=0 or n<k:
            return 0

        memo = [0]
        power = [1]

        test = n
        cnt = 0
        pws = 1
        while test > 0:
            test /= 10
            cnt *= 10
            cnt += 1
            memo.append(cnt)
            pws *= 10
            power.append(pws)
        bitcnt = len(memo) - 1

        res = []
        self.findKthNumberHelper(n+memo[bitcnt], k+memo[bitcnt], memo, power, bitcnt, res)
        num = 0
        for i in res:
            num *= 10
            num += i
        return num

        
    def findKthNumberHelper(self, n, k, memo, power, bitcnt, res):
        #print n, k, memo, power, bitcnt
        if n < 10:
            res.append(k-1)
            return

        firstbit = (n-memo[bitcnt]+1) / power[bitcnt-1]
        #print n-memo[bitcnt]+1, power[bitcnt-1], firstbit
        if firstbit > 9:
            firstbit = 9
        mm = [0] * 10
        for i in xrange(firstbit+1, 10):
            mm[i] = memo[bitcnt-1]
        for i in xrange(firstbit):
            mm[i] = memo[bitcnt]
        mm[firstbit] = n - sum(mm)
        lsum = sum(mm[:firstbit])
        rsum = sum(mm[firstbit+1:])

        idx = 0
        while idx<10:
            if k > mm[idx]:
                k -= mm[idx]
            else:
                break
            idx+=1
        #print mm, k, idx, idx*power[bitcnt-1]
        #print

        new_n = n-sum(mm[:idx])-sum(mm[idx+1:])-1
        res.append(idx)
        if k != 1:
            self.findKthNumberHelper(new_n, k-1, memo, power, bitcnt-1, res)


if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.findKthNumber(13, 2)
    #10, [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]
    print solution.findKthNumber(13, 8)
    #4, [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]
    print solution.findKthNumber(13, 1)
    #1, [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]
    print solution.findKthNumber(23, 13)
    #20, [1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 2, 20, 21, 22, 23, 3, 4, 5, 6, 7, 8, 9]
    print solution.findKthNumber(23, 15)
    #22, [1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 2, 20, 21, 22, 23, 3, 4, 5, 6, 7, 8, 9]
    print solution.findKthNumber(4289384, 1922239)
    print solution.findKthNumber(1, 1)
    #1
    print solution.findKthNumber(10000, 10000)
    #9999

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()