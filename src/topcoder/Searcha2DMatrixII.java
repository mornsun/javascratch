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
		if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
			return false;
		}
		int col = matrix[0].length-1;
		int row = 0;
		while(col >= 0 && row <= matrix.length-1) {
			if(target == matrix[row][col]) {
				return true;
			} else if(target < matrix[row][col]) {
				col--;
			} else if(target > matrix[row][col]) {
				row++;
			}
		}
		return false;
	}
	/**
	 * O(n ^ 1.58)解法：
参考：https://leetcode.com/discuss/47528/c-with-o-m-n-complexity

分治法，以矩形中点为基准，将矩阵拆分成左上，左下，右上，右下四个区域

若中点值 < 目标值，则舍弃左上区域，从其余三个区域再行查找

若中点值 > 目标值，则舍弃右下区域，从其余三个区域再行查找

时间复杂度递推式：T(n) = 3T(n/2) + c

相关博文：http://articles.leetcode.com/2010/10/searching-2d-sorted-matrix-part-ii.html
http://bookshadow.com/weblog/2015/07/23/leetcode-search-2d-matrix-ii/

	 * @param matrix
	 * @param target
	 * @return
	 */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int n=matrix.length, m=matrix[0].length;
        return helper(matrix,0,n-1,0,m-1,target);
    }
    boolean helper(int[][] matrix, int rowStart, int rowEnd, int colStart, int colEnd, int target ){
        if(rowStart>rowEnd||colStart>colEnd){
            return false;
        }
        int rm=(rowStart+rowEnd)/2, cm=(colStart+colEnd)/2;
        if(matrix[rm][cm]== target){
            return true;
        }
        else if(matrix[rm][cm] >target){
            return helper(matrix, rowStart, rm-1,colStart, cm-1,target)||
                helper(matrix,  rm, rowEnd, colStart,cm-1,target) ||
                helper(matrix, rowStart, rm-1,cm, colEnd,target);
        }
        else{
            return helper(matrix, rm+1, rowEnd, cm+1,colEnd,target)||
                helper(matrix,  rm+1, rowEnd, colStart,cm,target) ||
                helper(matrix, rowStart, rm,cm+1, colEnd,target);
        }
    }
    
    public boolean searchMatrix1(int[][] matrix, int target) {
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
		System.out.println(solution.searchMatrix(matrix, 14));
	}

}
