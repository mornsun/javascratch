#!/usr/bin/env python
#coding=utf8

'''
Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.

For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:

[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]
Follow up:
What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?

Related Topics 
Binary Search Tree 
Similar Questions 
Summary Ranges Find Right Interval

@author: Chauncey
beat 35.85%
'''

import heapq
import datetime
import time
import sys
import bisect
import collections
# Definition for an interval.
class Interval(object):
    def __init__(self, s=0, e=0):
        self.start = s
        self.end = e

class SummaryRanges(object):

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.intervals = []
        

    def addNum(self, val):
        """
        :type val: int
        :rtype: void
        """
        new_interval = [val, val]
        idx = bisect.bisect(self.intervals, new_interval)-1
        #print idx, new_interval
        inserted = False
        if idx != -1:
            if self.intervals[idx][1]+1 == val:
                self.intervals[idx][1] += 1
                inserted = True
                if idx+1 < len(self.intervals):
                    if self.intervals[idx+1][0]-1 == val:
                        self.intervals[idx][1] = self.intervals[idx+1][1]
                        del self.intervals[idx+1]
            elif self.intervals[idx][1] >= val:
                inserted = True

        if not inserted and idx+1 < len(self.intervals):
            if self.intervals[idx+1][0]-1 == val:
                self.intervals[idx+1][0] = val
                inserted = True
            elif self.intervals[idx+1][0] == val:
                inserted = True

        if not inserted:
            bisect.insort(self.intervals, new_interval)
        

    def getIntervals(self):
        """
        :rtype: List[Interval]
        """
        return self.intervals
        

# Your SummaryRanges object will be instantiated and called as such:

class MyOrderedDict(collections.OrderedDict):
    def next_key(self, key):
        next = self._OrderedDict__map[key][1]
        if next is self._OrderedDict__root:
            print ("{!r} is the last key".format(key))
        return next[2]

    def prev_key(self, key):
        prev = self._OrderedDict__map[key][0]
        if prev is self._OrderedDict__root:
            #raise ValueError("{!r} is the first key".format(key))
            print ("{!r} is the first key".format(key))
        return prev[2]

    def first_key(self):
        for key in self: return key
        raise ValueError("OrderedDict() is empty")

if __name__ == '__main__':
    obj = SummaryRanges()
    start_time = datetime.datetime.now()

    obj.addNum(1)
    print obj.getIntervals()
    obj.addNum(3)
    print obj.getIntervals()
    obj.addNum(7)
    print obj.getIntervals()
    obj.addNum(2)
    print obj.getIntervals()
    obj.addNum(6)
    print obj.getIntervals()

    '''od = MyOrderedDict([('apple', 4), ('banana', 3), ('orange', 2), ('pear', 1)])
    print od.next_key("apple")
    print od.next_key("banana")
    print od.next_key("orange")
    print od.next_key("pear")

    print
    print od.prev_key("apple")
    print od.prev_key("banana")
    print od.prev_key("orange")
    print od.prev_key("pear")'''
    
    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()


    obj = SummaryRanges()
    start_time = datetime.datetime.now()

    obj.addNum(6)
    print obj.getIntervals() #[[6,6]]
    obj.addNum(6)
    print obj.getIntervals() #[[6,6]]
    obj.addNum(0)
    print obj.getIntervals() #[[0,0],[6,6]]
    obj.addNum(4)
    print obj.getIntervals() #[[0,0],[4,4],[6,6]]
    obj.addNum(8)
    print obj.getIntervals() #[[0,0],[4,4],[6,6],[8,8]]
    obj.addNum(7)
    print obj.getIntervals() #[[0,0],[4,4],[6,8]]
    obj.addNum(6)
    print obj.getIntervals() #[[0,0],[4,4],[6,8]]
    obj.addNum(4)
    print obj.getIntervals() #[[0,0],[4,4],[6,8]]
    obj.addNum(7)
    print obj.getIntervals() #[[0,0],[4,4],[6,8]]
    obj.addNum(5)
    print obj.getIntervals() #[[0,0],[4,8]]
    
    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
