package topcoder;

import java.util.*;

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.

Hide Tags Array Two Pointers Binary Search
Hide Similar Problems (H) Minimum Window Substring

 *
 */
public class MinimumSizeSubarraySum
{
    public int minSubArrayLen(int s, int[] nums) {
        if (s<=0 || nums==null || nums.length==0) return 0;
        int l = 0, r = 0;
        int minwin = Integer.MAX_VALUE;
        int sum = 0;
        while (l<=r) {
        	if (sum < s) {
        		if (r>=nums.length) break;
        		sum += nums[r++];
        	} else {
        		if (r-l < minwin) {
        			//System.out.println(l+":"+r);
        			minwin = r-l;
        		}
        		sum -= nums[l++];
        	}
        }
        return minwin == Integer.MAX_VALUE ? 0 : minwin;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		MinimumSizeSubarraySum solution = new MinimumSizeSubarraySum();
		System.out.println(solution.minSubArrayLen(4, new int[]{1,2,3,4}));
	}

}
