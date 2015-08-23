package topcoder;

import java.util.*;

/**
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)

Hide Tags Array
Hide Similar Problems (H) Trapping Rain Water (M) Maximum Product Subarray (H) Paint House II

 * @author Chauncey
 *
 */
public class ProductofArrayExceptSelf
{
    public int[] productExceptSelf(int[] nums) {
    	if (null == nums || nums.length == 0) return null;
        int[] output = new int[nums.length];
        output[0] = 1;
        for (int i=0; i<nums.length-1; ++i) {
        	output[i+1] = output[i] * nums[i];
        }
        int multi = nums[nums.length-1];
        for (int i=nums.length-2; i>=0; --i) {
        	output[i] = multi * output[i];
        	multi = multi * nums[i];
        }
        return output;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ProductofArrayExceptSelf solution = new ProductofArrayExceptSelf();
		
		int[] nums = solution.productExceptSelf(new int[]{1,2,3,4});
		for (int num: nums) {
			System.out.println(num);
		}
	}

}
