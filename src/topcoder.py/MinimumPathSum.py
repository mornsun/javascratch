'''

@author: Chauncey
'''
class Solution:
    # @param {integer[][]} grid
    # @return {integer}
    def minPathSum(self, grid):
        if grid is None or len(grid) is 0 or len(grid[0]) is 0: return 0
        m = len(grid)
        n = len(grid[0])
        dp = [grid[0][0],]
        for j in xrange(1,n):
            dp.append(dp[j-1]+grid[0][j])
        for i in xrange(1,m):
            dp[0] += grid[i][0]
            for j in xrange(1,n):
                dp[j] = min(dp[j-1], dp[j]) + grid[i][j]
        return dp[n-1]
        
solution = Solution();
grid = [
  [1,2,3],
  [4,5,6],
  [7,8,9]
]
print solution.minPathSum(grid)