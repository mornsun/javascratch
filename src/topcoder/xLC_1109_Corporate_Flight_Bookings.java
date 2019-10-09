/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 	There are n flights, and they are labeled from 1 to n.
 * We have a list of flight bookings.  The i-th booking bookings[i] = [i, j, k] means that we booked k seats from flights labeled i to j inclusive.
 * Return an array answer of length n, representing the number of seats booked on each flight in order of their label.
 *
 * Example 1:
 *
 * Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * Output: [10,55,45,25,25]
 *
 * Constraints:
 *
 * 1 <= bookings.length <= 20000
 * 1 <= bookings[i][0] <= bookings[i][1] <= n <= 20000
 * 1 <= bookings[i][2] <= 10000
 *
 * @author Chauncey
 * Runtime: 3 ms, faster than 99.73% of Java online submissions for Corporate Flight Bookings.
 * Memory Usage: 66 MB, less than 100.00% of Java online submissions for Corporate Flight Bookings.
 */
public class xLC_1109_Corporate_Flight_Bookings
{
	public int[] corpFlightBookings(int[][] bookings, int n) {
		int[] res = new int[n];
		if (bookings==null || bookings.length==0)
			return res;

		for (int[] b : bookings) {
			res[b[0]-1] += b[2];
			if (b[1]<n) res[b[1]] -= b[2];
		}
		for (int i=1; i<n; ++i) {
			res[i] += res[i-1];
		}

		return res;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_1109_Corporate_Flight_Bookings solution = new xLC_1109_Corporate_Flight_Bookings();
        System.out.println(solution.corpFlightBookings(new int[][]{{2,2,50},{1,1,35},{3,3,40},{1,4,50}}, 4)); //{85,100,90,50}
		System.out.println(solution.corpFlightBookings(new int[][]{{1,2,10},{2,3,20},{2,5,25}}, 5)); //
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
