'''
On one line there are n houses. Give you an array of integer means the the position of each house. Now you need to pick k position to build k post office, so that the sum distance of each house to the nearest post office is the smallest. Return the least possible sum of all distances between each village and its nearest post office.

Have you met this question in a real interview? Yes
Example
Given array a = [1,2,3,4,5], k = 2. return 3.

Challenge
Could you solve this problem in O(n^2) time ?

@author: Chauncey
'''

import sys

class Solution:
    # @param {int[]} A an integer array
    # @param {int} k an integer
    # @return {int} an integer
    def postOffice(self, A, k):
        if A is None or len(A) == 0 or k == 0: return 0
        n = len(A)
        if k > n: k = n
        A.sort()
        sumto = [0] * (n+1)
        sumto[0] = 0
        for i in xrange(1, n+1):
            sumto[i] = sumto[i-1] + A[i-1]
            
        between = [[0] * n for i in xrange(n)]
        for i in xrange(n-1):
            for j in xrange(i+1, n):
                office = i + (j - i >> 1)
                between[i][j] = A[office]*(office-i+1)-(sumto[office+1]-sumto[i]) + (sumto[j+1]-sumto[office+1])-A[office]*(j-office)
        
        f = [[0] * n for i in xrange(k)]
        for j in xrange(n):
            f[0][j] = between[0][j]
        for i in xrange(1,k):
            f[i][i] = 0;
            left = i
            for j in xrange(i+1,n):
                minval = sys.maxsize
                for s in xrange(left,j):
                    nxt = f[i-1][s-1] + between[s][j]
                    if nxt < minval:
                        minval = nxt
                        left = s
                f[i][j] = minval
        return f[k-1][n-1]
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.postOffice([1,2,3,4,5],2)
    print solution.postOffice([112,122,360,311,85,225,405,53,405,43,342,13,588,424,299,37,104,289,404,414], 3)
    
    
    