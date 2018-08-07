package lintcode;

import java.util.*;

/**
 * Given two strings, find the longest common substring.

Return the length of it.

Have you met this question in a real interview? Yes
Example
Given A = "ABCD", B = "CBCE", return 2.

Note
The characters in substring should occur continuously in original string. This is different with subsequence.

Challenge
O(n x m) time and memory.

Tags Expand 
LintCode Copyright Longest Common Subsequence Dynamic Programming


Related Problems Expand 
Medium Longest Common Subsequence 37 %

 * @author Chauncey
 *
 */
public class LongestCommonSubstring
{
    /**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        if (null==A || A.length()==0 || null==B || B.length()==0)
            return 0;
        int alen = A.length();
        int blen = B.length();
        int max = 0;
        int[][] local = new int[alen+1][blen+1]; //can be tuned with scroll array
        for (int i=1; i<=alen; ++i) {
            for (int j=1; j<=blen; ++j) {
                local[i][j] = A.charAt(i-1)==B.charAt(j-1) ? local[i-1][j-1]+1 : 0;
                max = Math.max(max, local[i][j]);
            }
        }
        return max;
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        LongestCommonSubstring solution = new LongestCommonSubstring();

        System.out.println(solution.longestCommonSubstring("ABCD", "CBCE"));
    }

}
