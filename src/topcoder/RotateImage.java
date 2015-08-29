package topcoder;

import java.util.*;

/**
 * You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?

Show Tags

 * @author Chauncey
 *
 */
public class RotateImage
{
    public void rotate(int[][] matrix) {
    	if (matrix==null || matrix.length==0 || matrix[0].length!=matrix.length) return;
    	for (int i=0; i<matrix.length; ++i) {
    		for (int j=0; j<i; ++j) {
    			int tmp = matrix[i][j];
    			matrix[i][j] = matrix[j][i];
    			matrix[j][i] = tmp;
    		}
    	}
    	for (int i=0; i<matrix.length; ++i) {
    		for (int j=0; j<matrix.length/2; ++j) {
    			int tmp = matrix[i][j];
    			matrix[i][j] = matrix[i][matrix.length-1-j];
    			matrix[i][matrix.length-1-j] = tmp;
    		}
    	}
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		RotateImage solution = new RotateImage();
		int[][] matrix = new int[][]{
				{1,2,3},
				{4,5,6},
				{7,8,9}
		};

		//matrix = new int[][]{{1}};
		solution.rotate(matrix);
		for (int i=0; i<matrix.length; ++i) {
			for (int j=0; j<matrix.length; ++j) {
				System.out.print(matrix[i][j]+",");
			}
			System.out.println();
		}
	}

}
