#!/usr/bin/env python
#coding=utf8

'''
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"

Related Topics 
Stack, Greedy 


@author: Chauncey
'''

import heapq
import datetime
import time

class Solution(object):
    def removeDuplicateLetters(self, s):
        """
        :type s: str
        :rtype: str
        """
        
        

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