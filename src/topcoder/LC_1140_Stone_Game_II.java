/**
 * 
 */
package topcoder;

/**
 * 	Alex and Lee continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones.
 *
 * Alex and Lee take turns, with Alex starting first.  Initially, M = 1.
 *
 * On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).
 *
 * The game continues until all the stones have been taken.
 *
 * Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.
 *
 * Example 1:
 *
 * Input: piles = [2,7,9,4,4]
 * Output: 10
 * Explanation:  If Alex takes one pile at the beginning, Lee takes two piles, then Alex takes 2 piles again. Alex can get 2 + 4 + 4 = 10 piles in total. If Alex takes two piles at the beginning, then Lee can take all three piles left. In this case, Alex get 2 + 7 = 9 piles in total. So we return 10 since it's larger.
 *
 * Constraints:
 *
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 10 ^ 4
 *
 * DP, Minimax

 * @author Chauncey
 * Runtime: 4 ms, faster than 38.47% of Java online submissions for Stone Game II.
 * Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Stone Game II.
 */
public class LC_1140_Stone_Game_II
{
	public int stoneGameII(int[] piles) {
		if (piles==null || piles.length==0)
			return 0;

		int n = piles.length;
		if (n==1)
			return piles[0];
		if (n==2)
			return piles[0] + piles[1];

		int[][] dp = new int[n+1][n+1];
		int sum=0;
		for (int i=n-1; i>=0; --i) {
			sum += piles[i];
			for (int m=n; m>=1; --m) {
				int max = 0;
				for (int x=1; x<=2*m && x<=n-i; ++x) {
					dp[i][m] = Math.max(dp[i][m], sum-dp[i + x][Math.max(m,x)]);
				}
				//dp[i][m] = sums[i]-max;
				//System.out.print(dp[i][m] + " ");
			}
			//System.out.println();
		}

		return dp[0][1];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1140_Stone_Game_II solution = new LC_1140_Stone_Game_II();
		System.out.println(solution.stoneGameII(new int[]{2,7,9,4,4})); //10
		System.out.println(solution.stoneGameII(new int[]{9,2,2,8,3,7,9,9})); //29
		System.out.println(solution.stoneGameII(new int[]{8,9,5,4,5,4,1,1,9,3,1,10,5,9,6,2,7,6,6,9})); //56
		System.out.println(solution.stoneGameII(new int[]{8270,7145,575,5156,5126,2905,8793,7817,5532,5726,7071,7730,5200,5369,5763,7148,8287,9449,7567,4850,1385,2135,1737,9511,8065,7063,8023,7729,7084,8407})); //98008

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
