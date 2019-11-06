package topcoder;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.
 *
 * Example 1:
 *
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 * Example 2:
 *
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 *
 * Note:
 *
 * The length of the array won't exceed 10,000.
 * You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 *
 * DP, Math

 * @author Chauncey
 * Runtime: 2 ms, faster than 99.92% of Java online submissions for Continuous Subarray Sum.
 * Memory Usage: 39 MB, less than 100.00% of Java online submissions for Continuous Subarray Sum.
 *
 */
public class xLC_523_Continuous_Subarray_Sum
{
	public boolean checkSubarraySum(int[] nums, int k) {
		if (k==0) {
			for (int i = 0; i < nums.length - 1; ++i) {
				if (nums[i] == 0)
					if (nums[i + 1] == 0) return true;
					else ++i;
			}
			return false;
		}

		HashMap<Integer, Integer> reminders = new HashMap<>();
		int sum = 0;
		for (int i=0; i<nums.length; ++i) {
			sum += nums[i];
			sum %= k;
			if (sum==0 && i>0) return true;
			Integer prev = reminders.get(sum);
			if (prev != null) {
				if (i-prev>1) return true;
			}
			else reminders.put(sum, i);
		}
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_523_Continuous_Subarray_Sum solution = new xLC_523_Continuous_Subarray_Sum();
		System.out.println(solution.checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6)); //True
		System.out.println(solution.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6)); //True
		System.out.println(solution.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 26)); //False
		System.out.println(solution.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 0)); //False
		System.out.println(solution.checkSubarraySum(new int[]{0}, 0)); //False
		System.out.println(solution.checkSubarraySum(new int[]{0, 0}, 0)); //True
		System.out.println(solution.checkSubarraySum(new int[]{0, 0}, -1)); //True
		System.out.println(solution.checkSubarraySum(new int[]{0, 1}, -1)); //True
		System.out.println(solution.checkSubarraySum(new int[]{23, 2, 4, 6, 7}, -6)); //True
		System.out.println(solution.checkSubarraySum(new int[]{5, 0, 0}, 0)); //True
		System.out.println(solution.checkSubarraySum(new int[]{1, 0, 1, 0, 1}, 4)); //False

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
