package topcoder;

import java.util.*;

import topcoder.UniqueBinarySearchTreesII.TreeNode;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.

Hide Tags Array Binary Search
Hide Similar Problems (M) Search a 2D Matrix II

 * @author Chauncey
 *
 */
public class Searcha2DMatrix
{

	/**
	 * binary search from top-left to bottom-right will quicker than this approach
	 * @param matrix
	 * @param target
	 * @return
	 */
    public boolean searchMatrix(int[][] matrix, int target) {
    	if (matrix == null || matrix.length==0 || matrix[0].length==0) return false;
    	int lo = 0;
        int hi = matrix.length-1;
        while (lo < hi) {
        	int m = lo+((hi-lo+1)>>1);
        	if (target == matrix[m][0]) {
        		return true;
        	} else if (target < matrix[m][0]) {
        		hi = m - 1;
        	} else {
        		lo = m;
        	}
        }
        int row = lo;
        lo = 0;
        hi = matrix[row].length-1;
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
        return false;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Searcha2DMatrix solution = new Searcha2DMatrix();
		int[][] matrix = new int[][]{
				  {1,   3,  5,  7},
				  {10, 11, 16, 20},
				  {23, 30, 34, 50}
		};
		System.out.println(solution.searchMatrix(matrix, 50));
	}

}
