/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author cherry
 *
 */
public class MergeIntervals
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
    public static List<Interval> merge(List<Interval> intervals) {
    	Interval last = null;
    	Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval arg0, Interval arg1) {
                if (arg0.start == arg1.start) return 0;
                else if (arg0.start < arg1.start) return -1;
                else return 1;
            }
        });
    	List<Interval> list = new ArrayList<Interval>(intervals.size());
    	for (Interval interval : intervals) {
    		if (last == null) {
    			last = new Interval(interval.start, interval.end);
    		} else {
    			if (last.end >= interval.start) {
    				if (interval.end > last.end) last.end = interval.end;
    			} else {
    				list.add(last);
    				last = new Interval(interval.start, interval.end);
    			}
    		}
    	}
    	if (last != null) list.add(last);
        return list;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
    	List<Interval> intervals = new ArrayList<Interval>();
    	intervals.add(new Interval(2, 6));
    	intervals.add(new Interval(1, 3));
    	intervals.add(new Interval(8, 10));
    	intervals.add(new Interval(15, 18));
    	System.out.println(merge(intervals));
	}

}
