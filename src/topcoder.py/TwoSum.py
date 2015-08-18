'''

@author: Chauncey
'''
class Solution:
    def quick_sort(items):
        """ Implementation of quick sort """
        if len(items) > 1:
            pivot_index = len(items) / 2
            smaller_items = []
            larger_items = []
    
            for i, val in enumerate(items):
                if i != pivot_index:
                    if val < items[pivot_index]:
                        smaller_items.append(val)
                    else:
                        larger_items.append(val)
    
            quick_sort(smaller_items)
            quick_sort(larger_items)
            items[:] = smaller_items + [items[pivot_index]] + larger_items

    # @param {integer[]} nums
    # @param {integer} target
    # @return {integer[]}
    def twoSum(self, nums, target):
        if nums is None or len(nums)<2: return None
        arr = [(v,i+1) for i,v in enumerate(nums)]
        arr.sort(reverse=False)
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