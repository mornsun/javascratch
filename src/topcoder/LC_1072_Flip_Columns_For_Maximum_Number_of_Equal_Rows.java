/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	Given a matrix consisting of 0s and 1s, we may choose any number of columns in the matrix and flip every cell in that column.  Flipping a cell changes the value of that cell from 0 to 1 or from 1 to 0.
 *
 * Return the maximum number of rows that have all values equal after some number of flips.
 *
 * Example 1:
 *
 * Input: [[0,1],[1,1]]
 * Output: 1
 * Explanation: After flipping no values, 1 row has all values equal.
 * Example 2:
 *
 * Input: [[0,1],[1,0]]
 * Output: 2
 * Explanation: After flipping values in the first column, both rows have equal values.
 * Example 3:
 *
 * Input: [[0,0,0],[0,0,1],[1,1,0]]
 * Output: 2
 * Explanation: After flipping values in the first two columns, the last two rows have equal values.
 *
 *
 * Note:
 *
 * 1 <= matrix.length <= 300
 * 1 <= matrix[i].length <= 300
 * All matrix[i].length's are equal
 * matrix[i][j] is 0 or 1
 *
 * @author Chauncey
 * Runtime: 18 ms, faster than 92.15% of Java online submissions for Flip Columns For Maximum Number of Equal Rows.
 * Memory Usage: 59.7 MB, less than 100.00% of Java online submissions for Flip Columns For Maximum Number of Equal Rows.
 */
public class LC_1072_Flip_Columns_For_Maximum_Number_of_Equal_Rows
{
	public int maxEqualRowsAfterFlips(int[][] matrix) {

		if (matrix==null || matrix.length==0 || matrix[0].length==0)
			return 0;

		HashMap<List<Integer>, Integer> m = new HashMap<>();
		int max = 0;
		for (int[] row : matrix) {
			List<Integer> li = new ArrayList<>();
			boolean flip = row[0] == 1;
			int bit = 0;
			int bitmap = 0;
			for (int v : row) {
				v = flip ? (v==0 ? 1 : 0) : v;
				if (v==0) bit++;
				else bitmap |= 1<<bit++;
				if (bit == 32) {
					li.add(bitmap);
					bitmap = 0;
					bit = 0;
				}
			}
			if (bitmap>0) li.add(bitmap);
			int cnt = m.getOrDefault(li, 0) + 1;
			//System.out.print(li);
			//System.out.print(" ");
			max = Math.max(max, cnt);
			m.put(li, cnt);
		}
		return max;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1072_Flip_Columns_For_Maximum_Number_of_Equal_Rows solution = new LC_1072_Flip_Columns_For_Maximum_Number_of_Equal_Rows();
        System.out.println(solution.maxEqualRowsAfterFlips(new int[][]{{0,1},{1,1}})); //1
		System.out.println(solution.maxEqualRowsAfterFlips(new int[][]{{0,1},{1,0}})); //2
		System.out.println(solution.maxEqualRowsAfterFlips(new int[][]{{0,0,0},{0,0,1},{1,1,0}})); //2
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
