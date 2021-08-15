package topcoder;

import java.util.*;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
For example,

Given [[0, 30],[5, 10],[15, 20]],
return 2.

 * @author Chauncey
 *
 */
public class xMeetingRoomsII
{
	public int minMeetingRooms(int[][] intervals) {
		if (intervals==null)
			return 0;
		int[][] events = new int[intervals.length<<1][2];
		for (int i=0; i<intervals.length; ++i) {
			events[i<<1][0] = intervals[i][0];
			events[i<<1][1] = 1;
			events[(i<<1)+1][0] = intervals[i][1];
			events[(i<<1)+1][1] = -1;
		}
		Arrays.sort(events, (o1, o2)->o1[0]-o2[0]);
		int max = 0, rooms=0;
		for (int[] event : events) {
			rooms += event[1];
			max = Math.max(rooms, max);
		}
		return max;
	}

    public int minMeetingRooms1(int[][] intervals) {
    	if (intervals == null || intervals.length==0) return 0;
    	Arrays.sort(intervals, (o1,o2)->o1[0]-o2[0]);
    	PriorityQueue<Integer> pqueue = new PriorityQueue<Integer>();
    	int max = 0;
    	for (int[] interval : intervals) {
    		if (pqueue.isEmpty()) {
    			pqueue.offer(interval[1]);
    		} else {
    			int prev_end = pqueue.peek();
    			while (interval[0] >= prev_end) {
    				pqueue.poll();
					prev_end = pqueue.isEmpty() ? Integer.MAX_VALUE : pqueue.peek();
    			}
    			pqueue.offer(interval[1]);
    		}
    		max = Math.max(max, pqueue.size());
    	}
    	return max;
	}
        /*Arrays.sort(intervals, (o1, o2) -> { //lambda
            int r = o1.start - o2.start;
            return r == 0 ? o1.end - o2.end : r;
        });*/
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		xMeetingRoomsII solution = new xMeetingRoomsII();
		System.out.println(solution.minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}})); //2
		System.out.println(solution.minMeetingRooms(new int[][]{{0,30},{6,9},{5,10},{15,20}})); //3
		System.out.println(solution.minMeetingRooms(new int[][]{{7,10},{2,4}})); //1
	}

}
