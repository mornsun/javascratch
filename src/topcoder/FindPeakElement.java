package topcoder;

import java.util.*;

/**
 * A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

click to show spoilers.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.

Hide Tags Array Binary Search

 *
 */
public class FindPeakElement
{
    public int findPeakElement(int[] nums) {
    	if (nums == null || nums.length==0) return 0;
    	if (nums.length == 1) return 0;
    	int lo = 0;
    	int hi = nums.length-1;
    	while (lo < hi) {
    		int m = lo + ((hi-lo)>>1);
    		long lv = m==0 ? Long.MIN_VALUE : nums[m-1];
    		long rv = m==nums.length-1 ? Long.MIN_VALUE : nums[m+1];
    		if (nums[m] > lv && nums[m] > rv) {
    			return m;
    		} else if (lv < nums[m] && nums[m] < rv) {
    			lo = m + 1;
    		} else {
    			hi = m - 1;
    		}
    	}
        return lo;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		FindPeakElement solution = new FindPeakElement();
		System.out.println(solution.findPeakElement(new int[]{3,2,1}));
	}

}
