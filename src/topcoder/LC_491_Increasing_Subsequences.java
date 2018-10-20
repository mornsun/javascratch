/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

 

Example:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

Output:  [1,2,4,7,5,3,6,8,9]

Explanation:

 

Note:

The total number of elements of the given matrix will not exceed 10,000.

 * @author Chauncey
 *
 */
public class LC_491_Increasing_Subsequences
{
    public int[] findDiagonalOrder(int[][] matrix)
    {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
        	return new int[0];
        }
        
        int m=matrix.length, n=matrix[0].length;
        int[] res = new int[m*n];
        int d = -1;
        int y=0, s=0;
        
        int ofs = 0;
        while (ofs < res.length) {
//        	System.out.println(y+":"+(s-y));
        	res[ofs++] = matrix[y][s-y];
        	y += d;
        	boolean turn = false;
        	if (y < 0) {
        		y = 0;
//            	System.out.println('a');
            	if (!turn) {
            		++s;
            		d = -d;
            		turn = true;
            	}
        	}
        	if (s-y < 0) {
//            	System.out.println('b');
            	if (!turn) {
            		++s;
            		d = -d;
            		turn = true;
            	}
        	}
        	if (y == m) {
        		y = m-1;
//            	System.out.println('c');
            	if (!turn) {
            		++s;
            		d = -d;
            		turn = true;
            	}
        	}
        	if (s-y == n) {
//            	System.out.println('d');
            	if (!turn) {
            		++s;
            		d = -d;
            		turn = true;
            	}
        		y = s - (n-1);
        	}
        }
        return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		int[][] matrix = new int[][]{
			{ 1, 2, 3 },
			{ 4, 5, 6 },
			{ 7, 8, 9 }
		};
		LC_491_Increasing_Subsequences solution = new LC_491_Increasing_Subsequences();
		System.out.println(Arrays.toString(solution.findDiagonalOrder(matrix)));
		

		matrix = new int[][]{
			{ 1, 2, 3, 10 },
			{ 4, 5, 6, 11 },
			{ 7, 8, 9, 12 }
		};
		System.out.println(Arrays.toString(solution.findDiagonalOrder(matrix)));

		matrix = new int[][]{
			{ 1, 2, 3 },
			{ 4, 5, 6 },
			{ 7, 8, 9 },
			{ 10, 11, 12 }
		};
		System.out.println(Arrays.toString(solution.findDiagonalOrder(matrix)));
		
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
