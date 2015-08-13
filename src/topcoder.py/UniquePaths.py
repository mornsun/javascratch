'''

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
print solution.uniquePaths(1,2)