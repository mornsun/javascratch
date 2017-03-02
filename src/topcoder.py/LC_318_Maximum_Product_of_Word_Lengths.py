#!/usr/bin/env python
#coding=utf8

'''
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters.
You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.

Subscribe to see which companies asked this question.

Hide Tags Bit Manipulation


@author: Chauncey
'''

import heapq
import datetime
import time

class Solution(object):
    def maxProduct1(self, words):
        """
        :type words: List[str]
        :rtype: int
        """
        if words is None or len(words) < 2:
            return 0

        word_sig = []

        for word in words:
            sig = 0
            for ch in word:
                shift = ord(ch) - ord('a')
                sig |= 1 << shift
            word_sig.append(sig)

        maxproduct = 0
        l = len(word_sig)
        for i in xrange(0, l):
            for j in xrange(i+1, l):
                if word_sig[i] & word_sig[j] == 0:
                    maxproduct = max(maxproduct, len(words[i]) * len(words[j]))

        return maxproduct


    def maxProduct(self, words):
        maskLen = {reduce(lambda x, y: x | y, [1 << (ord(c) - 97) for c in word], 0): len(word)  for word in sorted(words, key = lambda x: len(x))}.items()
        return max([x[1] * y[1] for i, x in enumerate(maskLen) for y in maskLen[:i] if not (x[0] & y[0])] or [0])


    def maxProduct2(self, words):
        d = {sum(1 << (ord(c) - 97) for c in set(w)): len(w) for w in sorted(words, key=len)}
        return max([d[k] * d[K] for k, K in itertools.combinations(d.keys(), 2) if not K & k] or [0])


if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.maxProduct(["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]) #16
    print solution.maxProduct(["a", "ab", "abc", "d", "cd", "bcd", "abcd"]) #4
    print solution.maxProduct(["a", "aa", "aaa", "aaaa"]) #0
    #time.sleep(1.0)

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]