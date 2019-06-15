/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	Given a 2-dimensional grid of integers, each value in the grid represents the color of the grid square at that location.
 *
 * Two squares belong to the same connected component if and only if they have the same color and are next to each other in any of the 4 directions.
 *
 * The border of a connected component is all the squares in the connected component that are either 4-directionally adjacent to a square not in the component, or on the boundary of the grid (the first or last row or column).
 *
 * Given a square at location (r0, c0) in the grid and a color, color the border of the connected component of that square with the given color, and return the final grid.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,1],[1,2]], r0 = 0, c0 = 0, color = 3
 * Output: [[3, 3], [3, 2]]
 * Example 2:
 *
 * Input: grid = [[1,2,2],[2,3,2]], r0 = 0, c0 = 1, color = 3
 * Output: [[1, 3, 3], [2, 3, 3]]
 * Example 3:
 *
 * Input: grid = [[1,1,1],[1,1,1],[1,1,1]], r0 = 1, c0 = 1, color = 2
 * Output: [[2, 2, 2], [2, 1, 2], [2, 2, 2]]
 *
 *
 * Note:
 *
 * 1 <= grid.length <= 50
 * 1 <= grid[0].length <= 50
 * 1 <= grid[i][j] <= 1000
 * 0 <= r0 < grid.length
 * 0 <= c0 < grid[0].length
 * 1 <= color <= 1000
 *
 * Depth-First Search

 * @author Chauncey
 * Runtime: 1 ms, faster than 99.59%
 */
public class LC_1034_Coloring_A_Border
{
    private static int[] dr = new int[]{-1,1,0,0};
    private static int[] dc = new int[]{0,0,-1,1};

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {

        if (grid==null || grid.length==0 || grid[0].length==0 || r0<0 || c0<0 || r0>grid.length || c0>grid[0].length)
            return grid;

        int clr = grid[r0][c0];
        if (clr==color)
            return grid;

        int mr = grid.length;
        int mc = grid[0].length;
        LinkedList<int[]> q = new LinkedList<>();
        q.offer(new int[]{r0,c0});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r=cur[0];
            int c=cur[1];
            int d = 0;
            //System.out.println(r+":"+c);
            for (int i=0; i<dr.length; ++i) {
                int nr = r+dr[i];
                int nc = c+dc[i];
                if (nr<0 || nr>=mr || nc<0 || nc>=mc)
                    continue;
                if (grid[nr][nc] != clr && grid[nr][nc] > 0)
                    continue;
                d++;
                if (grid[nr][nc] == clr) {
                    q.offer(new int[]{nr, nc});
                    grid[r][c] = -clr;
                }
            }
            if (d!=4)
                grid[r][c] = -color;
        }

        q.offer(new int[]{r0,c0});
        grid[r0][c0] = -grid[r0][c0];
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r=cur[0];
            int c=cur[1];
            for (int i=0; i<dr.length; ++i) {
                int nr = r+dr[i];
                int nc = c+dc[i];
                if (nr<0 || nr>=mr || nc<0 || nc>=mc)
                    continue;
                if (grid[nr][nc] < 0) {
                    grid[nr][nc] = -grid[nr][nc];
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        return grid;

    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1034_Coloring_A_Border solution = new LC_1034_Coloring_A_Border();
        System.out.println(solution.colorBorder(new int[][]{{1,2,2},{2,3,2}}, 0, 1,3)); //[[1,3,3],[2,3,3]]

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
