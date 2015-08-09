/**
 * 
 */
package topcoder;

import java.util.*;

/**
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
