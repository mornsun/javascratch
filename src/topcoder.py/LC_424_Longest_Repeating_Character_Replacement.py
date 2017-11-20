#!/usr/bin/env python
#coding=utf8

'''
Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 10^4.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.


@author: Chauncey
beat 96.22%
'''

import heapq
import datetime
import time
import sys
import collections

class Solution(object):
    def characterReplacement(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: int
        """
        if not s or k<0:
            return 0

        n = len(s)
        cnts = [0] * 26
        maxi = 0
        max_cnt = 0
        start = 0

        for end in xrange(n):
            #print s[i],
            c = ord(s[end])-65
            cnts[c] += 1
            max_cnt = max(max_cnt, cnts[c])
            while end - start - max_cnt >= k:
                cnts[ord(s[start])-65] -= 1
                start += 1
            maxi = max(maxi, end - start + 1)
        return maxi


if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.characterReplacement("ABAB", 2) #4
    print solution.characterReplacement("AABABBA", 1) #4
    print solution.characterReplacement("AABA", 0) #2
    print solution.characterReplacement("ABBB", 2) #4
    print solution.characterReplacement("BAAAB", 2) #5

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()