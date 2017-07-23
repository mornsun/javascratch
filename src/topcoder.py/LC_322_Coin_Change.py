#!/usr/bin/env python
#coding=utf8

'''
You are given coins of different denominations and a total amount of money amount.
Write a function to compute the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.

Related Topics 
Dynamic Programming

@author: Chauncey
beat 95.22%
'''

import heapq
import datetime
import time
import sys

class Solution(object):
    def coinChangeDP(self, coins, amount):
        """
        :type coins: List[int]
        :type amount: int
        :rtype: int
        """
        if coins is None or len(coins)==0 or min(coins)<=0 or amount<0:
          return -1
        if amount==0:
          return 0

        f = [0]

        for i in xrange(amount):
          fewest = sys.maxint
          for denom in coins:
            if i+1-denom >= 0 and f[i+1-denom]!=-1:
              fewest = min(fewest, f[i+1-denom]+1)
          if fewest == sys.maxint:
            fewest = -1
          f.append(fewest)

        return f[amount]
        
    #DFS
    def coinChange(self, coins, amount):
        """
        :type coins: List[int]
        :type amount: int
        :rtype: int
        """
        if coins is None or len(coins)==0 or min(coins)<=0 or amount<0:
          return -1
        if amount==0:
          return 0

        coins.sort(reverse=True)

        visited = [False] * (amount+1)
        parents = [0]
        children = []
        cnt = 0
        visited[0] = True
        while parents:
          cnt += 1
          for parent in parents:
            for denom in coins:
              child = parent + denom
              if child > amount or visited[child]:
                continue
              if child == amount:
                return cnt
              children.append(child)
              visited[child] = True
          parents, children = children, []

        return -1

        

if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.coinChange([1, 2, 5], 11) #3
    print solution.coinChange([2], 3) #-1
    print solution.coinChange([1,2,5], 100)
    
    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]