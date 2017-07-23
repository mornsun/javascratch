#!/usr/bin/env python
#coding=utf8

'''
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words,
i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]

Related Topics 
Hash Table String Trie 

@author: Chauncey
beat 18.96%
'''

import heapq
import datetime
import time
import sys

class Solution(object):
    def palindromePairs(self, words):
        """
        :type words: List[str]
        :rtype: List[List[int]]
        """
        if words is None or len(words)==0:
            return []

        dic = {}
        for i in xrange(len(words)):
            dic[words[i]] = i

        ret = []
        for i in xrange(len(words)):
            word = words[i]
            for k in xrange(len(word)+1):
                prefix = word[0:k]
                suffix = word[k:]
                if self.isPalindrome(prefix):
                    rev = suffix[::-1]
                    index = dic.get(rev)
                    if index is not None and index!=i:
                        ret.append([index, i])
                if suffix and self.isPalindrome(suffix):
                    rev = prefix[::-1]
                    index = dic.get(rev)
                    if index is not None and index!=i:
                        ret.append([i, index])
        return ret
    
    def isPalindrome(self, word):
        n = len(word)
        if n==0 or n==1:
            return True
        for i in xrange(n>>1):
            if word[i] != word[n-1-i]:
                return False
        return True
        

if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.palindromePairs(["bat", "tab", "cat"]) #[[0, 1], [1, 0]]
    print solution.palindromePairs(["abcd", "dcba", "lls", "s", "sssll"]) #[[0, 1], [1, 0], [3, 2], [2, 4]]
    print solution.palindromePairs(["a",""]) #[[0,1],[1,0]]
    
    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]