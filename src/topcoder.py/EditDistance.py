'''

@author: Chauncey
'''
import sys

class Solution:
    # @param {string} word1
    # @param {string} word2
    # @return {integer}
    def minDistance(self, word1, word2):
        l1 = len(word1)
        l2 = len(word2)
        if l1 == 0: return l2
        elif l2 == 0: return l1
        
        dp = [[0 for j in xrange(l2+1)] for i in xrange(2)]
        for j in xrange(l2+1): dp[0][j] = j
        for i in xrange(1, l1+1):
            dp[1][0] = i
            for j in xrange(1, l2+1):
                dp[1][j] = min(dp[0][j]+1, dp[1][j-1]+1, dp[0][j-1]+(0 if word1[i-1]==word2[j-1] else 1))
            dp[0], dp[1] = dp[1], dp[0]
        return dp[0][l2]
         
            
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.minDistance("pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopically")
    
    
    