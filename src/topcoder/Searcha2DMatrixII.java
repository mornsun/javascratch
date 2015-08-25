package topcoder;

import java.util.*;

import topcoder.UniqueBinarySearchTreesII.TreeNode;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.

Hide Tags Divide and Conquer Binary Search
Hide Similar Problems (M) Search a 2D Matrix

 * @author Chauncey
 *
 */
public class Searcha2DMatrixII
{
    public boolean searchMatrix(int[][] matrix, int target) {
    	if (matrix == null || matrix.length==0 || matrix[0].length==0) return false;
    	if (matrix.length < matrix[0].length+32) {
	    	for (int row = 0; row < matrix.length; ++row) {
		        int lo = 0;
		        int hi = matrix[row].length-1;
		        while (lo <= hi) {
		        	int m = lo+((hi-lo)>>1);
		        	if (target == matrix[row][m]) {
		        		return true;
		        	} else if (target < matrix[row][m]) {
		        		hi = m - 1;
		        	} else {
		        		lo = m + 1;
		        	}
		        }
	    	}
    	} else {
	    	for (int column = 0; column < matrix[0].length; ++column) {
		        int lo = 0;
		        int hi = matrix.length-1;
		        while (lo <= hi) {
		        	int m = lo+((hi-lo)>>1);
		        	if (target == matrix[m][column]) {
		        		return true;
		        	} else if (target < matrix[m][column]) {
		        		hi = m - 1;
		        	} else {
		        		lo = m + 1;
		        	}
		        }
	    	}
    	}
        return false;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Searcha2DMatrixII solution = new Searcha2DMatrixII();
		int[][] matrix = new int[][]{
				  {1,   4,  7, 11, 15},
				  {2,   5,  8, 12, 19},
				  {3,   6,  9, 16, 22},
				  {10, 13, 14, 17, 24},
				  {18, 21, 23, 26, 30}
		};
		System.out.println(solution.searchMatrix(matrix, 20));
	}

}
