'''
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


Above is a 3 x 7 grid. How many possible unique paths are there?

Note: m and n will be at most 100.

Hide Tags Array Dynamic Programming
Hide Similar Problems (M) Unique Paths II (M) Minimum Path Sum (H) Dungeon Game

@author: Chauncey
'''
class Solution:
    # @param {integer} m
    # @param {integer} n
    # @return {integer}
    def uniquePaths(self, m, n):
        if m<1 or n<1: return 0
        dp = [1 for i in xrange(n)]
        for i in xrange(1,m):
            for j in xrange(1,n):
                dp[j] = dp[j-1] + dp[j]
        return dp[n-1]
        
solution = Solution();
print solution.uniquePaths(5,5)