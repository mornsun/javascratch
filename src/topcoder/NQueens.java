/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Hide Tags Backtracking
Hide Similar Problems (H) N-Queens II

 * @author Chauncey
 *
 */
public class NQueens
{
	private static boolean[] takeColumn;
	private static boolean[] takeDiag;
	private static boolean[] takeADiag;
	private static void dfs(List<List<String>> res, boolean[][] matrix, int row)
	{
		if (row == matrix.length) {
			List<String> path = new ArrayList<String>(matrix.length);
			for (int i=0; i<matrix.length; ++i) {
				StringBuffer sb = new StringBuffer();
				for (int j=0; j<matrix[i].length; ++j) {
					if (matrix[i][j])
						sb.append("Q");
					else
						sb.append(".");
				}
				path.add(sb.toString());
			}
			res.add(path);
		} else {
			for (int i=0; i<matrix[row].length; ++i) {
				if (takeColumn[i] || takeDiag[matrix.length-1-row+i] || takeADiag[row+i])
					continue;
				matrix[row][i] = takeColumn[i] = takeDiag[matrix.length-1-row+i] = takeADiag[row+i] = true;
				dfs(res, matrix, row+1);
				matrix[row][i] = takeColumn[i] = takeDiag[matrix.length-1-row+i] = takeADiag[row+i] = false;
			}
		}
	}
    public static List<List<String>> solveNQueens(int n) {
		List<List<String>> res = new ArrayList<List<String>>(n);
    	if (n==0) return res;
        boolean[][] matrix = new boolean[n][n];
        takeColumn = new boolean[n];
        takeDiag = new boolean[n*2-1];
        takeADiag = new boolean[n*2-1];
        dfs(res, matrix, 0);
        return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		/*System.out.println(row);
		for (int i=0; i< matrix.length; ++i) {
			for (int j=0; j<matrix[i].length; ++j) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}*/
		System.out.println(solveNQueens(4));
	}

}
