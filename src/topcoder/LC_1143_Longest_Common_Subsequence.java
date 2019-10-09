/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 	Given two strings text1 and text2, return the length of their longest common subsequence.
 *
 * A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * If there is no common subsequence, return 0.
 *
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 * Constraints:
 *
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * The input strings consist of lowercase English characters only.
 *
 * @author Chauncey
 * Runtime: 7 ms, faster than 87.95% of Java online submissions for Longest Common Subsequence.
 * Memory Usage: 36.2 MB, less than 100.00% of Java online submissions for Longest Common Subsequence.
 */
public class LC_1143_Longest_Common_Subsequence
{
	public int longestCommonSubsequence(String text1, String text2) {
		if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0)
			return 0;

		int n1 = text1.length(), n2 = text2.length();
		int[][] dp = new int[n1 + 1][n2 + 1];

		for (int i = 1; i <= n1; ++i) {
			for (int j = 1; j <= n2; ++j) {
				if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[n1][n2];
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1143_Longest_Common_Subsequence solution = new LC_1143_Longest_Common_Subsequence();
        System.out.println(solution.longestCommonSubsequence("abcde", "ace")); //3
		System.out.println(solution.longestCommonSubsequence("abc", "abc")); //3
		System.out.println(solution.longestCommonSubsequence("abc", "def")); //0
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
