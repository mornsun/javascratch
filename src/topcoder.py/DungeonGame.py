'''

@author: Chauncey
'''
class Solution:
    # @param {integer[][]} dungeon
    # @return {integer}
    def calculateMinimumHP(self, dungeon):
        if dungeon is None or len(dungeon) is 0 or len(dungeon[0]) is 0: return 0
        m = len(dungeon)
        n = len(dungeon[0])
        dp = [0 for i in xrange(n)]
        dp[n-1] = max(1, 1-dungeon[m-1][n-1])
        for j in xrange(n-2,-1,-1):
            dp[j] = max(1, dp[j+1]-dungeon[m-1][j])
        for i in xrange(m-2,-1,-1):
            dp[n-1] = max(1, dp[n-1]-dungeon[i][n-1])
            for j in xrange(n-2,-1,-1):
                dp[j] = max(1, min(dp[j+1], dp[j]) - dungeon[i][j])
        return dp[0]
        
solution = Solution();
dungeon = [[1,-3,3],
           [0,-2,0],
           [-3,-3,-3]]
print solution.calculateMinimumHP(dungeon)