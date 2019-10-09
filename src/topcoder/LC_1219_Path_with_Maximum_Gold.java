/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 	In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
 *
 * Return the maximum amount of gold you can collect under the conditions:
 *
 * Every time you are located in a cell you will collect all the gold in that cell.
 * From your position you can walk one step to the left, right, up or down.
 * You can't visit the same cell more than once.
 * Never visit a cell with 0 gold.
 * You can start and stop collecting gold from any position in the grid that has some gold.
 *
 * Example 1:
 *
 * Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
 * Output: 24
 * Explanation:
 * [[0,6,0],
 *  [5,8,7],
 *  [0,9,0]]
 * Path to get the maximum gold, 9 -> 8 -> 7.
 * Example 2:
 *
 * Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * Output: 28
 * Explanation:
 * [[1,0,7],
 *  [2,0,6],
 *  [3,4,5],
 *  [0,3,0],
 *  [9,0,20]]
 * Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
 *
 * Constraints:
 *
 * 1 <= grid.length, grid[i].length <= 15
 * 0 <= grid[i][j] <= 100
 * There are at most 25 cells containing gold.
 *
 * @author Chauncey
 * Runtime: 17 ms, faster than 57.22% of Java online submissions for Path with Maximum Gold.
 * Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Path with Maximum Gold.
 */
public class LC_1219_Path_with_Maximum_Gold
{
	public int getMaximumGold(int[][] grid)
	{
		if (grid==null || grid.length==0 || grid[0].length==0)
			return 0;

		int m=grid.length, n=grid[0].length;
		int max = 0;
		for (int i=0; i<m; ++i)
			for (int j=0; j<n; ++j)
				max = Math.max(max, DfsHelper(grid, i, j));

		return max;
	}

	private int[] yNexts = new int[]{-1, 0, 1, 0};
	private int[] xNexts = new int[]{0, -1, 0, 1};

	private int DfsHelper(int[][] grid, int y, int x)
	{
		if (y<0 || x<0 || y>=grid.length || x>=grid[0].length || grid[y][x]==0 || grid[y][x]>100)
			return 0;

		grid[y][x] += 1000;
		int max = 0;
		for (int i=0; i<4; ++i)
			max = Math.max(max, DfsHelper(grid, y+yNexts[i], x+xNexts[i]));
		grid[y][x] -= 1000;

		return max + grid[y][x];
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1219_Path_with_Maximum_Gold solution = new LC_1219_Path_with_Maximum_Gold();
        System.out.println(solution.getMaximumGold(new int[][]{{0,6,0},{5,8,7},{0,9,0}})); //24
		System.out.println(solution.getMaximumGold(new int[][]{{1,0,7},{2,0,6},{3,4,5},{0,3,0},{9,0,20}})); //28
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
