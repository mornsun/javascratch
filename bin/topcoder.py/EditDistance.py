'''
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
Hide Tags Dynamic Programming String
Hide Similar Problems (M) One Edit Distance

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
        
        #f[i][j] = min(f[i-1][j]+1, f[i][j-1]+1, f[i-1][j-1]+(0 if word1[i-1]==word2[j-1] else 1))
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
    
    
    