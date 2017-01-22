#!/usr/bin/env python
#coding=utf8

'''
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.
Subscribe to see which companies asked this question

Hide Tags Segment Tree Binary Indexed Tree
Hide Similar Problems (E) Range Sum Query - Immutable (H) Range Sum Query 2D - Mutable

@author: Chauncey
'''

import sys

class NumArray(object):

    def __init__(self, nums):
        """
        :type nums: List[int]
        """
        if nums is None or len(nums) == 0:
            return

        self.tree = [[0].extend(nums)]
        sum = 0
        for num in nums:
            sum += num
            self._leftsum.append(sum)
        

    def update(self, i, val):
        """
        :type i: int
        :type val: int
        :rtype: void
        """
        if self._leftsum is None or i > len(self._leftsum) - 2:
            return

        plus = val - self._leftsum[i+1] + self._leftsum[i]
        for i in xrange(i+1, len(self._leftsum)):
            self._leftsum[i] = self._leftsum[i] + plus
        

    def sumRange(self, i, j):
        """
        :type i: int
        :type j: int
        :rtype: int
        """
        if i > j or self._leftsum is None:
            return 0

        if i < 0:
            i = 0
        if j > len(self._leftsum) - 2:
            j = len(self._leftsum) - 2

        return self._leftsum[j+1] - self._leftsum[i]
        


# Your NumArray object will be instantiated and called as such:
# obj = NumArray(nums)
# obj.update(i,val)
# param_2 = obj.sumRange(i,j)
                    
if __name__ == '__main__':
    obj = NumArray([-2, 0, 3, -5, 2, -1])
    #1
    print obj.sumRange(0, 2)
    #-1
    print obj.sumRange(2, 5)
    #-3
    print obj.sumRange(0, 5)

    obj = NumArray([1, 3, 5])
    print obj.sumRange(0, 2)
    obj.update(1, 2)
    print obj.sumRange(0, 2)
