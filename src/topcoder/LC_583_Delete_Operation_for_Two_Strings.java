/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.List;

/**
 * 	Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.
 *
 * Example 1:
 * Input: "sea", "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 * Note:
 * The length of given words won't exceed 500.
 * Characters in given words can only be lower-case letters.
 *
 * DP
 *
 * @author Chauncey
 * Runtime: 5 ms, faster than 92.80% of Java online submissions for Delete Operation for Two Strings.
 * Memory Usage: 36 MB, less than 100.00% of Java online submissions for Delete Operation for Two Strings.
 */
public class LC_583_Delete_Operation_for_Two_Strings
{
	public int minDistance(String word1, String word2) {
		if (word1==null || word2==null) return 0;
		int l1 = word1.length();
		int l2 = word2.length();
		if (l1==0) return l2;
		if (l2==0) return l1;
		int dp[][] = new int[l1+1][l2+1];
		for (int i=1; i<=l1; ++i) {
			for (int j=1; j<=l2; ++j) {
				if (word1.charAt(i-1)==word2.charAt(j-1))
					dp[i][j] = dp[i-1][j-1]+1;
				else
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
			}
		}

		return l1-dp[l1][l2]+l2-dp[l1][l2];
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_583_Delete_Operation_for_Two_Strings solution = new LC_583_Delete_Operation_for_Two_Strings();
        System.out.println(solution.minDistance("tea", "eat")); //2
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
