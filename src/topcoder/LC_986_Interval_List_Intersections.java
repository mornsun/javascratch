/**
 * 
 */
package topcoder;

import java.util.ArrayList;

/**
 * 	Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
 *
 * Example 1:
 *
 * Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 *
 * Note:
 *
 * 0 <= A.length < 1000
 * 0 <= B.length < 1000
 * 0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 *
 * Two Pointers
 *
 * @author Chauncey
 * Runtime: 2 ms, faster than 99.89% of Java online submissions for Interval List Intersections.
 * Memory Usage: 46.5 MB, less than 56.76% of Java online submissions for Interval List Intersections.
 */
public class LC_986_Interval_List_Intersections
{
	public int[][] intervalIntersection(int[][] A, int[][] B) {
		if (A==null || B==null || A.length==0 || B.length==0)
			return new int[0][2];
		ArrayList<int[]> res = new ArrayList<>();
		for (int i=0, j=0; i<A.length && j<B.length; ) {
			if (A[i][1] >= B[j][1]) {
				if (A[i][0]<=B[j][0])
					res.add(new int[]{B[j][0], B[j][1]});
				else if (A[i][0]<=B[j][1])
					res.add(new int[]{A[i][0], B[j][1]});
				j++;
			} else {
				if (B[j][0]<=A[i][0])
					res.add(new int[]{A[i][0], A[i][1]});
				else if (B[j][0]<=A[i][1])
					res.add(new int[]{B[j][0], A[i][1]});
				i++;
			}
		}
		int[][] array = res.toArray(new int[0][]);
		return array;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_986_Interval_List_Intersections solution = new LC_986_Interval_List_Intersections();
        System.out.println(solution.intervalIntersection(new int[][]{{0,2},{5,10},{13,23},{24,25}}, new int[][]{{1,5},{8,12},{15,24},{25,26}})); //[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
