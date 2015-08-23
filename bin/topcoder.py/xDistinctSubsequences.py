'''
Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.

Hide Tags Dynamic Programming String

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
    
    
    