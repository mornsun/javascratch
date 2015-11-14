package topcoder;

import java.util.*;

/**
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
Subscribe to see which companies asked this question

Hide Tags Dynamic Programming
Hide Similar Problems (E) Range Sum Query - Immutable

 * @author Chauncey
 *
 */
public class SmallestRectangleEnclosingBlackPixels
{
	private int[][] _topLeftSum = null;

    public SmallestRectangleEnclosingBlackPixels(int[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) return;
        _topLeftSum = new int[matrix.length+1][matrix[0].length+1];
        for (int i=0; i<matrix.length; ++i) {
        	int sum = 0;
            for (int j=0; j<matrix[0].length; ++j) {
            	sum += matrix[i][j];
            	_topLeftSum[i+1][j+1] = _topLeftSum[i][j+1] + sum;
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (_topLeftSum == null || row1 > row2 || col1 > col2) return 0;
        if (row1 < 0) row1 = 0;
        if (col1 < 0) col1 = 0;
        if (row2 > _topLeftSum.length-2) row2 = _topLeftSum.length-2;
        if (col2 > _topLeftSum[0].length-2) col2 = _topLeftSum[0].length-2;
        return _topLeftSum[row2+1][col2+1] + _topLeftSum[row1][col1] - _topLeftSum[row1][col2+1] - _topLeftSum[row2+1][col1];
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[][] matrix = new int[][] {
		          {3, 0, 1, 4, 2},
		          {5, 6, 3, 2, 1},
		          {1, 2, 0, 1, 5},
		          {4, 1, 0, 1, 7},
		          {1, 0, 3, 0, 5}};
		SmallestRectangleEnclosingBlackPixels solution = new SmallestRectangleEnclosingBlackPixels(matrix);
		//8
		System.out.println(solution.sumRegion(2, 1, 4, 3));
		//11
		System.out.println(solution.sumRegion(1, 1, 2, 2));
		//12
		System.out.println(solution.sumRegion(1, 2, 2, 4));
		matrix = new int[][]{{1},{-7}};
		solution = new SmallestRectangleEnclosingBlackPixels(matrix);
		System.out.println(solution.sumRegion(0,0,0,0));
		System.out.println(solution.sumRegion(1,0,1,0));
		System.out.println(solution.sumRegion(0,0,1,0));
	}

}
