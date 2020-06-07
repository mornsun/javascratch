/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.List;

/**
 * 	Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 *
 * The order of output does not matter.
 *
 * Example 1:
 *
 * Input:
 * s: "cbaebabacd" p: "abc"
 *
 * Output:
 * [0, 6]
 *
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 *
 * Example 2:
 *
 * Input:
 * s: "abab" p: "ab"
 *
 * Output:
 * [0, 1, 2]
 *
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".

 Related Topics:
 Hash Table, Sliding Window

 * @author Chauncey
Runtime: 5 ms, faster than 97.11% of Java online submissions for Find All Anagrams in a String.
Memory Usage: 41.5 MB, less than 6.00% of Java online submissions for Find All Anagrams in a String.
 */
public class LC_438_Find_All_Anagrams_in_a_String
{
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> res = new ArrayList<>();
		if (s==null || p==null || s.length()==0 || p.length()==0 ||  s.length() < p.length())
			return res;

		int cnt = 0;
		int[] cmap = new int[26];
		for (char c : p.toCharArray()) {
			int idx = c-'a';
			if (cmap[idx] == 0)
				cnt++;
			cmap[idx]--;
		}
		int ns = s.length(), np = p.length();
		for (int i=0; i<np; ++i) {
			int idx = s.charAt(i) - 'a';
			if (cmap[idx] == 0)
				cnt++;
			cmap[idx]++;
			if (cmap[idx] == 0)
				cnt--;
		}
		if (cnt==0)
			res.add(0);
		for (int i=np; i<ns; ++i) {
			int idx0 = s.charAt(i-np) - 'a';
			int idx1 = s.charAt(i) - 'a';
			if (cmap[idx1] == 0)
				cnt++;
			cmap[idx1]++;
			if (cmap[idx1] == 0)
				cnt--;
			if (cmap[idx0] == 0)
				cnt++;
			cmap[idx0]--;
			if (cmap[idx0] == 0)
				cnt--;
			if (cnt==0)
				res.add(i-np+1);
		}

		return res;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_438_Find_All_Anagrams_in_a_String solution = new LC_438_Find_All_Anagrams_in_a_String();

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
