/**
 * 
 */
package topcoder;

import java.util.Arrays;

/**
 * 	Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 *
 *
 * Note:
 *
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 *
 * Sliding Window, Two Pointers
 *
 * @author Chauncey
 * Runtime: 5 ms, faster than 82.74% of Java online submissions for Permutation in String.
 * Memory Usage: 37.4 MB, less than 88.46% of Java online submissions for Permutation in String.
 */
public class xLC_567_Permutation_in_String
{
	public boolean checkInclusion(String s1, String s2) {

		if (s1==null || s1.length()==0) return true;
		if (s2==null || s2.length()==0) return false;
		int l1 = s1.length();
		int l2 = s2.length();
		if (l1>l2) return false;

		int total = 0;
		int[] ltrs = new int[26];
		Arrays.fill(ltrs, Integer.MIN_VALUE);
		for (char c : s1.toCharArray()) {
			int idx = c-'a';
			if (ltrs[idx]==Integer.MIN_VALUE) {
				ltrs[idx] = 1;
				total++;
			} else {
				ltrs[idx]++;
			}
		}
		for (int i=0; i<l1; ++i) {
			int idx = s2.charAt(i)-'a';
			if (ltrs[idx]==Integer.MIN_VALUE) continue;
			ltrs[idx]--;
			if (ltrs[idx]==0) total--;
		}
		if (total==0) return true;

		for (int i=l1; i<l2; ++i) {
			int idx = s2.charAt(i)-'a';
			if (ltrs[idx]!=Integer.MIN_VALUE) {
				ltrs[idx]--;
				if (ltrs[idx]==0) total--;
			}
			idx = s2.charAt(i-l1)-'a';
			if (ltrs[idx]!=Integer.MIN_VALUE) {
				ltrs[idx]++;
				if (ltrs[idx]==1) total++;
			}
			if (total==0) return true;
		}
		return false;

	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_567_Permutation_in_String solution = new xLC_567_Permutation_in_String();
		System.out.println(solution.checkInclusion("ab", "eidbaooo")); //true
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
