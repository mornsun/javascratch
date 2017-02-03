#!/usr/bin/env python
#coding=utf8

'''
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

prices = [1, 2, 3, 0, 2]
maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell]
Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.

Subscribe to see which companies asked this question

Hide Tags Dynamic Programming
Hide Similar Problems (E) Best Time to Buy and Sell Stock (E) Best Time to Buy and Sell Stock II

@author: Chauncey
'''
class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        l = len(prices)
        if prices is None or l < 2:
            return 0

        dp = [[0 for j in xrange(3)] for i in xrange(l)]
        #0 - buy, 1 - sell, 2 - cooldown

        for j in xrange(3): dp[0][j] = 0
        #print '--', max(dp[0][0], dp[0][1], dp[0][2])
        for i in xrange(1, l):
            dp[i][0] = max(dp[i-1][0] + prices[i] - prices[i-1], dp[i-1][2])
            dp[i][1] = dp[i-1][0] + prices[i] - prices[i-1]
            dp[i][2] = max(dp[i-1][1], dp[i-1][2])
            #print '--', max(dp[i][0], dp[i][1], dp[i][2])
        return max(dp[l-1][0], dp[l-1][1], dp[l-1][2])
        

        
if __name__ == '__main__':
    solution = Solution()
    print solution.maxProfit([1, 2, 3, 0, 2]) #3
    #transactions = [buy, sell, cooldown, buy, sell]