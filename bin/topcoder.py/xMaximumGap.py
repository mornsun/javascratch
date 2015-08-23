'''
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.

Credits:
Special thanks to @porker2008 for adding this problem and creating all test cases.

Hide Tags Sort

鸽巢原理(Pigeonhole principle),又名屉原理(box (or drawer) principle)

@author: Chauncey
'''

class Solution:
    # @param {integer[]} nums
    # @return {integer}
    def maximumGap(self, nums):
        if nums is None or len(nums) < 2: return 0
        n = len(nums)
        max_num = nums[0]
        min_num = nums[0]
        for num in nums[1:]:
            if num > max_num: max_num = num
            if num < min_num: min_num = num
        if max_num == min_num: return 0
        np = n+1;
        wid = max_num - min_num
        bkts = [None for i in range(n+1)]
        for num in nums:
            idx = int((num - min_num)*np/wid)
            if idx > n: idx = n
            if bkts[idx] is None:
                bkts[idx] = [num, num]
            else:
                if num < bkts[idx][0] : bkts[idx][0] = num
                elif num > bkts[idx][1] : bkts[idx][1] = num
        max_gap = 0
        last = None
        for bkt in bkts:
            if bkt is not None:
                if last is None:
                    last = bkt[1]
                else:
                    gap = bkt[0] - last
                    if gap > max_gap: max_gap = gap
                    last = bkt[1]
        return max_gap
            
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.maximumGap([1,10000000])
    
    
    