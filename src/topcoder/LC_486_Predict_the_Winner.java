/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 	Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.
 *
 * Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.
 *
 * Example 1:
 * Input: [1, 5, 2]
 * Output: False
 * Explanation: Initially, player 1 can choose between 1 and 2.
 * If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2).
 * So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
 * Hence, player 1 will never be the winner and you need to return False.
 * Example 2:
 * Input: [1, 5, 233, 7]
 * Output: True
 * Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
 * Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
 * Note:
 * 1 <= length of the array <= 20.
 * Any scores in the given array are non-negative integers and will not exceed 10,000,000.
 * If the scores of both players are equal, then player 1 is still the winner.
 *
 * DP, Minimax

 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Predict the Winner.
 * Memory Usage: 33.9 MB, less than 100.00% of Java online submissions for Predict the Winner.
 */
public class LC_486_Predict_the_Winner
{
	public boolean PredictTheWinner(int[] nums) {
		if (nums==null || nums.length==0)
			return false;

		int n = nums.length;
		int[] sum = new int[n+1];
		for (int i=1; i<=n; ++i) {
			sum[i] = sum[i-1] + nums[i-1];
		}

		int[] dp = new int[n];
		dp[n-1] = nums[n-1];
		for (int i=n-2; i>=0; --i) {
			int[] prev = dp;
			dp = new int[n];
			dp[i] = nums[i];
			dp[i+1] = Math.max(nums[i], nums[i+1]);
			for (int j=i+2; j<n; ++j) {
				dp[j] = Math.max(sum[j+1]-sum[i]-dp[j-1], sum[j+1]-sum[i]-prev[j]);
			}
		}
		//System.out.println(dp[0][n-1]);
		return dp[n-1]*2 >= sum[n];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_486_Predict_the_Winner solution = new LC_486_Predict_the_Winner();
		System.out.println(solution.PredictTheWinner(new int[]{1, 5, 2})); //false
		System.out.println(solution.PredictTheWinner(new int[]{1, 5, 233, 7})); //true
		System.out.println(solution.PredictTheWinner(new int[]{0})); //true
		System.out.println(solution.PredictTheWinner(new int[]{1,1,1})); //true

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
