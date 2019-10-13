/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.List;

/**
 * 	Given a 2D array A, each cell is 0 (representing sea) or 1 (representing land)
 *
 * A move consists of walking from one land square 4-directionally to another land square, or off the boundary of the grid.
 *
 * Return the number of land squares in the grid for which we cannot walk off the boundary of the grid in any number of moves.
 *
 * Example 1:
 *
 * Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 3
 * Explanation:
 * There are three 1s that are enclosed by 0s, and one 1 that isn't enclosed because its on the boundary.
 *
 * Example 2:
 *
 * Input: [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * Output: 0
 * Explanation:
 * All 1s are either on the boundary or can reach the boundary.
 *
 * Note:
 *
 * 1 <= A.length <= 500
 * 1 <= A[i].length <= 500
 * 0 <= A[i][j] <= 1
 * All rows have the same size.
 *
 *
 * Hint 1:
 * Can you model this problem as a graph problem? Create n * m + 1 nodes where n * m nodes represents each cell of the map and one extra node to represent the exterior of the map.
 * Hint 2:
 * In the map add edges between neighbors on land cells. And add edges between the exterior and land nodes which are in the boundary. Return as answer the number of nodes that are not reachable from the exterior node.
 *
 * DFS
 * 
 * @author Chauncey
 * Runtime: 5 ms, faster than 75.73% of Java online submissions for Number of Enclaves.
 * Memory Usage: 54.4 MB, less than 71.43% of Java online submissions for Number of Enclaves.
 */
public class LC_1020_Number_of_Enclaves
{
	public int numEnclaves(int[][] A) {

		if (A==null || A.length==0 || A[0].length==0) return 0;
		int m=A.length, n=A[0].length;
		for (int i=0; i<n; ++i) {
			if (A[0][i]==1) dfs(A, 0, i);
			if (A[m-1][i]==1) dfs(A, m-1, i);
		}
		for (int i=1; i<m-1; ++i) {
			if (A[i][0]==1) dfs(A, i, 0);
			if (A[i][n-1]==1) dfs(A, i, n-1);
		}
		int res = 0;
		for (int i=1; i<m-1; ++i)
			for (int j=1; j<n-1; ++j)
				if (A[i][j]==1) res++;
		return res;
	}

	private static int[] dx = new int[]{0,1,0,-1};
	private static int[] dy = new int[]{1,0,-1,0};
	private void dfs(int[][] A, int y, int x)
	{
		int m=A.length, n=A[0].length;
		for (int i=0; i<dx.length; ++i) {
			int nx = x+dx[i], ny = y+dy[i];
			if (ny<0 || ny>=m || nx<0 || nx>=n || A[ny][nx]==0) continue;
			A[ny][nx] = 0;
			dfs(A, ny, nx);
		}
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1020_Number_of_Enclaves solution = new LC_1020_Number_of_Enclaves();
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
