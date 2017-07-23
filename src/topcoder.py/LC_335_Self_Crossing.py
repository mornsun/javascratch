#!/usr/bin/env python
#coding=utf8

'''
You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north, then x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on. In other words, after each move your direction changes counter-clockwise.

Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.

Example 1:
Given x = 
[2, 1, 1, 2]
,
?????
?   ?
???????>
    ?

Return true (self crossing)

Example 2:
Given x = 
[1, 2, 3, 4]
,
????????
?      ?
?
?
?????????????>

Return false (not self crossing)

Example 3:
Given x = 
[1, 1, 1, 1]
,
?????
?   ?
?????>

Return true (self crossing)

Related Topics 
Math

@author: Chauncey
beat 92.56%
'''

import heapq
import datetime
import time
import sys

class Solution(object):
    def isSelfCrossing(self, x):
        """
        :type x: List[int]
        :rtype: bool
        """
        b = c = d = e = 0
        for a in x:
            if d >= b > 0 and (a >= c or a >= c-e >= 0 and f >= d-b):
                return True
            b, c, d, e, f = a, b, c, d, e
        return False
        

if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.isSelfCrossing([2, 1, 1, 2]) #true
    print solution.isSelfCrossing([1, 2, 3, 4]) #false
    print solution.isSelfCrossing([1, 1, 1, 1]) #true
    print solution.isSelfCrossing([1,1,2,1,1]) #true
    
    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]