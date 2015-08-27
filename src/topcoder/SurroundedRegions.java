package topcoder;

import java.util.*;

/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Hide Tags Breadth-first Search Union Find
Hide Similar Problems (M) Number of Islands

 * @author Chauncey
 *
 */
public class SurroundedRegions
{
    public void solve(char[][] board) {
    	if (null == board || board.length==0 || board[0].length==0) return;
        for (int i=0; i<board.length; ++i) {
    		if (board[i][0] == 'O') bfs(board, i, 0);
    		if (board[i][board[0].length-1] == 'O') bfs(board, i, board[0].length-1);
        }
    	for (int j=0; j<board[0].length; ++j) {
    		if (board[0][j] == 'O') bfs(board, 0, j);
    		if (board[board.length-1][j] == 'O') bfs(board, board.length-1, j);
    	}
        for (int i=0; i<board.length; ++i) {
        	for (int j=0; j<board[0].length; ++j) {
        		if (board[i][j] == 'O') board[i][j] = 'X';
        		if (board[i][j] == '+') board[i][j] = 'O';
        	}
        }
    }

	private final void bfs(char[][] board, int i, int j) {
		board[i][j] = '+';
		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[]{i,j});
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			i = cur[0]; j = cur[1];
			if (i > 0 && board[i-1][j] == 'O') {
				queue.add(new int[]{i-1, j});
				board[i-1][j] = '+';
			}
			if (i < board.length-1 && board[i+1][j] == 'O') {
				queue.add(new int[]{i+1, j});
				board[i+1][j] = '+';
			}
			if (j > 0 && board[i][j-1] == 'O') {
				queue.add(new int[]{i, j-1});
				board[i][j-1] = '+';
			}
			if (j < board[0].length-1 && board[i][j+1] == 'O') {
				queue.add(new int[]{i, j+1});
				board[i][j+1] = '+';
			}
		}
	}
	
	private static final void print_matrix(char[][] matrix) {
		for (int i=0; i<matrix.length; ++i) {
			for (int j=0; j<matrix[0].length; ++j) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SurroundedRegions solution = new SurroundedRegions();
		/*char[][] matrix = new char[][]{
				{'X', 'X', 'X', 'X'},
				{'X', '0', '0', 'X'},
				{'X', 'X', '0', 'X'},
				{'X', '0', 'X', 'X'}
		};*/
		char[][] matrix = new char[][]{
				{'X', 'X', 'X'},
				{'X', 'O', 'X'},
				{'X', 'X', 'X'}
		};

		solution.solve(matrix);
		//char[][] matrix = new char[][]{{'1'}};
		print_matrix(matrix);
	}

}
