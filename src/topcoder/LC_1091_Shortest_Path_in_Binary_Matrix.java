/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	In an N by N square grid, each cell is either empty (0) or blocked (1).
 *
 * A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
 *
 * Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
 * C_1 is at location (0, 0) (ie. has value grid[0][0])
 * C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
 * If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
 * Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
 *
 * Example 1:
 *
 * Input: [[0,1],[1,0]]
 * Output: 2
 *
 * Example 2:
 *
 * Input: [[0,0,0],[1,1,0],[1,1,0]]
 * Output: 4
 *
 * Note:
 *
 * 1 <= grid.length == grid[0].length <= 100
 * grid[r][c] is 0 or 1
 *
 * @author Chauncey
 * Runtime: 17 ms, faster than 87.25% of Java online submissions for Shortest Path in Binary Matrix.
 * Memory Usage: 47.1 MB, less than 100.00% of Java online submissions for Shortest Path in Binary Matrix.
 */
public class LC_1091_Shortest_Path_in_Binary_Matrix
{
	public int shortestPathBinaryMatrix(int[][] grid) {
		if (grid==null || grid.length==0 || grid[0].length!=grid.length)
			return -1;

		int n=grid.length;
		if (grid[0][0]!=0 || grid[n-1][n-1]!=0)
			return -1;
		if (grid[0][0]==0 && n==1)
			return 1;

		LinkedList<int[]> q = new LinkedList<>();
		int dist=1;
		q.offer(new int[]{0, 0});
		grid[0][0] = 1;
		while (!q.isEmpty()) {
			LinkedList<int[]> prev = q;
			q = new LinkedList<>();
			++dist;
			while(!prev.isEmpty()) {
				int[] curr = prev.poll();
				int row = curr[0], col = curr[1];
				for (int i=Math.max(row-1,0); i<=row+1 && i<n; ++i) {
					for (int j=Math.max(col-1,0); j<=col+1 && j<n; ++j) {
						if (i==row && j==col || grid[i][j]==1) continue;
						if (i==n-1 && j==n-1) return dist;
						q.offer(new int[]{i, j});
						grid[i][j] = 1;
					}
				}

			}
		}
		return -1;

	}

	public int shortestPathBinaryMatrix_pq(int[][] grid) {
		if (grid==null || grid.length==0 || grid[0].length!=grid.length)
			return -1;

		int n=grid.length;
		if (grid[0][0]!=0 || grid[n-1][n-1]!=0)
			return -1;
		if (grid[0][0]==0 && n==1)
			return 1;

		PriorityQueue<int[]> heap = new PriorityQueue<>((x, y)->x[0]-y[0]);
		heap.offer(new int[]{n-1, 1, 0, 0});
		grid[0][0] = -1;
		while (!heap.isEmpty()) {
			int[] curr = heap.poll();
			int row = curr[2], col = curr[3], step = curr[1]+1;
			for (int i=Math.max(row-1,0); i<=row+1 && i<n; ++i) {
				for (int j=Math.max(col-1,0); j<=col+1 && j<n; ++j) {
					if (i==row && j==col || grid[i][j]==1) continue;
					if (grid[i][j]!=0 && -grid[i][j]<step) continue;
					if (i==n-1 && j==n-1) return step;
					heap.offer(new int[]{n-1-Math.min(i,j)+step, step, i, j});
					grid[i][j] = -step;
				}
			}
		}
		return -1;

	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1091_Shortest_Path_in_Binary_Matrix solution = new LC_1091_Shortest_Path_in_Binary_Matrix();
        System.out.println(solution.shortestPathBinaryMatrix(new int[][]{{0,1},{1,0}})); //2
		System.out.println(solution.shortestPathBinaryMatrix(new int[][]{{0,0,0},{1,1,0},{1,1,0}})); //4
		System.out.println(solution.shortestPathBinaryMatrix(new int[][]{{0}})); //1
		System.out.println(solution.shortestPathBinaryMatrix(new int[][]{{0,0,0,0,1,1},{0,1,0,0,1,0},{1,1,0,1,0,0},{0,1,0,0,1,1},{0,1,0,0,0,1},{0,0,1,0,0,0}})); //7

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
