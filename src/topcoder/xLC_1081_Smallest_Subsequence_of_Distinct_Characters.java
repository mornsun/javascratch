/**
 * 
 */
package topcoder;

import java.util.LinkedList;

/**
 * 	Return the lexicographically smallest subsequence of text that contains all the distinct characters of text exactly once.
 *
 * Example 1:
 *
 * Input: "cdadabcc"
 * Output: "adbc"
 *
 * Example 2:
 *
 * Input: "abcd"
 * Output: "abcd"
 *
 * Example 3:
 *
 * Input: "ecbacba"
 * Output: "eacb"
 *
 * Example 4:
 *
 * Input: "leetcode"
 * Output: "letcod"
 *
 * Note:
 *
 * 1 <= text.length <= 1000
 * text consists of lowercase English letters.
 *
 * @author Chauncey
 * Runtime: 2 ms, faster than 90.92% of Java online submissions for Smallest Subsequence of Distinct Characters.
 * Memory Usage: 34.6 MB, less than 100.00% of Java online submissions for Smallest Subsequence of Distinct Characters.
 */
public class xLC_1081_Smallest_Subsequence_of_Distinct_Characters
{
	public String smallestSubsequence(String text) {
		if (text==null || text.length()==0)
			return "";

		int n = text.length();
		int[] last = new int[26];
		for (int i=0; i<n; ++i) {
			int ich = text.charAt(i) - 'a';
			last[ich] = i;
		}

		LinkedList<Integer> stack = new LinkedList<>();
		boolean[] got = new boolean[26];
		for (int i=0; i<n; ++i) {
			int ich = text.charAt(i) - 'a';
			if (got[ich]) continue;
			while (!stack.isEmpty() && stack.peek()>ich && i<last[stack.peek()]) {
				int del = stack.pop();
				got[del] = false;
			}
			stack.push(ich);
			got[ich] = true;
		}

		StringBuilder res = new StringBuilder();
		while (!stack.isEmpty()) res.append((char)(stack.pollLast()+'a'));
		return res.toString();
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_1081_Smallest_Subsequence_of_Distinct_Characters solution = new xLC_1081_Smallest_Subsequence_of_Distinct_Characters();
        System.out.println(solution.smallestSubsequence("cdadabcc")); //adbc
		System.out.println(solution.smallestSubsequence("abcd")); //abcd
		System.out.println(solution.smallestSubsequence("ecbacba")); //eacb
		System.out.println(solution.smallestSubsequence("leetcode")); //letcod
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
