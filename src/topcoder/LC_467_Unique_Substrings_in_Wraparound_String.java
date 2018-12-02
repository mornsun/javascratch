/**
 * 
 */
package topcoder;

import java.util.Arrays;

/**
 * 	Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s. In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.
 Note: p consists of only lowercase English letters and the size of p might be over 10000.
 Example 1:
 Input: "a"
 Output: 1

 Explanation: Only the substring "a" of string "a" is in the string s.

 Example 2:
 Input: "cac"
 Output: 2
 Explanation: There are two substrings "a", "c" of string "cac" in the string s.

 Example 3:
 Input: "zab"
 Output: 6
 Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.

 Related Topics:
 Dynamic Programming

 * @author Chauncey
 * beat 35.79%
 */
public class LC_467_Unique_Substrings_in_Wraparound_String
{
	public int findSubstringInWraproundString(String p) {
		if (p==null || p.length()==0)
			return 0;
		int sum = 1;
		int last = 0;
		int[] max = new int[26];
		max[p.charAt(0)-'a'] = 1;
		for (int i=1; i<p.length(); ++i) {
			char curr = p.charAt(i);
			char prev = p.charAt(i-1);
			if (curr == prev+1 || (curr=='a' && prev=='z')) {
				last += 1;
			} else
				last = 0;
			int ma = Math.max(last+1, max[curr-'a']);
			sum += ma-max[curr-'a'];
			max[curr-'a'] = ma;
		}

		return sum;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_467_Unique_Substrings_in_Wraparound_String solution = new LC_467_Unique_Substrings_in_Wraparound_String();
		System.out.println(solution.findSubstringInWraproundString("a")); //1
		System.out.println(solution.findSubstringInWraproundString("cac")); //2
		System.out.println(solution.findSubstringInWraproundString("zab")); //6

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
