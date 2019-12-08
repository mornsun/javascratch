/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
 *
 * Example:
 *
 * nums = [1, 2, 3]
 * target = 4
 *
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 *
 * Note that different sequences are counted as different combinations.
 * True combination pls. check: xLC_518_Coin_Change_2
 *
 * Therefore the output is 7.
 *
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 *
 *
 * Credits:
 * Special thanks to @pbrother for adding this problem and creating all test cases.
 *
 * @author Chauncey
 * Runtime: 2 ms, faster than 23.39% of Java online submissions for Combination Sum IV.
 * Memory Usage: 33.9 MB, less than 100.00% of Java online submissions for Combination Sum IV.
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Combination Sum IV.
 * Memory Usage: 34.4 MB, less than 100.00% of Java online submissions for Combination Sum IV.
 */
public class xLC_377_Combination_Sum_IV
{
	public int combinationSum4_1(int[] nums, int target) {
		if (nums==null || nums.length==0) return target==0 ? 1 : 0;
		int[] dp = new int[target+1];
		dp[0] = 1;
		for (int i=1; i<dp.length; ++i) {
			for (int j=0; j<nums.length; ++j) {
				int t = i-nums[j];
				if (t<0) continue;
				dp[i] += dp[i-nums[j]];
			}
		}
		return dp[target];
	}

	public int combinationSum4(int[] nums, int target) {
		int[] dp = new int[target + 1];
		Arrays.fill(dp, -1);
		dp[0] = 1;
		return helper(nums, target, dp);
	}

	private int helper(int[] nums, int target, int[] dp) {
		if (dp[target] != -1) {
			return dp[target];
		}
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			if (target >= nums[i]) {
				res += helper(nums, target - nums[i], dp);
			}
		}
		dp[target] = res;
		return res;
	}

		/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_377_Combination_Sum_IV solution = new xLC_377_Combination_Sum_IV();
        System.out.println(solution.combinationSum4(new int[]{1,2,3}, 4)); //7
		System.out.println(solution.combinationSum4(new int[]{3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25}, 10)); //9
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
