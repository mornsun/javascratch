/**
 * 
 */
package topcoder;

/**
 * 	Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 * Input:
 *
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 * Input:
 *
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb".
 *
 * DP
 *
 * @author Chauncey
 * Runtime: 22 ms, faster than 74.32% of Java online submissions for Longest Palindromic Subsequence.
 * Memory Usage: 48.1 MB, less than 5.55% of Java online submissions for Longest Palindromic Subsequence.
 */
public class xLC_516_Longest_Palindromic_Subsequence
{

	public int longestPalindromeSubseq(String s) {
		if (s==null || s.length()==0) return 0;
		int n = s.length();
		int[][] dp = new int[n][n];

		for (int i = n - 1; i >= 0; i--) {
			dp[i][i] = 1;
			for (int j = i+1; j < n; j++) {
				if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = dp[i+1][j-1] + 2;
				} else {
					dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
				}
			}
		}
		return dp[0][n-1];
	}

	public int longestPalindromeSubseq1(String s) {

		if (s==null || s.length()==0) return 0;
		int n = s.length();
		int[][] dp = new int[n+1][n+1];
		for (int i=1; i<=n; ++i) {
			char ch = s.charAt(i-1);
			dp[i][i-1] = 1;
			for (int j=i-2; j>=0; --j) {
				dp[i][j] = Math.max(dp[i][j+1], dp[i-1][j+1]+(s.charAt(j)==ch ? 2 : 0));
				dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
			}
		}
		return dp[n][0];
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_516_Longest_Palindromic_Subsequence solution = new xLC_516_Longest_Palindromic_Subsequence();
        System.out.println(solution.longestPalindromeSubseq("bbbab")); //4
		System.out.println(solution.longestPalindromeSubseq("cbbd")); //2
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
