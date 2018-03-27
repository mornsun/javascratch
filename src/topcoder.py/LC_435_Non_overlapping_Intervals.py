#!/usr/bin/env python
#coding=utf8

'''
Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note:
You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
Example 1:
Input: [ [1,2], [2,3], [3,4], [1,3] ]

Output: 1

Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:
Input: [ [1,2], [1,2], [1,2] ]

Output: 2

Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:
Input: [ [1,2], [2,3] ]

Output: 0

Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

Related Topics 
Greedy
Similar Questions 
Minimum Number of Arrows to Burst Balloons

Which interval would be the best first (leftmost) interval to keep? One that ends first, as it leaves the most room for the rest. So take one with smallest end, remove all the bad ones overlapping it, and repeat (taking the one with smallest end of the remaining ones). For the overlap test, just keep track of the current end, initialized with negative infinity.

@author: Chauncey
beat 50%
'''

import heapq
import datetime
import time
import sys
import collections
import operator
import bisect

# Definition for an interval.
class Interval(object):
    def __init__(self, s=0, e=0):
        self.start = s
        self.end = e

class Solution(object):
    def eraseOverlapIntervals(self, intervals):
        """
        :type intervals: List[Interval]
        :rtype: int
        """
        if not intervals:
            return 0

        cnt = 1
        #intervals.sort(key=lambda item: (item[0], item[1]))
        #intervals.sort(key=attrgetter('age'))
        intervals.sort(key=operator.itemgetter(1))
        print intervals
        end = intervals[0][1]

        for i in xrange(1, len(intervals)):
            interval = intervals[i]
            if interval[0] >= end:
                end = interval[1]
                cnt += 1

        return len(intervals) - cnt

    def eraseOverlapIntervals3(self, intervals):
        start = float('inf')
        erased = 0
        for i in sorted(intervals, key=lambda i: i[0], reverse=True):
            if i[1] > start:
                erased += 1
            else:
                start = i[0]
        return erased

    def eraseOverlapIntervals2(self, intervals):
        end = float('-inf')
        erased = 0
        for i in sorted(intervals, key=lambda i: i[1]):
            if i[0] >= end:
                end = i[1]
            else:
                erased += 1
        return erased

    def eraseOverlapIntervals1(self, intervals):
        """
        :type intervals: List[Interval]
        :rtype: int
        """
        if not intervals:
            return 0

        cnt = 1
        #intervals.sort(key=lambda item: (item[0], item[1]))
        #intervals.sort(key=attrgetter('age'))
        intervals.sort(key=lambda item: item.end)
        end = intervals[0].end

        for i in xrange(1, len(intervals)):
            interval = intervals[i]
            if interval.start >= end:
                end = interval.end
                cnt += 1

        return len(intervals) - cnt
        
        


if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.eraseOverlapIntervals3([ [1,2], [2,3], [3,4], [1,3] ]) #1
    print solution.eraseOverlapIntervals3([ [1,2], [1,2], [1,2] ]) #2
    print solution.eraseOverlapIntervals3([ [1,2], [2,3] ]) #0

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()