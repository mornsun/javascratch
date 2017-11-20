#!/usr/bin/env python
#coding=utf8

'''
Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.

Note:
Input contains only lowercase English letters.
Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
Input length is less than 50,000.
Example 1:
Input: "owoztneoer"

Output: "012"
Example 2:
Input: "fviefuro"

Output: "45"

zero, one, two, three, four, five, six, seven, eight, nine

@author: Chauncey
beat 24.02%
'''

import heapq
import datetime
import time
import sys
import collections

class Solution(object):
    def originalDigits(self, s):
        """
        :type s: str
        :rtype: str
        """
        if not s:
            return ""
        cnts = [0] * 10
        for c in s:
            if c == 'z': cnts[0]+=1
            elif c == 'o': cnts[1]+=1 #1-0-2-4
            elif c == 'w': cnts[2]+=1
            elif c == 'h': cnts[3]+=1 #3-8
            elif c == 'u': cnts[4]+=1
            elif c == 'f': cnts[5]+=1 #5-4
            elif c == 'x': cnts[6]+=1
            elif c == 's': cnts[7]+=1 #7-6
            elif c == 'g': cnts[8]+=1
            elif c == 'i': cnts[9]+=1 #9-5-6-8
        cnts[1] -= cnts[0]+cnts[2]+cnts[4]
        cnts[3] -= cnts[8]
        cnts[5] -= cnts[4]
        cnts[7] -= cnts[6]
        cnts[9] -= cnts[5]+cnts[6]+cnts[8]
        ret = ""
        for ch in xrange(10):
            ret += str(ch) * cnts[ch]
                
        return ret


if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.originalDigits("owoztneoer") #012
    print solution.originalDigits("fviefuro") #45
    print solution.originalDigits("nnei") #9

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()