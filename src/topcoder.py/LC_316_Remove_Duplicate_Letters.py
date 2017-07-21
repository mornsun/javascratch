#!/usr/bin/env python
#coding=utf8

'''
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc" abcd
Return "acdb"

Related Topics 
Stack, Greedy 


@author: Chauncey
'''

import heapq
import datetime
import time
import collections

class Solution(object):
    def removeDuplicateLetters(self, s):
        """
        :type s: str
        :rtype: str
        """
        if s is None or len(s)<=1:
          return s

        counter = collections.Counter(s)
        stack = []
        got = set()

        for c in s:
          counter[c] -= 1
          if c in got:
            continue
          got.add(c)
          while stack and stack[-1]>c and counter[stack[-1]]>0:
            top = stack.pop()
            got.remove(top)
          stack.append(c)

        return ''.join(stack)

    def removeDuplicateLetters(self, s):
        for c in sorted(set(s)):
            suffix = s[s.index(c):]
            if set(suffix) == set(s):
                return c + self.removeDuplicateLetters(suffix.replace(c, ''))
        return ''
        

if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.removeDuplicateLetters("bcabc") #abc
    print solution.removeDuplicateLetters("cbacdcbc") #acdb
    
    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]