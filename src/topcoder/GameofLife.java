package topcoder;

import java.util.*;

/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Hide Tags Array
Hide Similar Problems (M) Set Matrix Zeroes

 * @author Chauncey
 *
 */
public class GameofLife
{
	private final void check_cell(int[][] board, int row, int col) {
		int livecnt = 0;
		for (int i=row-1; i<=row+1; ++i) {
			if (i<0 || i>=board.length) continue;
			for (int j=col-1; j<=col+1; ++j) {
				if (j<0 || j>=board[0].length) continue;
				if (i==row && j==col) continue;
				if (board[i][j] == 1 || board[i][j] == 10)
					++livecnt;
			}
		}
		if (board[row][col] == 0) {
			if (livecnt==3)
				board[row][col] = 11;
		} else {
			if (livecnt<2 || livecnt>3)
				board[row][col] = 10;
		}
	}
    public void gameOfLife(int[][] board) {
        if (null == board || board.length==0 || board[0].length==0) return;
        for (int i=0; i<board.length; ++i) {
        	for (int j=0; j<board[i].length; ++j) {
        		check_cell(board, i, j);
        	}
        }
        for (int i=0; i<board.length; ++i) {
        	for (int j=0; j<board[i].length; ++j) {
        		if (board[i][j] == 10)
        			board[i][j] = 0;
        		if (board[i][j] == 11)
        			board[i][j] = 1;
        	}
        }
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		GameofLife solution = new GameofLife();
		int[][] matrix = new int[][]{{0,1,1,0},{1,1,1,1},{0,1,1,0},{0,1,1,0}};
		solution.gameOfLife(matrix);
		for (int[] row : matrix) {
			for (int cell : row) {
				System.out.print(cell);
			}
			System.out.println();
		}
	}

}
