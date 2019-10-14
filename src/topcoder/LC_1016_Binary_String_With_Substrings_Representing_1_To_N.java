/**
 * 
 */
package topcoder;

/**
 * 	Given a binary string S (a string consisting only of '0' and '1's) and a positive integer N, return true if and only if for every integer X from 1 to N, the binary representation of X is a substring of S.
 *
 *
 *
 * Example 1:
 *
 * Input: S = "0110", N = 3
 * Output: true
 * Example 2:
 *
 * Input: S = "0110", N = 4
 * Output: false
 *
 *
 * Note:
 *
 * 1 <= S.length <= 1000
 * 1 <= N <= 10^9
 *
 * String
 *
 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary String With Substrings Representing 1 To N.
 * Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Binary String With Substrings Representing 1 To N.
 */
public class LC_1016_Binary_String_With_Substrings_Representing_1_To_N
{
	public boolean queryString(String S, int N) {

		if (S==null || S.length()==0 || N<=0 || N>S.length()*2) return false;
		for (int i=N; i>N/2; --i)
			if (!S.contains(Integer.toBinaryString(i))) return false;
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1016_Binary_String_With_Substrings_Representing_1_To_N solution = new LC_1016_Binary_String_With_Substrings_Representing_1_To_N();
		//System.out.println(solution.queryString(new int[]{7,7,7,8,5,7,5,5,5,8,8}));

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
