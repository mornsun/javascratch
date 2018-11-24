/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a string S, count the number of distinct, non-empty subsequences of S .
 Since the result may be large, return the answer modulo 10^9 + 7.

 Example 1:
 Input: "abc"
 Output: 7
 Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
 Example 2:
 Input: "aba"
 Output: 6
 Explanation: The 6 distinct subsequences are "a", "b", "ab", "ba", "aa" and "aba".
 Example 3:
 Input: "aaa"
 Output: 3
 Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".

 Note:
 S contains only lowercase letters.
 1 <= S.length <= 2000

 Related Topics:
 Dynamic Programming

 * @author Chauncey
 * beat %
 */
public class LC_940_Distinct_Subsequences_II
{
    public int distinctSubseqII(String S) {
        if (S==null || S.length()==0)
            return 0;
        int MOD = 1_000_000_007;
        return 0;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_940_Distinct_Subsequences_II solution = new LC_940_Distinct_Subsequences_II();
		//System.out.println(solution.knightDialer(1)); //10

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
