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


    def isAdditiveList(self, num_list, start, end):
        for i in xrange(start+1, end-1):
            left = self._calNumber(num_list, start, i)
            if left is None:
                break
            for j in xrange(i+1, end):
                right = self._calNumber(num_list, i, j)
                if right is None:
                    break
                total = left + right
                #print '+', total
                l = self._toNumList(total)
                match = True
                for k in xrange(0, len(l)):
                    if j+k >= len(num_list) or l[k] != num_list[j+k]:
                        match = False
                        break
                if match and self.isAdditiveListRec(num_list, right, total, j, j+len(l)):
                    return True
        return False


    def isAdditiveListRec(self, num_list, left, right, i, j):
        if j == len(num_list):
            return True

        total = left + right
        #print '++', total
        l = self._toNumList(total)
        match = True
        for k in xrange(0, len(l)):
            if j+k >= len(num_list) or l[k] != num_list[j+k]:
                match = False
                break
        if match and self.isAdditiveListRec(num_list, right, total, j, j+len(l)):
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