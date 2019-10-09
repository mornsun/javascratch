/**
 * 
 */
package topcoder;

import java.util.HashMap;

/**
 * 	Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4], difference = 1
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [1,2,3,4].
 *
 * Example 2:
 *
 * Input: arr = [1,3,5,7], difference = 1
 * Output: 1
 * Explanation: The longest arithmetic subsequence is any single element.
 *
 * Example 3:
 *
 * Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [7,5,3,1].
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * -10^4 <= arr[i], difference <= 10^4
 *
 * @author Chauncey
 * Runtime: 52 ms, faster than 54.29% of Java online submissions for Longest Arithmetic Subsequence of Given Difference.
 * Memory Usage: 53.5 MB, less than 100.00% of Java online submissions for Longest Arithmetic Subsequence of Given Difference.
 */
public class LC_1218_Longest_Arithmetic_Subsequence_of_Given_Difference
{
	public int longestSubsequence(int[] arr, int difference) {
		if (arr==null || arr.length==0)
			return 0;

		HashMap<Integer, Integer> dp = new HashMap<>();
		for (int a : arr) {
			Integer val = dp.get(a-difference);
			int v = val==null ? 1:val+1;
			Integer bval = dp.get(a);
			int b = bval==null ? 0:bval;
			dp.put(a, Math.max(b, v));
			if (v>b) {
				dp.put(a, v);
			}
		}

		int max = 0;
		for (int v : dp.values()) {
			if (v>max) max = v;
		}
		return max;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1218_Longest_Arithmetic_Subsequence_of_Given_Difference solution = new LC_1218_Longest_Arithmetic_Subsequence_of_Given_Difference();
        System.out.println(solution.longestSubsequence(new int[]{1,2,3,4}, 1)); //4
		System.out.println(solution.longestSubsequence(new int[]{1,3,5,7}, 1)); //1
		System.out.println(solution.longestSubsequence(new int[]{1,5,7,8,5,3,4,2,1}, -2)); //4
		System.out.println(solution.longestSubsequence(new int[]{2,-6,-3,-6,2,0}, -2)); //2
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}
}
