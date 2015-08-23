'''
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2

Hide Tags Array Hash Table
Hide Similar Problems (M) 3Sum (M) 4Sum (M) Two Sum II - Input array is sorted (E) Two Sum III - Data structure design

@author: Chauncey
'''

def findKth(nums, k, lo=0, hi=-1):
    """ Implementation of quick sort """
    if hi == -1: hi = len(nums)-1
    v = nums[hi];
    i = lo-1;
    for j in xrange(lo, hi):
        if nums[j] >= v:
            i += 1;
            nums[i], nums[j] = nums[j], nums[i];
    i += 1
    nums[i], nums[hi] = nums[hi], nums[i];
    if k == i+1:
        return v;
    elif k < i+1:
        return findKth(nums, k, lo, i-1);
    else:
        return findKth(nums, k, i+1, hi);
    
def quick_sort(nums, lo=0, hi=-1):
    """ Implementation of quick sort """
    if hi == -1: hi = len(nums)-1
    v = nums[hi];
    i = lo-1;
    for j in xrange(lo, hi):
        if nums[j] >= v:
            i += 1;
            nums[i], nums[j] = nums[j], nums[i];
    i += 1
    nums[i], nums[hi] = nums[hi], nums[i];
    if lo < i-1: quick_sort(nums, lo, i-1);
    if i+1 < hi: quick_sort(nums, i+1, hi);
            
class Solution:

    # @param {integer[]} nums
    # @param {integer} target
    # @return {integer[]}
    def twoSum(self, nums, target):
        if nums is None or len(nums)<2: return None
        arr = [(v,i+1) for i,v in enumerate(nums)]
        #arr.sort(reverse=False)
        #print quick_sort(nums)
        #print nums
        x = 0
        y = len(arr) - 1
        while x < y:
            nx = arr[x][0]
            ny = arr[y][0]
            if nx + ny < target:
                x += 1
            elif nx + ny > target:
                y -= 1
            else:
                return [arr[x][1], arr[y][1]] if arr[x][1] < arr[y][1] else [arr[y][1], arr[x][1]]
        print None
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.twoSum([2, 11, 7, 15], 9)