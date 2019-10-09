/**
 * 
 */
package topcoder;

/**
 * 	You are given two strings s and t of the same length. You want to change s to t. Changing the i-th character of s to i-th character of t costs |s[i] - t[i]| that is, the absolute difference between the ASCII values of the characters.
 *
 * You are also given an integer maxCost.
 *
 * Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of twith a cost less than or equal to maxCost.
 *
 * If there is no substring from s that can be changed to its corresponding substring from t, return 0.
 *
 * Example 1:
 *
 * Input: s = "abcd", t = "bcdf", maxCost = 3
 * Output: 3
 * Explanation: "abc" of s can change to "bcd". That costs 3, so the maximum length is 3.
 *
 * Example 2:
 *
 * Input: s = "abcd", t = "cdef", maxCost = 3
 * Output: 1
 * Explanation: Each character in s costs 2 to change to charactor in t, so the maximum length is 1.
 *
 * Example 3:
 *
 * Input: s = "abcd", t = "acde", maxCost = 0
 * Output: 1
 * Explanation: You can't make any change, so the maximum length is 1.
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 10^5
 * 0 <= maxCost <= 10^6
 * s and t only contain lower case English letters.
 *
 * @author Chauncey
 * Runtime: 6 ms, faster than 82.18% of Java online submissions for Get Equal Substrings Within Budget.
 * Memory Usage: 37.1 MB, less than 100.00% of Java online submissions for Get Equal Substrings Within Budget.
 */
public class LC_1208_Get_Equal_Substrings_Within_Budget
{
	public int equalSubstring(String s, String t, int maxCost) {

		if (s==null || t==null || maxCost<0)
			return 0;
		int n=s.length();
		if (t.length()!=n)
			return 0;

		int cost = 0;
		int b = -1;
		int max = 0;
		for (int i=0; i<n; ++i) {
			cost += Math.abs(t.charAt(i)-s.charAt(i));
			if (cost<=maxCost) {
				max = Math.max(max, i-b);
			} else {
				++b;
				cost -= Math.abs(t.charAt(b)-s.charAt(b));
			}
		}
		return max;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1208_Get_Equal_Substrings_Within_Budget solution = new LC_1208_Get_Equal_Substrings_Within_Budget();
        System.out.println(solution.equalSubstring("abcd", "bcdf", 3)); //3
		System.out.println(solution.equalSubstring("abcd", "cdef", 3)); //1
		System.out.println(solution.equalSubstring("abcd", "acde", 0)); //0
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
