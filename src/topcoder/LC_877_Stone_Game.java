/**
 * 
 */
package topcoder;

/**
 * 	Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
 *
 * The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
 *
 * Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.  This continues until there are no more piles left, at which point the person with the most stones wins.
 *
 * Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
 *
 * Example 1:
 *
 * Input: [5,3,4,5]
 * Output: true
 * Explanation:
 * Alex starts first, and can only take the first 5 or the last 5.
 * Say he takes the first 5, so that the row becomes [3, 4, 5].
 * If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
 * If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
 * This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
 *
 * Note:
 *
 * 2 <= piles.length <= 500
 * piles.length is even.
 * 1 <= piles[i] <= 500
 * sum(piles) is odd.
 *
 * DP, Minimax

 * @author Chauncey
 * Runtime: 3 ms, faster than 55.72% of Java online submissions for Stone Game.
 * Memory Usage: 34.7 MB, less than 100.00% of Java online submissions for Stone Game.
 */
public class LC_877_Stone_Game
{
	public boolean stoneGame(int[] piles) {
		if (piles==null || piles.length==0) return false;
		int n = piles.length;
		int[] dp = new int[n];
		for (int i=n-2; i>=0; --i) {
			dp[i] = piles[i];
			dp[i+1] = Math.max(piles[i], piles[i+1]);
			for (int j=i+2; j<n; ++j) {
				dp[j] = Math.max(piles[i]+dp[j], piles[j]+dp[j-1]);
			}
		}
		int sum=0;
		for (int pile : piles)
			sum+=pile;
		return dp[n-1]*2>sum;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_877_Stone_Game solution = new LC_877_Stone_Game();
		System.out.println(solution.stoneGame(new int[]{5,3,4,5})); //true

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
