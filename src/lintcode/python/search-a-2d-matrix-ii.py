'''
Write an efficient algorithm that searches for a value in an m x n matrix, return the occurrence of it.

This matrix has the following properties:

    * Integers in each row are sorted from left to right.

    * Integers in each column are sorted from up to bottom.

    * No duplicate integers in each row or column.

Have you met this question in a real interview? Yes
Example
Consider the following matrix:

[
    [1, 3, 5, 7],
    [2, 4, 7, 8],
    [3, 5, 9, 10]
]

Given target = 3, return 2.

Challenge
O(m+n) time and O(1) extra space

Tags Expand 
Matrix


Related Problems Expand 
Easy Search a 2D Matrix 27 %

@author: Chauncey
'''

import sys

class Solution:
    """
    @param matrix: An list of lists of integers
    @param target: An integer you want to search in matrix
    @return: An integer indicates the total occurrence of target in the given matrix
    """
    def searchMatrix(self, matrix, target):
        if matrix is None or len(matrix)==0 or len(matrix[0])==0: return 0
        cnt = 0
        y = len(matrix[0]) - 1
        for x in range(len(matrix)):
            while y and matrix[x][y] > target:
                y -= 1
            if matrix[x][y] == target:
                cnt += 1
        return cnt
    
    def searchMatrix1(self, matrix, target):
        if matrix is None or len(matrix)==0 or len(matrix[0])==0: return 0
        def binSearch(nums, low, high):
            while low <= high:
                mid = (low + high) / 2
                if nums[mid] > target:
                    high = mid - 1
                else:
                    low = mid + 1
            return high
        y = len(matrix[0]) - 1
        cnt = 0
        for x in range(len(matrix)):
            y = binSearch(matrix[x], 0, y)
            while y>=0 and matrix[x][y] == target:
                cnt += 1
                y -= 1
        return cnt

if __name__ == '__main__':
    solution = Solution();
    # 0
    print solution.searchMatrix([], 1)
    # 2
    print solution.searchMatrix([[1, 3, 5, 7], [2, 4, 7, 8], [3, 5, 9, 10]], 3)
    
    
    