/**
 * 
 */
package topcoder;

/**
 * 	You are given a string containing only 4 kinds of characters 'Q', 'W', 'E' and 'R'.
 *
 * A string is said to be balanced if each of its characters appears n/4 times where n is the length of the string.
 *
 * Return the minimum length of the substring that can be replaced with any other string of the same length to make the original string s balanced.
 *
 * Return 0 if the string is already balanced.
 *
 * Example 1:
 *
 * Input: s = "QWER"
 * Output: 0
 * Explanation: s is already balanced.
 * Example 2:
 *
 * Input: s = "QQWE"
 * Output: 1
 * Explanation: We need to replace a 'Q' to 'R', so that "RQWE" (or "QRWE") is balanced.
 * Example 3:
 *
 * Input: s = "QQQW"
 * Output: 2
 * Explanation: We can replace the first "QQ" to "ER".
 * Example 4:
 *
 * Input: s = "QQQQ"
 * Output: 3
 * Explanation: We can replace the last 3 'Q' to make s = "QWER".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s.length is a multiple of 4
 * s contains only 'Q', 'W', 'E' and 'R'.
 *
 * Two Pointers, String
 *
 * @author Chauncey
 * Runtime: 10 ms, faster than 86.30% of Java online submissions for Replace the Substring for Balanced String.
 * Memory Usage: 36.2 MB, less than 100.00% of Java online submissions for Replace the Substring for Balanced String.
 */
public class LC_1234_Replace_the_Substring_for_Balanced_String
{
	public int balancedString(String s) {
		if (s==null || s.length()==0) return 0;
		int n = s.length();
		int bar = n/4, hi = n;
		int[] cnts = new int[26];
		while (--hi != -1) {
			int k = s.charAt(hi) - 'A';
			if (cnts[k] == bar) break;
			cnts[k]++;
		}
		hi++;
		//System.out.println("hi="+hi);
		int min = hi, lo = 0;
		while (hi<n) {
			int k = s.charAt(hi) - 'A';
			cnts[k]--;
			while(lo<hi) {
				k = s.charAt(lo) - 'A';
				if (cnts[k]==bar) break;
				cnts[k]++;
				lo++;
			}
			hi++;
			min = Math.min(min, hi-lo);
		}
		return min;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1234_Replace_the_Substring_for_Balanced_String solution = new LC_1234_Replace_the_Substring_for_Balanced_String();
        System.out.println(solution.balancedString("QWER")); //0
		System.out.println(solution.balancedString("QQWE")); //1
		System.out.println(solution.balancedString("QQQW")); //2
		System.out.println(solution.balancedString("QQQQ")); //3
		System.out.println(solution.balancedString("WQWRQQQW")); //3
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
