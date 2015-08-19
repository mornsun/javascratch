/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

Hide Tags Array Sort
Hide Similar Problems (H) Merge Intervals

 * @author Chauncey
 *
 */
public class InsertInterval
{
	public static class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
		
		public String toString() {
			return new String("["+start+","+end+"]");
		}
	}
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    	List<Interval> list = new ArrayList<Interval>(intervals.size());
    	//TODO: if intervals is an array (or array analog list), we can use binary search here
    	for (Interval interval : intervals) {
    		if (newInterval != null) {
	    		if (interval.end >= newInterval.start && interval.start <= newInterval.end) {
	    			newInterval.start = interval.start < newInterval.start ? interval.start : newInterval.start;
	    			newInterval.end = interval.end > newInterval.end ? interval.end : newInterval.end;
	    		} else if (interval.start > newInterval.end) {
	    			list.add(newInterval);
	    			list.add(interval);
	    			newInterval = null;
	    		} else {
	    			list.add(interval);
	    		}
    		} else {
    			list.add(interval);
    		}
    	}
    	if (newInterval != null) list.add(newInterval);
        return list;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
    	List<Interval> intervals = new ArrayList<Interval>();
    	intervals.add(new Interval(1, 3));
    	intervals.add(new Interval(6, 9));
    	intervals.add(new Interval(15, 18));
    	System.out.println(insert(intervals, new Interval(2, 5)));
	}

}
