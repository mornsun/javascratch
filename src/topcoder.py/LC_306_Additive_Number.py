#!/usr/bin/env python
#coding=utf8

'''
Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

For example:
"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
1 + 99 = 100, 99 + 100 = 199
Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Follow up:
How would you handle overflow for very large input integers?

@author: Chauncey
'''

class Solution(object):
    def isAdditiveNumber(self, num):
        """
        :type num: str
        :rtype: bool
        """
        if len(num) < 3:
        	return False

        num_list = []
        for ch in num:
            num_list.append(int(ch)-int('0'))

        return self.isAdditiveList(num_list, 0, len(num_list))


    def _toNumList(self, num):
        num_list = []
        while num > 0:
            num_list.append(num%10)
            num /= 10
        num_list.reverse()

        return num_list


    def _calNumber(self, num_list, start, end):
        if num_list[start] == 0 and end > start+1:
            return None

        res = num_list[start]
        for i in xrange(start+1, end):
            res *= 10
            res += num_list[i]

        return res


    def _validNumber(self, num_list, start, end):
        if num_list[start] == 0 and end > start+1:
            return False
        return True

    def _largeNumberPlus(self, num_list, s1, e1, s2, e2):
        offset1 = e1-1
        offset2 = e2-1
        res_list = []
        carrybit = 0
        while offset1 >= s1 or offset2 >= s2:
            left = num_list[offset1] if offset1 >= s1 else 0
            right = num_list[offset2] if offset2 >= s2 else 0
            total = left+right+carrybit
            carrybit = 1 if total>=10 else 0
            if total >= 10:
                total -= 10
            res_list.append(total)
            offset1-=1
            offset2-=1

        if carrybit == 1:
            res_list.append(1)
        res_list.reverse()

        return res_list


    def isAdditiveList(self, num_list, start, end):
        for i in xrange(start+1, end-1):
            if not self._validNumber(num_list, start, i):
                break
            for j in xrange(i+1, end):
                if not self._validNumber(num_list, i, j):
                    break
                l = self._largeNumberPlus(num_list, start, i, i, j)
                print '+', l
                #l = self._toNumList(total)
                match = True
                for k in xrange(0, len(l)):
                    if j+k >= len(num_list) or l[k] != num_list[j+k]:
                        match = False
                        break
                if match and self.isAdditiveListRec(num_list, i, j, j, j+len(l)):
                    return True
        return False


    def isAdditiveListRec(self, num_list, s1, e1, s2, e2):
        if e2 == len(num_list):
            return True

        l = self._largeNumberPlus(num_list, s1, e1, s2, e2)
        print '++', l
        match = True
        for k in xrange(0, len(l)):
            if e2+k >= len(num_list) or l[k] != num_list[e2+k]:
                match = False
                break
        if match and self.isAdditiveListRec(num_list, s2, e2, e2, e2+len(l)):
            return True
        return False

        
if __name__ == '__main__':
    solution = Solution();
    print solution.isAdditiveNumber('112358') #True
    print
    print solution.isAdditiveNumber('199100199') #True
    print
    print solution.isAdditiveNumber('112') #True
    print
    print solution.isAdditiveNumber('1') #False
    print
    print solution.isAdditiveNumber('0') #False
    print
    print solution.isAdditiveNumber('1023') #False
    print
    print solution.isAdditiveNumber('101') #True
    print
    print solution.isAdditiveNumber('0235813') #False