/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

Hide Tags Divide and Conquer Array Dynamic Programming
Hide Similar Problems (M) Best Time to Buy and Sell Stock (M) Maximum Product Subarray

 * @author Chauncey
 *
 */
public class MaximumSubarray
{
    public static int maxSubArray(int[] nums) {
    	if (null == nums) return 0;
        int dp=0, max=Integer.MIN_VALUE;
        for (int i=0; i<nums.length; ++i) {
        	dp += nums[i];
        	if (dp>max) {
        		max = dp;
        	}
        	if (dp<0) {
        		dp = 0;
        	}
        }
        return max;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] nums = new int[]{-2,1};
		System.out.println(maxSubArray(nums));
	}

}
