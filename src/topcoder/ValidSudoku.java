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
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

Hide Tags Hash Table
Hide Similar Problems (H) Sudoku Solver

 * @author Chauncey
 *
 */
public class ValidSudoku {

    private static void fill0 (boolean[] flag) {
        for (int i=0; i<9; ++i) {
            flag[i] = false;
        }
    }
    public static boolean isValidSudoku(char[][] board) {
        boolean[] flag = new boolean[9];
        for (int i=0; i<9; ++i) { //validate lines
            for (int j=0; j<9; ++j) {
                final int n = board[i][j]-'1';
                //System.out.print(n+", ");
                if (n>=0 && n<9) {
                    if (flag[n] == true) return false;
                    flag[n] = true;
                }
            }
            fill0(flag);
            //System.out.println();
        }
        for (int j=0; j<9; ++j) { //validate columns
            for (int i=0; i<9; ++i) {
                final int n = board[i][j]-'1';
                if (n>=0 && n<9) {
                    if (flag[n] == true) return false;
                    flag[n] = true;
                }
            }
            fill0(flag);
        }
        for (int x=0; x<3; ++x) { //validate squares
            for (int y=0; y<3; ++y) {
                final int endX = 3*(x+1);
                for(int i=3*x; i<endX; ++i) {
                    final int endY = 3*(y+1);
                    for(int j=3*y; j<endY; ++j) {
                        final int n = board[i][j]-'1';
                        if (n>=0 && n<9) {
                            if (flag[n] == true) return false;
                            flag[n] = true;
                        }
                    }
                }
                fill0(flag);
            }
        }
        return true;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	    char[][] a = new char[][] {{'.', '8', '7', '6', '5', '4', '3', '2', '1',},
	            {'2', '.', '.', '.', '.', '.', '.', '.', '.',},
	            {'3', '.', '.', '.', '.', '.', '.', '.', '.',},
	            {'4', '.', '.', '.', '.', '.', '.', '.', '.',},
	            {'5', '.', '.', '.', '.', '.', '.', '.', '.',},
	            {'6', '.', '.', '.', '.', '.', '.', '.', '.',},
	            {'7', '.', '.', '.', '.', '.', '.', '.', '.',},
	            {'8', '.', '.', '.', '.', '.', '.', '.', '.',},
	            {'9', '.', '.', '.', '.', '.', '.', '.', '.',}};
		System.out.println(isValidSudoku(a));
	}

}
