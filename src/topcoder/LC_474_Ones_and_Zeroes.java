/**
 * 
 */
package topcoder;

import java.util.HashMap;
import java.util.Map;

/**
 * 	In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
 * For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.
 * Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.
 *
 * Note:
 *
 * The given numbers of 0s and 1s will both not exceed 100
 * The size of given string array won't exceed 600.
 *
 * Example 1:
 *
 * Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 * Output: 4
 *
 * Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
 *
 * Example 2:
 *
 * Input: Array = {"10", "0", "1"}, m = 1, n = 1
 * Output: 2
 *
 * Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
 *
 * DP
 *
 * @author Chauncey
 * Runtime: 100 ms, faster than 12.53% of Java online submissions for Ones and Zeroes.
 * Memory Usage: 41 MB, less than 25.00% of Java online submissions for Ones and Zeroes.
 *
Runtime: 13 ms, faster than 78.37% of Java online submissions for Ones and Zeroes.
Memory Usage: 35.5 MB, less than 100.00% of Java online submissions for Ones and Zeroes.
 */
public class LC_474_Ones_and_Zeroes
{
	public int findMaxForm1(String[] strs, int m, int n) {
		int[][] memo = new int[m+1][n+1];
		int numZeroes, numOnes;

		for (String s : strs) {
			numZeroes = numOnes = 0;
			// count number of zeroes and ones in current string
			for (char c : s.toCharArray()) {
				if (c == '0')
					numZeroes++;
				else if (c == '1')
					numOnes++;
			}

			// memo[i][j] = the max number of strings that can be formed with i 0's and j 1's
			// from the first few strings up to the current string s
			// Catch: have to go from bottom right to top left
			// Why? If a cell in the memo is updated(because s is selected),
			// we should be adding 1 to memo[i][j] from the previous iteration (when we were not considering s)
			// If we go from top left to bottom right, we would be using results from this iteration => overcounting
			for (int i = m; i >= numZeroes; i--) {
				for (int j = n; j >= numOnes; j--) {
					memo[i][j] = Math.max(memo[i][j], memo[i - numZeroes][j - numOnes] + 1);
				}
			}
		}
		return memo[m][n];
	}

	public int findMaxForm(String[] strs, int m, int n) {

		if (strs==null || strs.length==0) return 0;
		HashMap<Integer, Integer> dp = new HashMap<>();
		dp.put(0, 0);
		for (String str : strs) {
			int dm = 0;
			int dn = 0;
			for (char ch : str.toCharArray()) {
				if (ch=='0') dm++;
				else if (ch=='1') dn++;
			}
			HashMap<Integer, Integer> nextdp = new HashMap<>();
			for (Map.Entry<Integer, Integer> entry : dp.entrySet()) {
				int k0 = entry.getKey();
				int m0 = k0/(n+1), n0 = k0%(n+1);
				int m1 = m0 + dm, n1 = n0 + dn;
				if (m1>m || n1>n) continue;
				int k1 = m1*(n+1) + n1;
				int vn = entry.getValue()+1;
				int v1 = Math.max(dp.getOrDefault(k1, 0), nextdp.getOrDefault(k1, 0));
				if (vn>v1)
					nextdp.put(k1, vn);
			}
			dp.putAll(nextdp);
		}
		int max = 0;
		for (Map.Entry<Integer, Integer> entry : dp.entrySet())
			max = Math.max(max, entry.getValue());
		return max;

	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_474_Ones_and_Zeroes solution = new LC_474_Ones_and_Zeroes();
        System.out.println(solution.findMaxForm(new String[]{"10","0001","111001","1","0"}, 5, 3)); //4
        System.out.println(solution.findMaxForm(new String[]{"10","0","1"}, 1, 1)); //2
		System.out.println(solution.findMaxForm(new String[]{"0","11","1000","01","0","101","1","1","1","0","0","0","0","1","0","0110101","0","11","01","00","01111","0011","1","1000","0","11101","1","0","10","0111"}, 9, 80)); //17
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
