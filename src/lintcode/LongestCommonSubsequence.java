package lintcode;

import java.util.*;

/**
 * Given two strings, find the longest common subsequence (LCS).

Your code should return the length of LCS.

Have you met this question in a real interview? Yes
Example
For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.

For "ABCD" and "EACB", the LCS is "AC", return 2.

Clarification
What's the definition of Longest Common Subsequence?

https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
http://baike.baidu.com/view/2020307.htm
Tags Expand 
LintCode Copyright Longest Common Subsequence Dynamic Programming


Related Problems Expand 
Medium Edit Distance 29 %
Medium Longest Common Substring 29 %

 * @author Chauncey
 *
 */
public class LongestCommonSubsequence
{
    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    public int longestCommonSubsequence(String A, String B) {
    	if (null==A || A.length()==0 || null==B || B.length()==0)
    		return 0;
    	int alen = A.length();
    	int blen = B.length();
    	int[][] f = new int[alen+1][blen+1]; //can be tuned with scroll array
    	for (int i=1; i<=alen; ++i) {
    		for (int j=1; j<=blen; ++j) {
    			f[i][j] = A.charAt(i-1)==B.charAt(j-1) ? f[i-1][j-1]+1 : Math.max(f[i-1][j], f[i][j-1]);
    		}
    	}
    	return f[alen][blen];
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		LongestCommonSubsequence solution = new LongestCommonSubsequence();

		System.out.println(solution.longestCommonSubsequence("ABCD", "EDCA"));
		System.out.println(solution.longestCommonSubsequence("ABCD", "EACB"));
	}

}
