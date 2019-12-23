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
 * Runtime: 1 ms, faster than 99.07% of Java online submissions for Insert Interval.
 * Memory Usage: 39.9 MB, less than 90.63% of Java online submissions for Insert Interval.
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

	public int[][] insert(int[][] intervals, int[] newInterval) {
		int lo=0, hi=intervals.length, target=newInterval[1];
		while (lo<hi) {
			int m=lo+(hi-lo>>>1);
			if (target>=intervals[m][0])
				lo = m+1;
			else
				hi = m;
		}
		lo--;
		while (lo>=0 && intervals[lo][1]>=newInterval[0]) {
			newInterval[0] = Math.min(intervals[lo][0], newInterval[0]);
			newInterval[1] = Math.max(intervals[lo][1], newInterval[1]);
			lo--;
		}
		int[][] res = new int[lo+2+intervals.length-hi][];
		System.arraycopy(intervals, 0, res, 0, lo+1);
		res[lo+1] = newInterval;
		System.arraycopy(intervals, hi, res, lo+2, intervals.length-hi);
		return res;
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

		long startTime = System.currentTimeMillis();
		InsertInterval solution = new InsertInterval();
		System.out.println(Arrays.deepToString(solution.insert(new int[][]{{1,3},{6,9}}, new int[]{2,5}))); //{{1,5},{6,9}}
		System.out.println(Arrays.deepToString(solution.insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[]{4,8}))); //{{1,2},{3,10},{12,16}}
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
