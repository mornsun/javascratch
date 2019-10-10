/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	You are driving a vehicle that has capacity empty seats initially available for passengers.  The vehicle only drives east (ie. it cannot turn around and drive west.)
 * Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  The locations are given as the number of kilometers due east from your vehicle's initial location.
 * Return true if and only if it is possible to pick up and drop off all passengers for all the given trips.
 *
 * Example 1:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 *
 * Example 2:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 *
 * Example 3:
 *
 * Input: trips = [[2,1,5],[3,5,7]], capacity = 3
 * Output: true
 *
 * Example 4:
 *
 * Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
 * Output: true
 *
 * Constraints:
 *
 * trips.length <= 1000
 * trips[i].length == 3
 * 1 <= trips[i][0] <= 100
 * 0 <= trips[i][1] < trips[i][2] <= 1000
 * 1 <= capacity <= 100000
 *
 * @author Chauncey
 * Runtime: 36 ms, faster than 23.71% of Java online submissions for Car Pooling.
 * Memory Usage: 43.3 MB, less than 100.00% of Java online submissions for Car Pooling.
 * Runtime: 6 ms, faster than 63.32% of Java online submissions for Car Pooling.
 * Memory Usage: 43.4 MB, less than 100.00% of Java online submissions for Car Pooling.
 */
public class xLC_1094_Car_Pooling
{
	public boolean carPooling1(int[][] trips, int capacity) {
		if (capacity<=0)
			return false;
		if (trips==null || trips.length==0)
			return true;

		int cnt = 0;
		Arrays.sort(trips, (o1, o2) -> o1[1]-o2[1]);
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->o1[2]-o2[2]);
		for (int[] trip : trips) {
			while (!pq.isEmpty()) {
				int[] prev = pq.peek();
				if (prev[2] > trip[1]) break;
				cnt -= prev[0];
				pq.poll();
			}
			cnt += trip[0];
			if (cnt>capacity)
				return false;
			pq.offer(trip);
		}

		return true;
	}


	public boolean carPooling(int[][] trips, int capacity) {
		Map<Integer, Integer> m = new TreeMap<>();
		for (int[] t : trips) {
			m.put(t[1], m.getOrDefault(t[1], 0) + t[0]);
			m.put(t[2], m.getOrDefault(t[2], 0) - t[0]);
		}
		for (int v : m.values()) {
			capacity -= v;
			if (capacity < 0) {
				return false;
			}
		}
		return true;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_1094_Car_Pooling solution = new xLC_1094_Car_Pooling();
        System.out.println(solution.carPooling(new int[][]{{2,1,5},{3,3,7}}, 4)); //false
		System.out.println(solution.carPooling(new int[][]{{2,1,5},{3,3,7}}, 5)); //true
		System.out.println(solution.carPooling(new int[][]{{2,1,5},{3,5,7}}, 3)); //true
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
