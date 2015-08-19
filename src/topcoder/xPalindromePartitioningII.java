/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

Hide Tags Dynamic Programming
Hide Similar Problems (M) Palindrome Partitioning

 * @author Chauncey
 *
 */
public class xPalindromePartitioningII
{
    // dp[i,j] = s[i]==s[j] && (dp[i+1][j-1] || j-i<1)
    public static int minCut(String s) {
    	final int len = s.length();
    	final boolean[][] is = new boolean [len][len];
    	for (int i=len-1; i>=0; --i) {
    		is[i][i] = true;
    		for (int j=i+1; j<len; ++j) {
    			is[i][j] = (s.charAt(i) == s.charAt(j)) && (j-i<=2 || is[i+1][j-1]);
    		}
    	}
    	//dp[j] = min(dp[j] && is[i][j-1] => dp[i]+1)
    	int[] dp = new int[len+1];
    	dp[0] = 0;
    	for (int i=1; i<dp.length; ++i) {
    		dp[i] = Integer.MAX_VALUE;
    	}
    	for (int i=0; i<len; ++i) {
    		if (dp[i] == Integer.MAX_VALUE)
    			continue;
    		for (int j=i+1; j<=len; ++j) {
    			if (is[i][j-1] && dp[i]+1<dp[j])
    				dp[j] = dp[i] + 1;
    		}
    	}
    	return dp[len]-1;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] nums = new int[]{-2,1};
		System.out.println(minCut("aab"));
	}

}
