'''

@author: Chauncey
'''
import sys
import collections

class Solution:
    # @param {integer[]} nums
    # @param {integer} k
    # @return {integer[]}
    def maxSlidingWindow(self, nums, k):
        res = []
        l = len(nums)
        if l == 0: return res
        dq = collections.deque()
        for i in xrange(l):
            while dq and nums[dq[-1]] <= nums[i]:
                dq.pop()
            dq.append(i)
            if i >= k - 1:
                res.append(nums[dq[0]])
            if dq[0] == i - k + 1:
                dq.popleft()
        return res
         
            
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.maxSlidingWindow([1,3,-1,-3,5,3,6,7], 3)
    
    
    