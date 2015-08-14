'''

@author: Chauncey
'''

import sys

class Solution:
    # @param {integer[]} nums
    # @param {integer} target
    # @return {integer[][]}
    def fourSum(self, nums, target):
        res = []
        if nums is None or len(nums) < 4: return res
        l = len(nums)
        nums.sort()
        dict = {}
        for h in xrange(l-1):
            a = nums[h]
            if h>0 and a==nums[h-1]:
                continue
            for i in xrange(h+1,l):
                b = nums[i]
                if i>h+1 and b==nums[i-1]:
                    continue
                sum = a + b
                ls = dict.get(sum)
                if ls is None:
                    dict[sum] = [[a, b]]
                else:
                    ls.append([a, b])
                
        for h in xrange(l-3):
            a = nums[h]
            if h>0 and a==nums[h-1]:
                continue
            for i in xrange(h+1, l-2):
                b = nums[i]
                if i>h+1 and b==nums[i-1]:
                    continue
                diff = target - a - b
                ls = dict.get(diff)
                if ls is None:
                    continue
                else:
                    for pair in ls:
                        if pair[0] < nums[i+1] or pair[1] < nums[i+2]:
                            continue
                        else:
                            res.append([a,b,pair[0],pair[1]])
        return res
    
    def fourSum1(self, nums, target):
        res = []
        if nums is None or len(nums) < 4: return res
        l = len(nums)
        nums.sort()
        for h in xrange(l-3):
            a = nums[h]
            if h>0 and a==nums[h-1]:
                continue
            for i in xrange(h+1, l-2):
                b = nums[i]
                if i>h+1 and b==nums[i-1]:
                    continue
                ab = a + b
                j = i + 1
                k = l - 1
                #TODO: binary search the boundary
                while j < k:
                    c = nums[j]
                    if j>i+1 and c==nums[j-1]:
                        j += 1
                        continue
                    d = nums[k]
                    if k<l-1 and d==nums[k+1]:
                        k -= 1
                        continue
                    diff = ab + c + d - target
                    if diff > 0:
                        k -= 1
                    elif diff < 0:
                        j += 1
                    else:
                        res.append([a,b,c,d])
                        j += 1
                        k -= 1
        return res
            
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.fourSum([1,0,-1,0,-2,2], 0)
    
    
    