/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 	Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map, return the volume of water it can trap after raining.
 *
 * Example 1:
 * Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * Output: 4
 * Explanation: After the rain, water is trapped between the blocks.
 * We have two small pounds 1 and 3 units trapped.
 * The total volume of water trapped is 4.
 *
 * Example 2:
 * Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * Output: 10
 *
 * Constraints:
 *
 * m == heightMap.length
 * n == heightMap[i].length
 * 1 <= m, n <= 200
 * 0 <= heightMap[i][j] <= 2 * 104
 *
 * @author Chauncey
 * Runtime: 18 ms, faster than 58.01% of Java online submissions for Trapping Rain Water II.
 * Memory Usage: 42.1 MB, less than 49.19% of Java online submissions for Trapping Rain Water II.
 */
public class xLC_407_Trapping_Rain_Water_II
{
	private int[] tos = new int[]{0,-1,0,1,0};
	public int trapRainWater(int[][] heightMap) {
		if (heightMap==null || heightMap.length==0 || heightMap[0]==null || heightMap[0].length==0) {
			return 0;
		}
		int m=heightMap.length, n=heightMap[0].length;
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->o1[0]-o2[0]);
		boolean[][] visited = new boolean[m][n];
		int res = 0;
		for (int i=0; i<n; ++i) {
			pq.offer(new int[]{heightMap[0][i], 0, i});
			visited[0][i] = true;
			pq.offer(new int[]{heightMap[m-1][i], m-1, i});
			visited[m-1][i] = true;
		}
		for (int i=1; i<m-1; ++i) {
			pq.offer(new int[]{heightMap[i][0], i, 0});
			visited[i][0] = true;
			pq.offer(new int[]{heightMap[i][n-1], i, n-1});
			visited[i][n-1] = true;
		}
		while (!pq.isEmpty()) {
			int[] lowest = pq.poll();
			int h=lowest[0], y=lowest[1], x=lowest[2];
			for (int i=0; i<tos.length-1; ++i) {
				int y1 = y+tos[i], x1 = x+tos[i+1];
				if (y1<0 || x1<0 || y1>=m || x1>=n || visited[y1][x1])
					continue;
				if (h<=heightMap[y1][x1])
					pq.offer(new int[]{heightMap[y1][x1], y1, x1});
				else {
					pq.offer(new int[]{h, y1, x1});
					res += h-heightMap[y1][x1];
				}
				visited[y1][x1] = true;
			}
		}
		return res;
	}

		/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_407_Trapping_Rain_Water_II solution = new xLC_407_Trapping_Rain_Water_II();
        System.out.println(solution.trapRainWater(new int[][]{{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}})); //4
		System.out.println(solution.trapRainWater(new int[][]{{3,3,3,3,3},{3,2,2,2,3},{3,2,1,2,3},{3,2,2,2,3},{3,3,3,3,3}})); //10
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
