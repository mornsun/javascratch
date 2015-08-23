package topcoder;

import java.util.*;

/**
 * There are a row of n houses, each house can be painted with one of the three colors:
 * red, blue or green. The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
For example,
costs[0][0] is the cost of painting house 0 with color red;
costs[1][2] is the cost of painting house 1 with color green, and so on...
Find the minimum cost to paint all houses.

Note:
All costs are positive integers

time complexity O(n), space complexity O(1), without modifying input parameters 

 * @author Chauncey
 *
 */
public class PaintHouse
{
	public int minCost(int[][] costs) {
		if (costs == null || costs.length==0 || costs[0].length==0) return 0;
		int[][] colors = new int[2][3];
		int cur = 1, prev = 0;
		for (int[] cost : costs) {
			colors[cur][0] = Math.min(colors[prev][1], colors[prev][2]) + cost[0];
			colors[cur][1] = Math.min(colors[prev][0], colors[prev][2]) + cost[1];
			colors[cur][2] = Math.min(colors[prev][0], colors[prev][1]) + cost[2];
			cur = ((~cur) & 1);
			prev = ((~prev) & 1);
		}
		return Math.min(colors[prev][0], Math.min(colors[prev][1], colors[prev][2]));
	}
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		PaintHouse solution = new PaintHouse();
		
		System.out.println(solution.minCost(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
	}

}
