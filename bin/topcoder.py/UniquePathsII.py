'''
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.

Hide Tags Array Dynamic Programming
Hide Similar Problems (M) Unique Paths

@author: Chauncey
'''
class Solution:
    # @param {integer[][]} obstacleGrid
    # @return {integer}
    def uniquePathsWithObstacles(self, obstacleGrid):
        if obstacleGrid is None or len(obstacleGrid) is 0 or len(obstacleGrid[0]) is 0: return 0
        m = len(obstacleGrid)
        n = len(obstacleGrid[0])
        dp = [0 for i in xrange(n)]
        for j in xrange(n):
            if obstacleGrid[0][j] is 0:
                dp[j] = 1
            else:
                break
        for i in xrange(1,m):
            dp[0] = 0 if obstacleGrid[i][0] else dp[0]
            for j in xrange(1,n):
                dp[j] = 0 if obstacleGrid[i][j] else dp[j-1] + dp[j]
            print dp
        return dp[n-1]
        
solution = Solution();
obstacleGrid = [
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
m = len(obstacleGrid)
n = len(obstacleGrid[0])
print m,n
print solution.uniquePathsWithObstacles(obstacleGrid)