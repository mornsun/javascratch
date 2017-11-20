#!/usr/bin/env python
#coding=utf8

'''
We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

Example:

n = 10, I pick 8.

First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.

You end up paying $5 + $7 + $9 = $21.
Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.

Related Topics 
Dynamic Programming, Minimax 
Similar Questions 
Flip Game II, Guess Number Higher or Lower, Can I Win, Find K Closest Elements 

f(i,j) = min(x + max(f(i,x-1), f(x+1,j) ) )

@author: Chauncey
beat 24.02%
'''

import heapq
import datetime
import time
import sys
import collections

class Solution(object):
    def getMoneyAmount(self, n):
        """
        :type n: int
        :rtype: int
        """
        if not n or n==1:
            return 0
        f = [[0]*n for _ in xrange(n)]
        for i in xrange(n):
            f[i][i] = i + 1 

        for k in xrange(1, n-1):
            for i in xrange(n-k):
                mini = min(f[i][i] + f[i+1][i+k], f[i][i+k-1] + f[i+k][i+k])
                for x in xrange(i+1, i+k):
                    mini = min(mini, f[x][x] + max(f[i][x-1], f[x+1][i+k]) )
                f[i][i+k] = mini
                print i,i+k,'=',mini

        mini = min(f[1][n-1], f[0][n-2])
        for x in xrange(1, n-1):
            mini = min(mini, max(f[0][x-1], f[x+1][n-1]) )
        f[0][n-1] = mini

        return f[0][n-1]


if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    #print solution.getMoneyAmount(10) #
    print solution.getMoneyAmount(1) #0
    print solution.getMoneyAmount(2) #1
    print solution.getMoneyAmount(3) #2

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()