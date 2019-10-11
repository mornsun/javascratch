/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 	You have a set of tiles, where each tile has one letter tiles[i] printed on it.  Return the number of possible non-empty sequences of letters you can make.
 *
 * Example 1:
 *
 * Input: "AAB"
 * Output: 8
 * Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
 *
 * Example 2:
 *
 * Input: "AAABBC"
 * Output: 188
 *
 * Note:
 *
 * 1 <= tiles.length <= 7
 * tiles consists of uppercase English letters.
 *
 * @author Chauncey
 * Runtime: 2 ms, faster than 84.12% of Java online submissions for Letter Tile Possibilities.
 * Memory Usage: 34.3 MB, less than 100.00% of Java online submissions for Letter Tile Possibilities.
 */
public class LC_1079_Letter_Tile_Possibilities
{
	public int numTilePossibilities(String tiles) {

		if (tiles==null || tiles.length()==0)
			return 0;
		int cnts[] = new int[26];
		for (char ch : tiles.toCharArray()) cnts[ch-'A']++;
		return dfs(cnts);
	}

	int dfs(int[] cnts) {
		int res = 0;
		for (int i=0; i<26; ++i) {
			if (cnts[i]==0) continue;
			cnts[i]--;
			res += dfs(cnts)+1;
			cnts[i]++;
		}
		return res;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1079_Letter_Tile_Possibilities solution = new LC_1079_Letter_Tile_Possibilities();
        System.out.println(solution.numTilePossibilities("AAB")); //8
		System.out.println(solution.numTilePossibilities("AAABBC")); //188
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
