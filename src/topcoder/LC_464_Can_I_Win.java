/**
 * 
 */
package topcoder;

import java.util.Arrays;

/**
 * 	In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.
 *
 * What if we change the game so that players cannot re-use integers?
 *
 * For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.
 *
 * Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.
 *
 * You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.
 *
 * Example
 *
 * Input:
 * maxChoosableInteger = 10
 * desiredTotal = 11
 *
 * Output:
 * false
 *
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 *
 * DP, MiniMax
 * 
 * @author Chauncey
 * Runtime: 7 ms, faster than 99.19% of Java online submissions for Can I Win.
 * Memory Usage: 34.9 MB, less than 93.10% of Java online submissions for Can I Win.
 */
public class LC_464_Can_I_Win
{
	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		if (desiredTotal <= 0) return true;
		int canTotal = (1+maxChoosableInteger)*maxChoosableInteger/2;
		if (desiredTotal>canTotal) return false;
		if (desiredTotal==canTotal) return (maxChoosableInteger&1)!=0;

		byte[] memo = new byte[1<<maxChoosableInteger];
		return canIWin(memo, 0, maxChoosableInteger, desiredTotal);

	}

	private boolean canIWin(byte[] memo, int state, int choose, int total) {
		if (total<=0) return false;
		for (int i=0; i<choose; ++i) {
			if ((state & (1<<i)) != 0) continue;
			state |= 1<<i;
			if (memo[state] == 1) return true;
			if (memo[state] == 0) {
				if (!canIWin(memo, state, choose, total-(i+1))) {
					memo[state] = 1;
					return true;
				} else {
					memo[state] = 2;
				}
			}
			state &= ~(1<<i);
		}
		return false;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_464_Can_I_Win solution = new LC_464_Can_I_Win();
		System.out.println(solution.canIWin(10,11)); //false
		System.out.println(solution.canIWin(10,20)); //true
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
