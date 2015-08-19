/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
Hide Tags Array
Hide Similar Problems (M) Spiral Matrix

 * @author Chauncey
 *
 */
public class SpiralMatrixII
{
    public static int[][] generateMatrix(int n) {
    	int[][] matrix = new int[n][n];
    	if (n == 0) return matrix;
    	int beginX = 0, endX = n-1; //internal margin
    	int beginY = 0, endY = n-1;
    	int cnt = 1;
    	while (true) {
	    	for (int i = beginX; i<=endX; ++i) {
	    		matrix[beginY][i] = cnt++;
	    	}
	    	if (++beginY > endY) break;
	    	for (int i = beginY; i<=endY; ++i) {
	    		matrix[i][endX] = cnt++;
	    	}
	    	if (--endX < beginX) break;
	    	for (int i = endX; i>=beginX; --i) {
	    		matrix[endY][i] = cnt++;
	    	}
	    	if (--endY < beginY) break;
	    	for (int i = endY; i>=beginY; --i) {
	    		matrix[i][beginX] = cnt++;
	    	}
	    	if (++beginX > endX) break;
    	}
    	return matrix;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[][] matrix = generateMatrix(3);
		for (int i=0; i<matrix.length; ++i) {
			for (int j=0; j<matrix[i].length; ++j) {
				System.out.print(matrix[i][j]+",");
			}
			System.out.println();
		}
	}

}
