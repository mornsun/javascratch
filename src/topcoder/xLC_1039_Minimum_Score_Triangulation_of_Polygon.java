/**
 * 
 */
package topcoder;

/**
 *  Given N, consider a convex N-sided polygon with vertices labelled A[0], A[i], ..., A[N-1] in clockwise order.
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
 * Hint 1
 * Without loss of generality, there is a triangle that uses adjacent vertices A[0] and A[N-1] (where N = A.length). Depending on your choice K of it, this breaks down the triangulation into two subproblems A[1:K] and A[K+1:N-1].
 *
 *  DP
 *
 * @author Chauncey
 * Runtime: 3 ms, faster than 72.84% of Java online submissions for Minimum Score Triangulation of Polygon.
 * Memory Usage: 34.5 MB, less than 100.00% of Java online submissions for Minimum Score Triangulation of Polygon.
 */
public class xLC_1039_Minimum_Score_Triangulation_of_Polygon
{
	public int minScoreTriangulation(int[] A) {

		if (A==null || A.length==0) return 0;
		int n = A.length;
		int[][] dp = new int[n][n];
		for (int d=2; d<n; ++d) {
			for (int i=0; i<n-d; ++i) {
				int j = i+d;
				int min = Integer.MAX_VALUE;
				for (int k=i+1; k<j; ++k)
					min = Math.min(min, dp[i][k]+dp[k][j]+A[i]*A[k]*A[j]);
				dp[i][j] = min;
			}
		}
		return dp[0][n-1];
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_1039_Minimum_Score_Triangulation_of_Polygon solution = new xLC_1039_Minimum_Score_Triangulation_of_Polygon();
        System.out.println(solution.minScoreTriangulation(new int[]{1,2,3})); //6
		System.out.println(solution.minScoreTriangulation(new int[]{3,7,4,5})); //144
		System.out.println(solution.minScoreTriangulation(new int[]{1,3,1,4,1,5})); //13
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
