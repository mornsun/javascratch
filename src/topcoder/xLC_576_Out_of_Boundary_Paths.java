/**
 * 
 */
package topcoder;

/**
 * 	There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: m = 2, n = 2, N = 2, i = 0, j = 0
 * Output: 6
 * Explanation:
 *
 * Example 2:
 *
 * Input: m = 1, n = 3, N = 3, i = 0, j = 1
 * Output: 12
 * Explanation:
 *
 *
 *
 * Note:
 *
 * Once you move the ball out of boundary, you cannot move it back.
 * The length and height of the grid is in range [1,50].
 * N is in range [0,50].
 *
 * DP, Depth-first Search
 *
 * @author Chauncey
 * Runtime: 7 ms, faster than 62.50% of Java online submissions for Out of Boundary Paths.
 * Memory Usage: 33.8 MB, less than 25.00% of Java online submissions for Out of Boundary Paths.
 */
public class xLC_576_Out_of_Boundary_Paths
{
	public int findPaths(int m, int n, int N, int i, int j) {
		long[][][] dp = new long[2][m][n];
		while (N-- != 0) {
			for (int y=0; y<m; ++y) {
				for (int x=0, pn=N%2, cn=(N+1)%2; x<n; ++x) {
					dp[cn][y][x] = ((y==0 ? 1 : dp[pn][y-1][x]) + (y==m-1 ? 1 : dp[pn][y+1][x])
							+ (x==0 ? 1 : dp[pn][y][x-1]) + (x==n-1 ? 1 : dp[pn][y][x+1])) % 1000000007;
				}
			}
		}
		return (int)dp[1][i][j];
	}

	int findPaths1(int m, int n, int N, int i, int j) {
		long[][][] dp = new long[51][50][50];
		for (int Ni = 1; Ni <= N; ++Ni)
			for (int mi = 0; mi < m; ++mi)
				for (int ni = 0; ni < n; ++ni)
					dp[Ni][mi][ni] = ((mi == 0 ? 1 : dp[Ni - 1][mi - 1][ni]) + (mi == m - 1? 1 : dp[Ni - 1][mi + 1][ni])
							+ (ni == 0 ? 1 : dp[Ni - 1][mi][ni - 1]) + (ni == n - 1 ? 1 : dp[Ni - 1][mi][ni + 1])) % 1000000007;
		return (int)dp[N][i][j];
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_576_Out_of_Boundary_Paths solution = new xLC_576_Out_of_Boundary_Paths();
		System.out.println(solution.findPaths(2, 2, 2, 0, 0)); //6
		System.out.println(solution.findPaths(1, 3, 3, 0, 1)); //12
		System.out.println(solution.findPaths(8, 50, 23, 5, 26)); //914783380
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
