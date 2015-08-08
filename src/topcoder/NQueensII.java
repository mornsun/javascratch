/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author cherry
 *
 */
public class NQueensII
{
	private static boolean[] takeColumn;
	private static boolean[] takeDiag;
	private static boolean[] takeADiag;
	private static int dfs(boolean[][] matrix, int row)
	{
		if (row == matrix.length) {
			return 1;
		} else {
			int cnt = 0;
			for (int i=0; i<matrix[row].length; ++i) {
				if (takeColumn[i] || takeDiag[matrix.length-1-row+i] || takeADiag[row+i])
					continue;
				matrix[row][i] = takeColumn[i] = takeDiag[matrix.length-1-row+i] = takeADiag[row+i] = true;
				cnt += dfs(matrix, row+1);
				matrix[row][i] = takeColumn[i] = takeDiag[matrix.length-1-row+i] = takeADiag[row+i] = false;
			}
			return cnt;
		}
	}
    public static int totalNQueens(int n) {
    	if (n==0) return 0;
        boolean[][] matrix = new boolean[n][n];
        takeColumn = new boolean[n];
        takeDiag = new boolean[n*2-1];
        takeADiag = new boolean[n*2-1];
        return dfs(matrix, 0);
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
		System.out.println(totalNQueens(4));
	}

}
