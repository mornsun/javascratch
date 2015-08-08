/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author cherry
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
