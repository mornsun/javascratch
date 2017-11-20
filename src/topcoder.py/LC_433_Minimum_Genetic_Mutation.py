#!/usr/bin/env python
#coding=utf8

'''
A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".

Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.

For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.

Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.

Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.

Note:

Starting point is assumed to be valid, so it might not be included in the bank.
If multiple mutations are needed, all mutations during in the sequence must be valid.
You may assume start and end string is not the same.
Example 1:

start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]

return: 1
Example 2:

start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

return: 2
Example 3:

start: "AAAAACCC"
end:   "AACCCCCC"
bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]

return: 3


@author: Chauncey
beat 91.23%
'''

import heapq
import datetime
import time
import sys

class Solution(object):
    def minMutation(self, start, end, bank):
        """
        :type start: str
        :type end: str
        :type bank: List[str]
        :rtype: int
        """
        if len(start)!=8 or len(end)!=8 or not bank:
            return -1

        bank_dict = {mut: False for mut in bank}
        step = 1
        curr = [start]
        while curr:
            temp = []
            for curr_start in curr:
                for i in xrange(8):
                    c = curr_start[i]
                    for to_c in ('A', 'C', 'G', 'T'):
                        if c == to_c:
                            continue
                        new_start = curr_start[0:i] + to_c + curr_start[i+1:]
                        #print new_start,
                        if new_start not in bank_dict:
                            continue
                        if bank_dict[new_start]:
                            continue
                        if new_start == end:
                            return step
                        temp.append(new_start)
                        bank_dict[new_start] = True
            step += 1
            #print temp
            curr = temp
        return -1



if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.minMutation("AACCGGTT", "AACCGGTA", ["AACCGGTA"]) #1
    print solution.minMutation("AACCGGTT", "AAACGGTA", ["AACCGGTA", "AACCGCTA", "AAACGGTA"]) #2
    print solution.minMutation("AAAAACCC", "AACCCCCC", ["AAAACCCC", "AAACCCCC", "AACCCCCC"]) #3

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()