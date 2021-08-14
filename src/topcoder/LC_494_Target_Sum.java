/**
 * 
 */
package topcoder;

import java.util.HashMap;

/**
 * You are given an integer array nums and an integer target.
 *
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
 *
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
 * Return the number of different expressions that you can build, which evaluates to target.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,1,1], target = 3
 * Output: 5
 * Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * Example 2:
 *
 * Input: nums = [1], target = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000

 * @author Chauncey
 * Runtime: 52 ms, faster than 52.04% of Java online submissions for Target Sum.
 * Memory Usage: 38.9 MB, less than 32.58% of Java online submissions for Target Sum.
 */
public class LC_494_Target_Sum
{
	public int findTargetSumWays(int[] nums, int target) {
		int n = nums.length;
		HashMap<Integer, Integer> curr = new HashMap<>();
		curr.put(0, 1);
		for (int i=0; i<n; ++i) {
			HashMap<Integer, Integer> prev = curr;
			curr = new HashMap<>();
			for (HashMap.Entry<Integer, Integer> sum : prev.entrySet()) {
				int k = sum.getKey()+nums[i];
				curr.put(k, curr.getOrDefault(k, 0) + sum.getValue());
				k = sum.getKey()-nums[i];
				curr.put(k, curr.getOrDefault(k, 0) + sum.getValue());
			}
		}
		return curr.getOrDefault(target, 0);
	}

	public int findTargetSumWays1(int[] nums, int target) {
		int n = nums.length;
		return helper(nums, target, 0);
	}

	private int helper(int[] nums, int target, int idx) {
		if (idx==nums.length) {
			if (target==0)
				return 1;
			else
				return 0;
		}
		return helper(nums, target+nums[idx], idx+1) + helper(nums, target-nums[idx], idx+1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_494_Target_Sum solution = new LC_494_Target_Sum();
		System.out.println(solution.findTargetSumWays(new int[]{1}, 1)); //1
		System.out.println(solution.findTargetSumWays(new int[]{1,1,1,1,1}, 3)); //5
		
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
