/**
 * 
 */
package topcoder;

import java.util.HashMap;
import java.util.Map;

/**
 * 	We write the integers of A and B (in the order they are given) on two separate horizontal lines.
 *
 * Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:
 *
 * A[i] == B[j];
 * The line we draw does not intersect any other connecting (non-horizontal) line.
 * Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.
 *
 * Return the maximum number of connecting lines we can draw in this way.
 *
 * Example 1:
 *
 * Input: A = [1,4,2], B = [1,2,4]
 * Output: 2
 * Explanation: We can draw 2 uncrossed lines as in the diagram.
 * We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.
 *
 * Example 2:
 *
 * Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
 * Output: 3
 *
 * Example 3:
 *
 * Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
 * Output: 2
 *
 * Note:
 *
 * 1 <= A.length <= 500
 * 1 <= B.length <= 500
 * 1 <= A[i], B[i] <= 2000
 *
 * Array

 * @author Chauncey
 * Runtime: 6 ms, faster than 28.66%
 */
public class LC_1035_Uncrossed_Lines
{
    public int maxUncrossedLines(int[] A, int[] B) { //LCS
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return 0;
        }

        int[][] dp = new int[A.length + 1][B.length + 1];

        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < B.length; ++j) {
                if (A[i] == B[j]) {
                    dp[i + 1][j + 1] = Math.max(dp[i][j] + 1, Math.max(dp[i + 1][j], dp[i][j + 1]));
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j], Math.max(dp[i + 1][j], dp[i][j + 1]));
                }
            }
        }

        return dp[A.length][B.length];
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1035_Uncrossed_Lines solution = new LC_1035_Uncrossed_Lines();
		System.out.println(solution.maxUncrossedLines(new int[]{1,4,2}, new int[]{1,2,4}));
        System.out.println(solution.maxUncrossedLines(new int[]{2,5,1,2,5}, new int[]{10,5,2,1,5,2}));
        System.out.println(solution.maxUncrossedLines(new int[]{1,3,7,1,7,5}, new int[]{1,9,2,5,1}));

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
