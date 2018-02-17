#!/usr/bin/env python
#coding=utf8

'''
In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.

Example:

Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
Output: True
Explanation:
We can transform start to end following these steps:
RXXLRXRXL ->
XRXLRXRXL ->
XRLXRXRXL ->
XRLXXRRXL ->
XRLXXRRLX
Note:

1 <= len(start) = len(end) <= 10000.
Both start and end will only consist of characters in {'L', 'R', 'X'}.

Related Topics 
Brainteaser

@author: Chauncey
beat %
'''

import heapq
import datetime
import time
import sys
import collections
class Solution(object):
    def canTransform1(self, start, end):
        """
        :type start: str
        :type end: str
        :rtype: bool
        """
        if not start:
            return False

        if start == end:
            return True

        # check equality of len
        l = len(start)

        if l != len(end):
            return False

        # check equality of chars
        c_R = 0
        c_L = 0
        c_X = 0

        for c in start:
            if c == 'R':
                c_R += 1
            elif c == 'L':
                c_L += 1
            elif c == 'X':
                c_X += 1
            else:
                return False

        for c in end:
            if c == 'R':
                c_R -= 1
            elif c == 'L':
                c_L -= 1
            elif c == 'X':
                c_X -= 1
            else:
                return False

        if c_R or c_L or c_X:
            return False

        # search BFS
        curr = [start]
        searched = set(curr)
        next = []

        while curr:
            for s in curr:
                for i in xrange(l-1):
                    snippet = s[i:i+2]
                    new_snippet = None

                    if snippet == 'XL':
                        new_snippet = 'LX'
                    elif snippet == 'RX':
                        new_snippet = 'XR'

                    if new_snippet:
                        new_s = s[:i] + new_snippet + s[i+2:]

                        if new_s not in searched:
                            if new_s == end:
                                return True

                            searched.add(new_s)
                            next.append(new_s)

            curr = next;
            next = []

        return False

    def canTransform(self, start, end):
        """
        :type start: str
        :type end: str
        :rtype: bool
        """
        if not start:
            return False

        if start == end:
            return True

        # check equality of len
        l = len(start)

        if l != len(end):
            return False

        # search brainteaser
        c_L = 0
        c_R = 0
        for i in xrange(l):
            if c_L > 0:
                if start[i] == 'R' or end[i] == 'R':
                    return False
                if start[i] == 'L':
                    c_L -= 1
                if end[i] == 'L':
                    c_L += 1
            elif c_R > 0:
                if end[i] == 'L' or start[i] == 'L':
                    return False
                if end[i] == 'R':
                    c_R -= 1
                if start[i] == 'R':
                    c_R += 1
            elif c_L == 0 and c_R == 0:
                if start[i] == 'X' and end[i] == 'L':
                    c_L = 1
                elif start[i] == 'R' and end[i] == 'X':
                    c_R = 1
                elif start[i] != end[i]:
                    return False
            #print start[i], end[i], c_L, c_R

        return c_L == 0 and c_R == 0


if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.canTransform("RXXLRXRXL", "XRLXXRRLX")
    #True
    print solution.canTransform("", "")
    #False
    print solution.canTransform("", "RX")
    #False
    print solution.canTransform("RXXLRXRXR", "XRLXXRRLX")
    #False
    print solution.canTransform("RL", "LR")
    #False
    print solution.canTransform("XXXLXXLXXLXXRXXXRXLXRXRXXXXXLX", "LLLXXXXXXXXXXXXXRRLXXXXXXXRRLX")
    #True

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()