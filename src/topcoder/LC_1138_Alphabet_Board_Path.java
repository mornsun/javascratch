/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 	On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
 *
 * Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.
 *
 * We may make the following moves:
 *
 * 'U' moves our position up one row, if the position exists on the board;
 * 'D' moves our position down one row, if the position exists on the board;
 * 'L' moves our position left one column, if the position exists on the board;
 * 'R' moves our position right one column, if the position exists on the board;
 * '!' adds the character board[r][c] at our current position (r, c) to the answer.
 * (Here, the only positions that exist on the board are positions with letters on them.)
 *
 * Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any path that does so.
 *
 * Example 1:
 *
 * Input: target = "leet"
 * Output: "DDR!UURRR!!DDD!"
 * Example 2:
 *
 * Input: target = "code"
 * Output: "RR!DDRR!UUL!R!"
 *
 * Constraints:
 *
 * 1 <= target.length <= 100
 * target consists only of English lowercase letters.

 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Alphabet Board Path.
 * Memory Usage: 34.3 MB, less than 100.00% of Java online submissions for Alphabet Board Path.
 */
public class LC_1138_Alphabet_Board_Path
{
	public String alphabetBoardPath(String target) {
		if(target == null || target.length()==0)
			return "";

		StringBuilder sb = new StringBuilder();
		int n = target.length(), y = 0, x = 0;
		for (int i=0; i<n; ++i) {
			int offset = target.charAt(i)-'a';
			int ny = offset/5;
			int nx = offset%5;
			int step = ny-y;
			char ch = step>=0 ? 'D' : 'U';
			if (step<0)
				step = -step;
			for (int k = 0; k<step; ++k) {
				sb.append(ch);
			}
			step = nx-x;
			ch = step>=0 ? 'R' : 'L';
			if (step<0)
				step = -step;
			if (offset==25 && sb.charAt(sb.length()-1)=='D') {
				sb.setLength(sb.length()-1);
				for (int k = 0; k<step; ++k) {
					sb.append(ch);
				}
				sb.append('D');
			} else {
				for (int k = 0; k<step; ++k) {
					sb.append(ch);
				}
			}
			sb.append('!');
			y = ny;
			x = nx;
		}
		return sb.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1138_Alphabet_Board_Path solution = new LC_1138_Alphabet_Board_Path();
		System.out.println(solution.alphabetBoardPath("zdz")); //"DDDDD!UUUUURRR!DDDDLLLD!"

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
