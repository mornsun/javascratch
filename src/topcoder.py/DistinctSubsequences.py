'''

@author: Chauncey
'''

class Solution:
    # @param {string} s
    # @param {string} t
    # @return {integer}
    def numDistinct(self, s, t):
        if s is None or len(s) == 0: return 0
        if t is None or len(t) == 0: return 0
        m = len(s)
        n = len(t)
        #f[i][j] = s[i]==t[j]: f[i-1][j]+f[i-1][j-1] else: f[i-1][j]
        dp = [0] * n
        if s[0]==t[0]: dp[0] = 1
        for i in xrange(1,m):
            k = i+1 if i+1<n else n
            for j in xrange(k-1,0,-1):
                dp[j] += dp[j-1] if s[i]==t[j] else 0
            if s[i]==t[0]: dp[0] = dp[0] + 1
        return dp[n-1]
                
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.numDistinct('ddd', 'dd')
    
    
    