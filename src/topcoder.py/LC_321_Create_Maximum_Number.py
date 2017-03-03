#!/usr/bin/env python
#coding=utf8

'''
Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.

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


@author: Chauncey
'''

import heapq
import datetime
import time

class Solution(object):
    def maxNumber(self, nums1, nums2, k):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :type k: int
        :rtype: List[int]
        """
        


if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.maxProduct(["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]) #16
    print solution.maxProduct(["a", "ab", "abc", "d", "cd", "bcd", "abcd"]) #4
    print solution.maxProduct(["a", "aa", "aaa", "aaaa"]) #0
    #time.sleep(1.0)

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]