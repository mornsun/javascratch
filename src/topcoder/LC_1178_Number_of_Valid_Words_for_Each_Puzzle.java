/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
 * word contains the first letter of puzzle.
 * For each letter in word, that letter is in puzzle.
 * For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage"; while invalid words are "beefed" (doesn't include "a") and "based" (includes "s" which isn't in the puzzle).
 * Return an array answer, where answer[i] is the number of words in the given word list words that are valid with respect to the puzzle puzzles[i].
 *
 * Example :
 *
 * Input:
 * words = ["aaaa","asas","able","ability","actt","actor","access"],
 * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * Output: [1,1,3,2,4,0]
 * Explanation:
 * 1 valid word for "aboveyz" : "aaaa"
 * 1 valid word for "abrodyz" : "aaaa"
 * 3 valid words for "abslute" : "aaaa", "asas", "able"
 * 2 valid words for "absoryz" : "aaaa", "asas"
 * 4 valid words for "actresz" : "aaaa", "asas", "actt", "access"
 * There're no valid words for "gaswxyz" cause none of the words in the list contains letter 'g'.
 *
 * Constraints:
 *
 * 1 <= words.length <= 10^5
 * 4 <= words[i].length <= 50
 * 1 <= puzzles.length <= 10^4
 * puzzles[i].length == 7
 * words[i][j], puzzles[i][j] are English lowercase letters.
 * Each puzzles[i] doesn't contain repeated characters.
 *
 * @author Chauncey
 * Runtime: 583 ms, faster than 28.03% of Java online submissions for Number of Valid Words for Each Puzzle.
 * Memory Usage: 61.3 MB, less than 100.00% of Java online submissions for Number of Valid Words for Each Puzzle.
 */
public class LC_1178_Number_of_Valid_Words_for_Each_Puzzle
{
	public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {

		ArrayList<Integer> res = new ArrayList<>();
		if (puzzles==null || puzzles.length==0)
			return res;

		res.ensureCapacity(puzzles.length);
		for (int i=0; i<puzzles.length; ++i)
			res.add(0);
		if (words==null || words.length==0)
			return res;

		ArrayList<ArrayList<Integer>> wds = new ArrayList<>(26);
		for (int i=0; i<26; ++i)
			wds.add(new ArrayList<Integer>());

		int[] visit = new int[8];
		for (String word : words) {
			int m = 0;
			int cnt = 0;
			for (char ch : word.toCharArray()) {
				int f = ch - 'a';
				int b = 1 << f;
				if ( (m&b) == 0) {
					visit[cnt++] = f;
					if (cnt==8)
						break;
				}
				m |= b;
			}
			if (cnt>7)
				continue;
			visit[cnt] = -1;
			for (int i=0; i<7 && visit[i]!=-1; ++i)
				wds.get(visit[i]).add(m);
		}

		for (int i=0; i<puzzles.length; ++i) {
			String puzzle = puzzles[i];
			int m = 0;
			for (char ch : puzzle.toCharArray()) {
				int b = 1 << ch - 'a';
				m |= b;
			}
			ArrayList<Integer> li = wds.get(puzzle.charAt(0)-'a');
			int cnt = 0;
			for (int n : li) {
				if ((n|m) == m)
					cnt++;
			}
			res.set(i, cnt);
		}
		return res;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1178_Number_of_Valid_Words_for_Each_Puzzle solution = new LC_1178_Number_of_Valid_Words_for_Each_Puzzle();
        System.out.println(solution.findNumOfValidWords(new String[]{"aaaa","asas","able","ability","actt","actor","access"}, new String[]{"aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"})); //[1,1,3,2,4,0]
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
