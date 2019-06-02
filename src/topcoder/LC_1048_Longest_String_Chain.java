/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	Given a list of words, each word consists of English lowercase letters.
 *
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".
 *
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
 *
 * Return the longest possible length of a word chain with words chosen from the given list of words.
 *
 * Example 1:
 *
 * Input: ["a","b","ba","bca","bda","bdca"]
 * Output: 4
 * Explanation: one of the longest word chain is "a","ba","bda","bdca".
 *
 * Note:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 16
 * words[i] only consists of English lowercase letters.
 *
 * Hash Table, Dynamic Programming

 * @author Chauncey
 * Runtime: 34 ms, faster than 75.13%
 */
public class LC_1048_Longest_String_Chain
{
	public int longestStrChain(String[] words) {
		if (words==null || words.length==0)
			return 0;

		List<HashMap<String, Integer>> maps = new ArrayList<>();
		int SIZE = words.length/4;
		for (int i=0; i<16; ++i) {
			maps.add(new HashMap<String, Integer>(SIZE));
		}

		for (String word : words) {
			maps.get(word.length()-1).put(word, 0);
		}

		int max = 0;
		for (int i=maps.size()-1; i>0; --i) {
			for (Map.Entry<String, Integer> entry : maps.get(i).entrySet()) {
				int cntp = entry.getValue();
				String word = entry.getKey();
				for (int k=0; k<word.length(); ++k) {
					String nw = word.substring(0,k) + word.substring(k+1);
					Integer cnt = maps.get(i-1).get(nw);
					if (cnt!=null && cntp+1>cnt) {
						maps.get(i-1).put(nw, cntp+1);
						max = cntp+1>max ? cntp+1 : max;
					}
				}
			}
		}

		return max+1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1048_Longest_String_Chain solution = new LC_1048_Longest_String_Chain();
		System.out.println(solution.longestStrChain(new String[]{"a","b","ba","bca","bda","bdca"}));

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
