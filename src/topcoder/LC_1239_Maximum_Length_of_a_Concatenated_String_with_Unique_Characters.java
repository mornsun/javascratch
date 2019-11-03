/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.List;

/**
 * 	Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
 * Return the maximum possible length of s.
 *
 * Example 1:
 *
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
 * Maximum length is 4.
 *
 * Example 2:
 *
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible solutions are "chaers" and "acters".
 *
 * Example 3:
 *
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 *
 * Constraints:
 *
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] contains only lower case English letters.
 *
 * Backtracking, Bit Manipulation
 *
 * @author Chauncey
 * Runtime: 2 ms, faster than 95.28% of Java online submissions for Maximum Length of a Concatenated String with Unique Characters.
 * Memory Usage: 35.7 MB, less than 100.00% of Java online submissions for Maximum Length of a Concatenated String with Unique Characters.
 */
public class LC_1239_Maximum_Length_of_a_Concatenated_String_with_Unique_Characters
{
	public int maxLength(List<String> arr) {
		if (arr==null || arr.size()==0) return 0;
		return dfs(arr, 0, 0, 0);
	}

	private int dfs(List<String> arr, int idx, int len, int bm) {
		int n = arr.size();
		if (idx == n) return len;
		int max = len;
		for (int i=idx; i<n; ++i) {
			String s = arr.get(i);
			boolean flag = false;
			int nbm = bm;
			for (char ch : s.toCharArray()) {
				int btest = 1<<ch-'a';
				if ((nbm & btest) != 0) {
					flag = true;
					break;
				}
				nbm |= btest;
			}
			if (flag) continue;
			max = Math.max(max, dfs(arr, i+1, len+s.length(), nbm));
		}
		return max;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1239_Maximum_Length_of_a_Concatenated_String_with_Unique_Characters solution = new LC_1239_Maximum_Length_of_a_Concatenated_String_with_Unique_Characters();
        System.out.println(solution.maxLength(Arrays.asList(new String[]{"un","iq","ue"}))); //4
		System.out.println(solution.maxLength(Arrays.asList(new String[]{"cha","r","act","ers"}))); //6
		System.out.println(solution.maxLength(Arrays.asList(new String[]{"abcdefghijklmnopqrstuvwxyz"}))); //26
		System.out.println(solution.maxLength(Arrays.asList(new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p"}))); //16
		System.out.println(solution.maxLength(Arrays.asList(new String[]{"yy","bkhwmpbiisbldzknpm"}))); //0
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
