/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 */
package topcoder;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.


A sudoku puzzle...


...and its solution numbers marked in red.

Hide Tags Backtracking Hash Table
Hide Similar Problems (E) Valid Sudoku

 * @author Chauncey
 *
 */
public class SudokuSolver {
    private static boolean dfs(char[][] board, int row, int column, boolean[][] takeRow, boolean[][] takeColumn, boolean[][] takeGrid) {
    	int nextR = row, nextC = column+1;
		for (; nextR<9; ++nextR) {
			for (; nextC<9; ++nextC) {
				if (board[nextR][nextC] == '.') break;
			}
			if (nextC != 9) break;
			nextC = 0;
		}
		//System.out.println(row+","+column+"],["+nextR+","+nextC);
    	int grid = (row/3*3)+column/3;
    	for (int i=0; i<9; ++i) {
    		//System.out.println(takeRow[row][i] +","+ takeColumn[column][i] +","+ takeGrid[grid][i]);
    		if (takeRow[row][i] || takeColumn[column][i] || takeGrid[grid][i])
    			continue;
    		board[row][column] = (char)('1'+i);
    		takeRow[row][i] = takeColumn[column][i] = takeGrid[grid][i] = true;
    		if (nextR == 9) return true;
    		if (true == dfs(board, nextR, nextC, takeRow, takeColumn, takeGrid)) {
    			return true;
    		}
    		takeRow[row][i] = takeColumn[column][i] = takeGrid[grid][i] = false;
    		board[row][column] = '.';
    	}
    	return false;
    }
    
    public static void solveSudoku(char[][] board) {
    	boolean[][] takeRow = new boolean[9][9];
    	boolean[][] takeColumn = new boolean[9][9];
    	boolean[][] takeGrid = new boolean[9][9];
    	if (null == board || board.length!=9 || board[0].length!=9) return;
    	int nextR = -1, nextC = -1;
		for (int r=0; r<9; ++r) {
			for (int c=0; c<9; ++c) {
				if (board[r][c] != '.') {
					int idx = board[r][c] - '1';
		    		takeRow[r][idx] = takeColumn[c][idx] = takeGrid[(r/3*3)+c/3][idx] = true;
				} else if (nextR == -1 && board[r][c] == '.') {
					nextR = r;
					nextC = c;
				}
			}
		}
		if (nextR != -1)
			dfs(board, nextR, nextC, takeRow, takeColumn, takeGrid);
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    /*char[][] a = new char[][] {{'.', '8', '7', '6', '5', '4', '3', '2', '1',},
	            {'2', '.', '.', '.', '.', '.', '.', '.', '.',},
	            {'3', '.', '.', '.', '.', '.', '.', '.', '.',},
	            {'4', '.', '.', '.', '.', '.', '.', '.', '.',},
	            {'5', '.', '.', '.', '.', '.', '.', '.', '.',},
	            {'6', '.', '.', '.', '.', '.', '.', '.', '.',},
	            {'7', '.', '.', '.', '.', '.', '.', '.', '.',},
	            {'8', '.', '.', '.', '.', '.', '.', '.', '.',},
	            {'1', '.', '.', '.', '.', '.', '.', '.', '.',}};*/
	    char[][] a = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
	    		{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
	    		{'.', '9', '8', '.', '.', '.', '.', '6', '.', },
	    		{'8', '.', '.', '.', '6', '.', '.', '.', '3', },
	    		{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
	    		{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
	    		{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
	    		{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
	    		{'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
	    solveSudoku(a);
	    for (int i=0; i<a.length; ++i) {
	    	for (int j=0; j<a[i].length; ++j) {
	    		System.out.print(a[i][j]+", ");
	    	}
			System.out.println();
	    }
	}

}
