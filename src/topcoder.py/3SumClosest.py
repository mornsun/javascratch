'''

@author: Chauncey
'''

import sys

class Solution:
    # @param {integer[]} nums
    # @param {integer} target
    # @return {integer}
    def threeSumClosest(self, nums, target):
        if nums is None or len(nums) < 3: return 0
        l = len(nums)
        nums.sort()
        min_gap = sys.maxint
        min_sum = 0
        for i in xrange(l-2):
            a = nums[i]
            j = i + 1
            k = l - 1
            #TODO: binary search the boundary
            while j < k:
                b = nums[j]
                c = nums[k]
                sum = a + b + c
                diff = sum - target
                gap = abs(diff)
                if gap < min_gap:
                    min_gap = gap
                    min_sum = sum
                    if gap is 0:
                        return target
                if diff > 0:
                    k -= 1
                else:
                    j += 1
        return min_sum
            
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.threeSumClosest([-1, 2, 1, -4], 1)
    
    
    