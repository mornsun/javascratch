package topcoder;

import java.util.*;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?

Hide Tags Array

 * @author Chauncey
 *
 */
public class SetMatrixZeroes
{
    public void setZeroes(int[][] matrix) {
    	if (matrix==null || matrix.length==0 || matrix[0].length==0) return;
    	boolean zero_row = false;
    	boolean zero_column = false;
    	for (int j=0; j<matrix[0].length; ++j) { //0 exists in the 1st row
    		if (matrix[0][j] == 0) {
    			zero_row = true;
    			break;
    		}
    	}
    	for (int i=0; i<matrix.length; ++i) { //0 exists in the 1st column
    		if (matrix[i][0] == 0) {
    			zero_column = true;
    			break;
    		}
    	}
    	for (int i=1; i<matrix.length; ++i) {
    		for (int j=1; j<matrix[0].length; ++j) {
    			if (matrix[i][j] == 0) {
    				matrix[i][0] = 0;
    				matrix[0][j] = 0;
    			}
    		}
    	}
    	for (int i=1; i<matrix.length; ++i) {
    		if (matrix[i][0] == 0)
	    		for (int j=1; j<matrix[0].length; ++j) {
	    			matrix[i][j] = 0;
	    		}
    	}
    	for (int j=1; j<matrix[0].length; ++j) {
    		if (matrix[0][j] == 0)
	    		for (int i=1; i<matrix.length; ++i) {
	    			matrix[i][j] = 0;
	    		}
    	}
    	if (zero_row) {
    		for (int j=0; j<matrix[0].length; ++j) {
    			matrix[0][j] = 0;
    		}
    	}
    	if (zero_column) {
    		for (int i=0; i<matrix.length; ++i) {
    			matrix[i][0] = 0;
    		}
    	}
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SetMatrixZeroes solution = new SetMatrixZeroes();
		int[][] matrix = new int[][]{
				{0,0,0,5},
				{4,3,1,4},
				{0,1,1,4},
				{1,2,1,3},
				{0,0,1,1}
		};

		//matrix = new int[][]{{1}};
		solution.setZeroes(matrix);
		for (int i=0; i<matrix.length; ++i) {
			for (int j=0; j<matrix[j].length; ++j) {
				System.out.print(matrix[i][j]+",");
			}
			System.out.println();
		}
	}

}
