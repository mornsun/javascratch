/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 	Given N, consider a convex N-sided polygon with vertices labelled A[0], A[i], ..., A[N-1] in clockwise order.
 *
 * Suppose you triangulate the polygon into N-2 triangles.  For each triangle, the value of that triangle is the product of the labels of the vertices, and the total score of the triangulation is the sum of these values over all N-2 triangles in the triangulation.
 *
 * Return the smallest possible total score that you can achieve with some triangulation of the polygon.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: 6
 * Explanation: The polygon is already triangulated, and the score of the only triangle is 6.
 *
 * Example 2:
 *
 * Input: [3,7,4,5]
 * Output: 144
 * Explanation: There are two triangulations, with possible scores: 3*7*5 + 4*5*7 = 245, or 3*4*5 + 3*4*7 = 144.  The minimum score is 144.
 *
 * Example 3:
 *
 * Input: [1,3,1,4,1,5]
 * Output: 13
 * Explanation: The minimum score triangulation has score 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13.
 *
 * Note:
 *
 * 3 <= A.length <= 50
 * 1 <= A[i] <= 100
 *
 * Dynamic Programming

 * @author Chauncey
 * Runtime: 53 ms, faster than 61.07%
 */
public class LC_1039_Minimum_Score_Triangulation_of_Polygon
{
	public int minScoreTriangulation(int[] A)
	{
		if (A==null || A.length<3)
			return 0;

		int n = A.length;
		int[][] dp = new int[n][n];
		for (int i=n-1; i>=0; --i) {
			for (int j=i+1; j<n; ++j ) {
				for (int k=i+1; k<j; ++k) {
					dp[i][j] = Math.min(dp[i][j] == 0 ? Integer.MAX_VALUE : dp[i][j], dp[i][k] + A[i] * A[k] * A[j] + dp[k][j]);
				}
			}
		}
		return dp[0][n-1];
	}

	public int minScoreTriangulation1(int[] A) {
		int n = A.length;
		int[][] dp = new int[n][n];
		for (int d = 2; d < n; ++d) {
			for (int i = 0; i + d < n; ++i) {
				int j = i + d;
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = i + 1; k < j; ++k)
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
			}
		}
		return dp[0][n - 1];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1039_Minimum_Score_Triangulation_of_Polygon solution = new LC_1039_Minimum_Score_Triangulation_of_Polygon();
		System.out.println(solution.minScoreTriangulation(new int[]{1,2,3}));
		System.out.println(solution.minScoreTriangulation(new int[]{3,7,4,5}));
		System.out.println(solution.minScoreTriangulation(new int[]{1,3,1,4,1,5}));

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
