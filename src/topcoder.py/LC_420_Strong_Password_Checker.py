#!/usr/bin/env python
#coding=utf8

'''
A password is considered strong if below conditions are all met:

It has at least 6 characters and at most 20 characters.
It must contain at least one lowercase letter, at least one uppercase letter, and at least one digit.
It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
Write a function strongPasswordChecker(s), that takes a string s as input, and return the MINIMUM change required to make s a strong password. If s is already strong, return 0.

Insertion, deletion or replace of any one character are all considered as one change.


@author: Chauncey
beat 58.93%
'''

import heapq
import datetime
import time
import sys

class Solution(object):
    def strongPasswordChecker(self, s):
        """
        :type s: str
        :rtype: int
        """
        if not s:
            return 6
        n = len(s)
        insertion = max(6-n, 0)
        deletion = max(n-20, 0)
        has_lower = False
        has_upper = False
        has_digit = False
        triple = 0
        prev_c = None
        cnt_same = 0
        same_list = []
        for c in s:
            if c.isupper():
                has_upper = True
            elif c.islower():
                has_lower = True
            elif c.isdigit():
                has_digit = True

            if c == prev_c:
                cnt_same += 1
            else:
                prev_c = c
                if cnt_same >= 3:
                    same_list.append(cnt_same-2)
                cnt_same = 1

        if cnt_same >= 3:
            same_list.append(cnt_same-2)

        lack = (0 if has_lower else 1) + (0 if has_upper else 1) + (0 if has_digit else 1)

        if not same_list:
            if insertion > 0:
                return max(insertion, lack)
            elif deletion > 0:
                return deletion + lack
            return lack

        if insertion > 0:
            if same_list[0] == 5:
                return max(2, lack)
            return max(insertion, lack)

        #print same_list, lack, deletion
        ret = 0
        if deletion > 0:
            del_list = [[] for _ in xrange(3)]
            for i in xrange(len(same_list)):
                best = (same_list[i])%3
                if best > 0:
                    del_list[best].append(i)
            for i in del_list[1]:
                if deletion == 0:
                    break
                ret += 1
                same_list[i] -= 1
                deletion -= 1
            if deletion > 0:
                for i in del_list[2]:
                    if deletion == 0:
                        break
                    if deletion == 1:
                        ret += 1
                        same_list[i] -= 1
                        deletion = 0
                        break
                    ret += 2
                    same_list[i] -= 2
                    deletion -= 2
            if deletion > 0:
                for i in xrange(len(same_list)):
                    if deletion <= same_list[i]:
                        same_list[i] -= deletion
                        ret += deletion
                        deletion = 0
                        break
                    ret += same_list[i]
                    deletion -= same_list[i]
                    same_list[i] = 0
            if deletion > 0:
                return ret + deletion + lack

        #print same_list, lack, deletion
        replacement = 0
        for same in same_list:
            replacement += (same+2) / 3
        return ret + max(replacement, lack)


if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    print solution.strongPasswordChecker(".....") #3
    print solution.strongPasswordChecker("aaa111") #2
    print solution.strongPasswordChecker("1234567890123456Baaaa") #2

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]