#!/usr/bin/env python
#coding=utf8

'''
Given a non negative integer number num.
For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.

Related Topics 
Dynamic Programming, Bit Manipulation 
Similar Questions 
Number of 1 Bits

@author: Chauncey
beat 95.72%
'''

import heapq
import datetime
import time
import sys

class Solution(object):
    def countBits(self, num):
        """
        :type num: int
        :rtype: List[int]
        """
        f = [0] * (num+1)
        for x in xrange(0, num+1):
            f[x] = f[x>>1] + (x&1)
        return f

        

if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.countBits(5)
    #[0,1,1,2,1,2]
    print solution.countBits(0) #None
    print solution.countBits(None) #None
    
    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]