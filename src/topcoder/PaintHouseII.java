package topcoder;

import java.util.*;

/**
 * There are a row of n houses, each house can be painted with one of the k colors.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color. 

The cost of painting each house with a certain color is represented by a n x k cost matrix.
For example, costs[0][0] is the cost of painting house 0 with color 0;
costs[1][2] is the cost of painting house 1 with color 2, and so on...
Find the minimum cost to paint all houses. 

Note: 
All costs are positive integers. 

Follow up: 
Could you solve it in O(nk) runtime? 

time complexity O(nk), space complexity O(k), without modifying input parameters
This solution takes reference to 238. Product of Array Except Self
Other solution with higher performance: find minimum and second minimum (may = minimum) 

 * @author Chauncey
 *
 */
public class PaintHouseII
{
    public int minCostII(int[][] costs) {  
		if (costs == null || costs.length==0 || costs[0].length==0) return 0;
		int k = costs[0].length;
		int[][] colors = new int[2][k];
		int cur = 1, prev = 0;
		for (int[] cost : costs) {
			colors[cur][0] = Integer.MAX_VALUE;
			for (int i=1; i<k; ++i) {
				colors[cur][i] = Math.min(colors[prev][i-1], colors[cur][i-1]);
			}
			//colors[cur][k-1] = cost[k-1] + colors[cur][k-1];
			int min = Integer.MAX_VALUE;
			for (int i=k-1; i>=0; --i) {
				colors[cur][i] = cost[i] + Math.min(colors[cur][i], min);
				if (colors[prev][i] < min) min = colors[prev][i];
			}
			cur = ((~cur) & 1);
			prev = ((~prev) & 1);
		}
		int min = colors[prev][0];
		for (int cost: colors[prev]) {
			if (cost < min) min = cost;
		}
		return min;
	}
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		PaintHouseII solution = new PaintHouseII();
		
		System.out.println(solution.minCostII(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
	}

}
