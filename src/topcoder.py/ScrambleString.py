'''

@author: Chauncey
'''
import sys
import collections

class Solution:
    # @param {string} s1
    # @param {string} s2
    # @return {boolean}
    def isScramble(self, s1, s2):
        if s1 is None or s2 is None: return False
        l = len(s1)
        l2 = len(s2)
        if l != l2: return False
        dp = [[[False for j in xrange(l)] for i in xrange(l)] for n in xrange(l+1)]
        for i in xrange(l):
            for j in xrange(l):
                if s1[i] == s2[j]:
                    dp[1][i][j] = True
                
        for n in xrange(2,l+1):
            for i in xrange(l-n+1):
                for j in xrange(l-n+1):
                    for k in xrange(1,n):
                        if dp[k][i][j] and dp[n-k][i+k][j+k] \
                                or dp[k][i][j+n-k] and dp[n-k][i+k][j]:
                            dp[n][i][j] = True
                            break
        return dp[l][0][0]

if __name__ == '__main__':
    solution = Solution();
    print solution.isScramble('rgeat', 'great')
    
    
    