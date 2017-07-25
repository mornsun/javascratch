#!/usr/bin/env python
#coding=utf8

'''
Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.

Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).


Example 1:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [3,2,4,4],
  [1,3,2,4],
  [2,3,3,4]
]

Return true. All 5 rectangles together form an exact cover of a rectangular region.

Example 2:

rectangles = [
  [1,1,2,3],
  [1,3,2,4],
  [3,1,4,2],
  [3,2,4,4]
]

Return false. Because there is a gap between the two rectangular regions.

Example 3:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [3,2,4,4]
]

Return false. Because there is a gap in the top center.

Example 4:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [2,2,4,4]
]

Return false. Because two of the rectangles overlap with each other.

@author: Chauncey
beat 97.14%
'''

import heapq
import datetime
import time
import sys

class Solution(object):
    def isRectangleCover(self, rectangles):
        """
        :type rectangles: List[List[int]]
        :rtype: bool
        """
        if not rectangles or len(rectangles[0]) == 0:
            return False

        x1 = sys.maxint
        x2 = 0
        y1 = sys.maxint
        y2 = 0
        
        point_set = set()
        area = 0
        
        for rect in rectangles:
            x1 = min(rect[0], x1)
            y1 = min(rect[1], y1)
            x2 = max(rect[2], x2)
            y2 = max(rect[3], y2)
            
            area += (rect[2] - rect[0]) * (rect[3] - rect[1]);
            
            s1 = (rect[0],rect[1])
            s2 = (rect[0],rect[3])
            s3 = (rect[2],rect[3])
            s4 = (rect[2],rect[1])
            
            point_set.remove(s1) if s1 in point_set else point_set.add(s1)
            point_set.remove(s2) if s2 in point_set else point_set.add(s2)
            point_set.remove(s3) if s3 in point_set else point_set.add(s3)
            point_set.remove(s4) if s4 in point_set else point_set.add(s4)
        
        if (x1,y1) not in point_set or (x1,y2) not in point_set or (x2,y1) not in point_set or (x2,y2) not in point_set or len(point_set)!=4:
            return False
        
        return area == (x2-x1) * (y2-y1)
        

        

if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.isRectangleCover([
  [1,1,3,3],
  [3,1,4,2],
  [3,2,4,4],
  [1,3,2,4],
  [2,3,3,4]
])
    #True
    print solution.isRectangleCover([
  [1,1,2,3],
  [1,3,2,4],
  [3,1,4,2],
  [3,2,4,4]
]) #False
    print solution.isRectangleCover([
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [3,2,4,4]
]) #False
    print solution.isRectangleCover([
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [2,2,4,4]
]) #False

    print solution.isRectangleCover([
        [0,0,2,2],
        [1,1,3,3],
        [2,0,3,1],
        [0,3,3,4]
]) #False
    
    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]