'''
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.

Hide Tags Array Stack
Hide Similar Problems (H) Maximal Rectangle

@author: Chauncey
'''
import sys
import collections

class Solution:
    # @param {integer[]} height
    # @return {integer}
    def largestRectangleArea(self, height):
        if height is None or len(height) == 0: return 0
        height.append(0)
        l = len(height)
        stack = []
        max_area = 0
        i = 0
        while i < l:
            if len(stack) == 0 or height[i] > height[stack[-1]]:
                stack.append(i)
                i += 1
            else:
                tmp = stack.pop()
                max_area = max(max_area, height[tmp]*(i if len(stack)==0 else i-stack[-1]-1))
                #print max_area
        return max_area
        
        
    def largestRectangleArea1(self, height):
        if height is None or len(height) == 0: return 0
        l = len(height)
        max_area = -sys.maxsize-1
        for i in xrange(l):
            dp = height[i]
            area = i;
            #print i,0,dp[i][0],area
            if area > max_area:
                max_area = area
            for j in xrange(1,l-i):
                dp = min(dp, height[i+j])
                area = (j+1)*dp;
                #print i,j,dp[i][j],area
                if area > max_area:
                    max_area = area
        return max_area
         
            
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.largestRectangleArea([2,1,5,6,4,1])
    
    
    