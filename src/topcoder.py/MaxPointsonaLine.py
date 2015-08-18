'''

@author: Chauncey
'''
import sys

# Definition for a point.
class Point:
    def __init__(self, a=0, b=0):
        self.x = a
        self.y = b

class Solution:
    # @param {Point[]} points
    # @return {integer}
    def maxPoints(self, points):
        if points is None or len(points)==0: return 0
        pts = {} #points at same coordinate, initially only this point
        for point in points:
            cnt = pts.get((point.x, point.y))
            if cnt is None:
                pts[(point.x, point.y)] = 1
            else:
                pts[(point.x, point.y)] = cnt+1
        pts = pts.items()
        l = len(pts)
                
        map = {}
        maxnum = 0
        for i in xrange(l):
            (x1,y1),cnt1 = pts[i]
            if cnt1 > maxnum: maxnum = cnt1
            for j in xrange(i+1, l):
                (x2,y2),cnt2 = pts[j]
                if x2-x1 == 0:
                    slope = sys.maxsize
                else:
                    slope = (y2-y1+0.0)/(x2-x1)
                cnt = map.get(slope)
                #print slope,cnt1,cnt2,cnt
                if cnt is None:
                    cnt = cnt1 + cnt2
                    map[slope] = cnt
                    if cnt > maxnum:
                        maxnum = cnt
                else:
                    cnt += cnt2
                    map[slope] = cnt
                    if cnt > maxnum:
                        maxnum = cnt
            map.clear()
        return maxnum
                
                    
if __name__ == '__main__':
    solution = Solution();
    pt1 = Point(1,0)
    pt2 = Point(0,0)
    pt3 = Point(3,6)
    print solution.maxPoints([pt1,pt2,pt3])
    
    
    