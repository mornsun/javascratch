package topcoder;

import java.util.Arrays;

/**
 * Given a list of strings, you need to find the longest uncommon subsequence among them. The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.
 *
 * A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.
 *
 * The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence. If the longest uncommon subsequence doesn't exist, return -1.
 *
 * Example 1:
 * Input: "aba", "cdc", "eae"
 * Output: 3
 * Note:
 *
 * String

 * @author Chauncey
 * Runtime: 3 ms, faster than 36.70% of Java online submissions for Longest Uncommon Subsequence II.
 * Memory Usage: 34.7 MB, less than 100.00% of Java online submissions for Longest Uncommon Subsequence II.
 *
 */
public class xLC_522_Longest_Uncommon_Subsequence_II
{
	public int findLUSlength(String[] strs) {
		if (strs==null || strs.length==0) return -1;
		Arrays.sort(strs, (o1, o2) -> o2.length()-o1.length());
		for (int i=0; i<strs.length; ++i) {
			String s1 = strs[i];
			boolean flag = true;
			for (int j=0; j<strs.length; ++j) {
				if (i==j)
					continue;
				String s2 = strs[j];
				if (s2.length()<s1.length())
					break;
				int idx=0;
				for (char ch : s2.toCharArray()) {
					if (idx==s1.length())
						break;
					if (ch==s1.charAt(idx))
						idx++;
				}
				if (idx==s1.length()) flag = false;
			}
			if (flag) return s1.length();
		}
		return -1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_522_Longest_Uncommon_Subsequence_II solution = new xLC_522_Longest_Uncommon_Subsequence_II();
		System.out.println(solution.findLUSlength(new String[]{"aba", "cdc", "eae"})); //3
		System.out.println(solution.findLUSlength(new String[]{"abcde", "abcd", "abc"})); //5
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
