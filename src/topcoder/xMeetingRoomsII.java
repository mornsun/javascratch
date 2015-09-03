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
	private final static class Interval {
		public int start;
		public int end;
		public Interval(int s, int e) {start=s; end=e;}
	}
    private Comparator<Interval> comparator = new Comparator<Interval>() {
        public int compare(Interval o1, Interval o2) {
            int r = o1.start - o2.start;
            return r == 0 ? o1.end - o2.end : r;
        }
    };
    public int minMeetingRooms(Interval[] intervals) {
    	if (intervals == null || intervals.length==0) return 0;
    	Arrays.sort(intervals, comparator);
        /*Arrays.sort(intervals, (o1, o2) -> { //lambda
            int r = o1.start - o2.start;
            return r == 0 ? o1.end - o2.end : r;
        });*/
    	PriorityQueue<Integer> pqueue = new PriorityQueue<Integer>();
    	for (Interval interval : intervals) {
    		if (pqueue.isEmpty()) {
    			pqueue.offer(interval.end);
    		} else {
    			int prev_end = pqueue.peek();
    			if (interval.start >= prev_end) {
    				pqueue.remove();
    			}
    			pqueue.offer(interval.end);
    		}
    		
    	}
    	return pqueue.size();
	}
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		List<List<Integer>> vec2d = new LinkedList<List<Integer>>();
		List<Integer> vec = new LinkedList<Integer>(Arrays.asList(new Integer[]{1,2}));
		vec2d.add(vec);
		vec = new LinkedList<Integer>(Arrays.asList(new Integer[]{3}));
		vec2d.add(vec);
		vec = new LinkedList<Integer>(Arrays.asList(new Integer[]{4,5,6}));
		vec2d.add(vec);
		xMeetingRoomsII solution = new xMeetingRoomsII();

		System.out.println(solution.minMeetingRooms(new Interval[]{new Interval(0,30),new Interval(5,10),new Interval(15,20)}));
	}

}
