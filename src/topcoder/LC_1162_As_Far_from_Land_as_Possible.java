/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 	Given an N x N grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized and return the distance.
 * The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 * If no land or water exists in the grid, return -1.
 *
 * Example 1:
 * Input: [[1,0,1],[0,0,0],[1,0,1]]
 * Output: 2
 * Explanation:
 * The cell (1, 1) is as far as possible from all the land with distance 2.
 *
 * Example 2:
 * Input: [[1,0,0],[0,0,0],[0,0,0]]
 * Output: 4
 * Explanation:
 * The cell (2, 2) is as far as possible from all the land with distance 4.
 *
 * Note:
 *
 * 1 <= grid.length == grid[0].length <= 100
 * grid[i][j] is 0 or 1
 *
 * Related Topic:
 * BFS, Graph
 *
 * Hint:
 * Can you think of this problem in a backwards way ?
 * Imagine expanding outward from each land cell. What kind of search does that ?
 * Use BFS starting from all land cells in the same time.
 * When do you reach the furthest water cell?
 *
 * @author Chauncey
 * Runtime: 17 ms, faster than 86.36% of Java online submissions for As Far from Land as Possible.
 * Memory Usage: 47.5 MB, less than 100.00% of Java online submissions for As Far from Land as Possible.
 */
public class LC_1162_As_Far_from_Land_as_Possible
{
    private int[] yofs = new int[]{-1,0,1,0};
    private int[] xofs = new int[]{0,-1,0,1};

    public int maxDistance(int[][] grid) {
        if (grid==null || grid.length==0 || grid[0].length==0)
            return -1;

        LinkedList<int[]> curr = new LinkedList<>();
        int m = grid.length, n=grid[0].length;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1)
                    curr.add(new int[]{i, j});
            }
        }

        int res = -1;
        while (!curr.isEmpty()) {
            LinkedList<int[]> prev = curr;
            curr = new LinkedList<>();
            res++;
            while (!prev.isEmpty()) {
                int[] pos = prev.poll();
                for (int i=0; i<yofs.length; ++i) {
                    int y = pos[0] + yofs[i];
                    int x = pos[1] + xofs[i];
                    if (y<0 || y>=m || x<0 || x>=n)
                        continue;
                    if (grid[y][x] == 0) {
                        curr.add(new int[]{y, x});
                        grid[y][x] = 1;
                    }
                }
            }
        }
        return res==0 ? -1 : res;
    }

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1162_As_Far_from_Land_as_Possible solution = new LC_1162_As_Far_from_Land_as_Possible();
		//[1,2,-3,3,1]
        System.out.println(solution.maxDistance(new int[][]{{1,0,1},{0,0,0},{1,0,1}})); //2
        System.out.println(solution.maxDistance(new int[][]{{1,0,0},{0,0,0},{0,0,0}})); //4
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
