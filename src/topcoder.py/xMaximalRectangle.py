'''
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.

Hide Tags Array Hash Table Stack Dynamic Programming
Hide Similar Problems (H) Largest Rectangle in Histogram (M) Maximal Square

@author: Chauncey
'''
import sys
import collections

class Solution:
    # @param {character[][]} matrix
    # @return {integer}
    def maximalRectangle(self, matrix):
        if matrix is None or len(matrix) == 0 or matrix[0] is None or len(matrix[0]) == 0: return 0
        max_area = 0
        m = len(matrix)
        n = len(matrix[0])
        l = [0 for i in xrange(n)]
        r = [n for i in xrange(n)]
        h = [0 for i in xrange(n)]
        for i in xrange(m):
            left = 0
            for j in xrange(n):
                if matrix[i][j] == '0':
                    left = j+1
                    h[j] = 0
                    l[j] = 0
                    r[j] = n
                else:
                    l[j] = max(l[j], left)
                    h[j] += 1
            right = n
            for j in xrange(n-1,-1,-1):
                if matrix[i][j] == '0':
                    right = j
                else:
                    r[j] = min(r[j], right)
                    max_area = max(max_area, (r[j]-l[j])*h[j])
                    #print r[j], l[j], h[j]
        return max_area
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.maximalRectangle(["00110",'11111','01110','00110'])
    
    
    