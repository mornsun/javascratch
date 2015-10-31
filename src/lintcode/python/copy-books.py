'''
Given an array A of integer with size of n( means n books and number of pages of each book) and k people to copy the book. You must distribute the continuous id books to one people to copy. (You can give book A[1],A[2] to one people, but you cannot give book A[1], A[3] to one people, because book A[1] and A[3] is not continuous.) Each person have can copy one page per minute. Return the number of smallest minutes need to copy all the books.

Have you met this question in a real interview? Yes
Example
Given array A = [3,2,4], k = 2.

Return 5( First person spends 5 minutes to copy book 1 and book 2 and second person spends 4 minutes to copy book 3. )

Challenge
Could you do this in O(n*k) time ?

f[k][n] = max(f[k-1][left], f[0][n]-f[0][left])

@author: Chauncey
'''

import sys

class Solution:
    # @param pages: a list of integers
    # @param k: an integer
    # @return: an integer
    def copyBooks(self, pages, k):
        if pages is None or len(pages) == 0 or k == 0: return 0
        n = len(pages)
        if k > n: k = n
        f = [[0] * n for i in xrange(k)]
        f[0][0] = pages[0]
        for j in xrange(1,n):
            f[0][j] = f[0][j-1] + pages[j]
        for i in xrange(1, k):
            f[i][i] = max(f[i-1][i-1], pages[i])
            left = i-1
            for j in xrange(i+1, n):
                minval = sys.maxsize
                while left < j:
                    nxt = max(f[i-1][left], f[0][j]-f[0][left])
                    if nxt < minval:
                        minval = nxt
                    else:
                        break
                    left += 1
                f[i][j] = minval
                if left > i: left -= 1
        return f[k-1][n-1]
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.copyBooks([3,2,4],2)
    
    
    