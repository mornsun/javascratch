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

Beat 98.91%
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

        self.tree = [nums]
        cur_nodes = nums
        while len(cur_nodes) > 1:
            next_nodes = []
            for i in xrange(0, len(cur_nodes), 2):
                left = cur_nodes[i]
                right = 0 if i+1 >= len(cur_nodes) else cur_nodes[i+1]
                next_nodes.append(left + right)
            cur_nodes = next_nodes
            self.tree.append(cur_nodes)
        #print self.tree
        

    def update(self, i, val):
        """
        :type i: int
        :type val: int
        :rtype: void
        """
        if self.tree is None or i >= len(self.tree[0]):
            return

        plus = val - self.tree[0][i]
        self.tree[0][i] = val
        for h in xrange(1, len(self.tree)):
            i >>= 1
            self.tree[h][i] += plus


    def _sumBefore(self, i):
        sum = 0
        for h in xrange(0, len(self.tree)):
            if i & 1 == 1:
                sum += self.tree[h][i-1]
            i >>= 1
        return sum
        

    def sumRange(self, i, j):
        """
        :type i: int
        :type j: int
        :rtype: int
        """
        if i > j or self.tree is None:
            return 0

        if i < 0:
            i = 0
        if j >= len(self.tree[0]):
            j = len(self.tree[0]) - 1

        #print j+1, self._sumBefore(j+1), '-', i, self._sumBefore(i)
        return self._sumBefore(j+1) - self._sumBefore(i)
        


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
    print

    obj = NumArray([1, 3, 5])
    print obj.sumRange(0, 2) #9
    obj.update(1, 2)
    print obj.sumRange(0, 2) #8
    print

    obj = NumArray([-1])
    print obj.sumRange(0, 0) #-1
    obj.update(0, 1)
    print obj.sumRange(0, 0) #1