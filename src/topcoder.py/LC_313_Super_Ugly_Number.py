#!/usr/bin/env python
#coding=utf8

'''
Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

Note:
(1) 1 is a super ugly number for any given primes.
(2) The given numbers in primes are in ascending order.
(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
(4) The nth super ugly number is guaranteed to fit in a 32-bit signed integer.

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.

Subscribe to see which companies asked this question

Hide Tags Math Heap
Hide Similar Problems (M) Ugly Number II


@author: Chauncey
'''

import heapq
import datetime
import time

class Solution(object):
    def nthSuperUglyNumber1(self, n, primes):
        """
        :type n: int
        :type primes: List[int]
        :rtype: int
        """
        if primes is None:
            return 
        items = set()
        heap = [1]
        items.add(1)

        k = len(primes)
        #nexts = [0 for _ in xrange(k)];
        num = 1;
        for _ in xrange(n):
            num = heapq.heappop(heap)
            items.remove(num);
            for prime in primes:
                next_num = num * prime
                if not next_num in items:
                    heapq.heappush(heap, next_num)
                    items.add(next_num)

        return num


    def nthSuperUglyNumber(self, n, primes):
        """
        :type n: int
        :type primes: List[int]
        :rtype: int
        """
        ugly = [1] * n
        i_list = [-1] * len(primes)
        v_list = [1] * len(primes)
        k = 0
        while k < n:
            x = min(v_list)
            ugly[k] = x
            for v in xrange(len(v_list)):
                if x == v_list[v]:
                    i_list[v] += 1
                    v_list[v] = ugly[i_list[v]] * primes[v]
            k += 1
        return ugly[k-1]
        

        
if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.nthSuperUglyNumber1(5, [2, 7, 13, 19]) #8
    print solution.nthSuperUglyNumber1(100000, [7,19,29,37,41,47,53,59,61,79,83,89,101,103,109,127,131,137,139,157,167,179,181,199,211,229,233,239,241,251])
    #time.sleep(1.0)

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]