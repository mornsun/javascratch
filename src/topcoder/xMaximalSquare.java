package topcoder;

import java.util.*;

import topcoder.UniqueBinarySearchTreesII.TreeNode;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.
Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.

Hide Tags Dynamic Programming
Hide Similar Problems (H) Maximal Rectangle

 * @author Chauncey
 *
 */
public class xMaximalSquare
{
    public int maximalSquare1(char[][] matrix) {
        if (matrix ==null || matrix.length ==0 || matrix[0].length ==0) return 0;
        int max_area = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] l = new int[n];
        int[] r = new int[n];
        Arrays.fill(r, n);
        int[] h = new int[n];
        for (int i=0; i<m; ++i) {
        	int left = 0;
        	for (int j=0; j<n; ++j) {
        		if (matrix[i][j] == '1') {
                    if (left > l[j]) l[j] = left;
                    ++h[j];
        		} else {
        			left = j + 1;
        			h[j] = 0;
        			l[j] = 0;
        			r[j] = n;
        		}
        	}
            int right = n;
            for (int j=n-1; j>=0; --j) {
                if (matrix[i][j] == '1') {
                    if (right < r[j]) r[j] = right;
                    if (r[j]-l[j]>=h[j]) {
                    	int area = h[j]*h[j];
                    	if (area > max_area) max_area = area;
                    }
                } else {
                    right = j;
                }
            }
        	for (int j=0; j<n; ++j) {
        		System.out.print(l[j]+":"+r[j]+":"+h[j]+" ");
        	}
        	System.out.println();
        }
        return max_area;
    }

    public int maximalSquare(char[][] matrix) {
        if (matrix ==null || matrix.length ==0 || matrix[0].length ==0) return 0;
        int max_edge = 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] f = new int[2][n];
        int prev = 0, cur = 1;
        for (int j=0; j<n; ++j) {
        	if (matrix[0][j] == '1') {
        		f[0][j] = 1;
        		if (max_edge==0) max_edge = 1;
        	}
        	//System.out.print(f[0][j]+",");
        }
    	//System.out.println();
        for (int i=1; i<m; ++i) {
        	if (matrix[i][0] == '1') {
        		f[cur][0] = 1;
        		if (max_edge==0) max_edge = 1;
        	} else {
        		f[cur][0] = 0;
        	}
        	//System.out.print(f[cur][0]+",");
        	for (int j=1; j<n; ++j) {
        		if (matrix[i][j] == '1') {
        			f[cur][j] = Math.min(f[prev][j-1], Math.min(f[prev][j], f[cur][j-1])) + 1;
        			if (f[cur][j] > max_edge) max_edge = f[cur][j];
        		} else {
        			f[cur][j] = 0;
        		}
            	//System.out.print(f[cur][j]+",");
        	}
        	//System.out.println();
			cur = ((~cur) & 1);
			prev = ((~prev) & 1);
        }
    	return max_edge*max_edge;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		xMaximalSquare solution = new xMaximalSquare();
		char[][] matrix = new char[][]{
				{'1', '1', '1', '0', '1', '0', '1', '1'},
				{'1', '1', '1', '1', '1', '1', '1', '0'},
				{'1', '1', '1', '1', '1', '1', '1', '0'},
				{'1', '1', '1', '1', '1', '0', '0', '0'},
				{'0', '1', '1', '1', '1', '0', '0', '0'},
		};

		//char[][] matrix = new char[][]{{'1'}};
		System.out.println(solution.maximalSquare1(matrix));
	}

}
