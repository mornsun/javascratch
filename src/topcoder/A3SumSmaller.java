package topcoder;

import java.util.*;

/**
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target. 

For example, given nums = [-2, 0, 1, 3], and target = 2. 

Return 2. Because there are two triplets which sums are less than 2: 

[-2, 0, 1] 
[-2, 0, 3] 
Follow up: 
Could you solve it in O(n2) runtime? 

 * @author Chauncey
 *
 */
public class A3SumSmaller
{
    public static int threeSumSmaller(int[] nums, int target) {
    	if (nums == null || nums.length==0) return 0;
    	Arrays.sort(nums);
    	int res = 0;
    	for (int i=0; i<nums.length-2; ++i) {
    		int pvt = target-nums[i];
    		if (nums[i+1]+nums[i+2] >= pvt) break;
    		int lo = i+1;
    		int hi = nums.length-1;
    		while (lo<hi) {
    			if (nums[lo]+nums[hi] < pvt) {
    				res += hi-lo;
    				++lo;
    			} else {
    				--hi;
    			}
    		}
    	}
    	return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println(threeSumSmaller(new int[]{-2, 0, 1, 2, 3}, 2));
	}

}
