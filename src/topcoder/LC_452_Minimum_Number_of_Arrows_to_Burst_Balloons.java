/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 	There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be at most 104 balloons.
 * An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.
 *
 * Example:
 *
 * Input:
 * [[10,16], [2,8], [1,6], [7,12]]
 *
 * Output:
 * 2
 *
 * Explanation:
 * One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 *
 * Greedy
 * 
 * @author Chauncey
 * Runtime: 51 ms, faster than 43.51% of Java online submissions for Minimum Number of Arrows to Burst Balloons.
 * Memory Usage: 45.1 MB, less than 42.86% of Java online submissions for Minimum Number of Arrows to Burst Balloons.
 */
public class LC_452_Minimum_Number_of_Arrows_to_Burst_Balloons
{
	public int findMinArrowShots(int[][] ballons) {
		Arrays.sort(ballons, (o1, o2)->o1[1]-o2[1]);
		int cnt = 0;
		int[] prev = null;
		for (int[] ballon : ballons) {
			if (prev!=null && ballon[0]<=prev[1]) continue;
			cnt++;
			prev = ballon;
		}
		return cnt;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_452_Minimum_Number_of_Arrows_to_Burst_Balloons solution = new LC_452_Minimum_Number_of_Arrows_to_Burst_Balloons();
		//[[10,16],[2,8],[1,6],[7,12]]
		//[[3,9],[7,12],[3,8],[6,8],[9,10],[2,9],[0,9],[3,9],[0,6],[2,8]]
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
