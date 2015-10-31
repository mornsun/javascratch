'''
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Subscribe to see which companies asked this question

Hide Tags Array Two Pointers Binary Search
Hide Similar Problems (H) First Missing Positive (M) Single Number (M) Linked List Cycle II (M) Missing Number

@author: Chauncey
'''

import sys

class Solution:
    # @param {integer[]} nums
    # @param {integer} target
    # @return {integer}
    def findDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if nums is None or len(nums) == 0: return 0
        l = len(nums)
        lo = 1
        hi = l-1
        while lo < hi:
            m = lo + (hi-lo>>1)
            cnt = 0
            for num in nums:
                if num <= m:
                    cnt += 1
            if cnt > m:
                hi = m
            else:
                lo = m + 1
        return lo
            
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.findDuplicate([1,2,5,4,3,5,6])
    
    
    