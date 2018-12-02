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
 dp[k] = 2 * dp[k-1] - dp[last[S[k]] - 1]

 * @author Chauncey
 * beat 67.89%
 */
public class LC_940_Distinct_Subsequences_II
{
    public int distinctSubseqII(String S) {
        if (S==null || S.length()==0)
            return 0;
        int MOD = 1_000_000_007;

		int sum = 2;
		int[] max = new int[26];
		max[S.charAt(0)-'a'] = 1;
		for (int i=1; i<S.length(); ++i) {
			int curr = S.charAt(i)-'a';
			int ma = (sum*2)%MOD - max[curr];
			if (ma<0) ma += MOD;
			//System.out.println(ma+":"+sum+":"+max[curr]);
			max[curr] = sum;
			sum = ma;
		}
		int ret = sum-1;
        return ret < 0 ? ret+MOD : ret;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_940_Distinct_Subsequences_II solution = new LC_940_Distinct_Subsequences_II();
		System.out.println(solution.distinctSubseqII("a")); //1
		System.out.println(solution.distinctSubseqII("ab")); //3
		System.out.println(solution.distinctSubseqII("abc")); //7 ["a", "b", "c", "ab", "ac", "bc", "abc"]
		System.out.println(solution.distinctSubseqII("aba")); //6 ["a", "b",      "ab", "ba", "aa", "aba"]
		System.out.println(solution.distinctSubseqII("aaa")); //3 ["a", "aa", "aaa"]
		System.out.println(((-1)%5)); //3 ["a", "aa", "aaa"]
		System.out.println(solution.distinctSubseqII("zchmliaqdgvwncfatcfivphddpzjkgyygueikthqzyeeiebczqbqhdytkoawkehkbizdmcnilcjjlpoeoqqoqpswtqdpvszfaksn")); //97915677

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
