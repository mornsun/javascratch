/**
 * 
 */
package topcoder;

import java.util.Arrays;

/**
 * 	On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
 *
 * A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 *
 * Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.
 *
 * The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.
 *
 *
 * Example:
 *
 * Input: 3, 2, 0, 0
 * Output: 0.0625
 * Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
 * From each of those positions, there are also two moves that will keep the knight on the board.
 * The total probability the knight stays on the board is 0.0625.
 *
 *
 * Note:
 *
 * N will be between 1 and 25.
 * K will be between 0 and 100.
 * The knight always initially starts on the board.
 *
 * DP
 *
 * @author Chauncey
 * Runtime: 9 ms, faster than 18.07% of Java online submissions for Knight Probability in Chessboard.
 * Memory Usage: 34.1 MB, less than 42.86% of Java online submissions for Knight Probability in Chessboard.
 */
public class xLC_688_Knight_Probability_in_Chessboard
{
	public double knightProbability(int N, int K, int r, int c) {
		if (K==0) return 1;
		double[][][] dp = new double[2][N][N];
		for (int y=0; y<N; ++y) {
			Arrays.fill(dp[0][y], 1.0);
		}
		for (int k=0; k<K; ++k) {
			for (int y=0; y<N; ++y) {
				for (int x=0, prev=k%2, curr=(k+1)%2; x<N; ++x) {
					dp[curr][y][x] = (y<2 || x<1 ? 0 : dp[prev][y-2][x-1]) + (y<1 || x<2 ? 0 : dp[prev][y-1][x-2]) + (y+2>=N || x<1 ? 0 : dp[prev][y+2][x-1]) + (y+1>=N || x<2 ? 0 : dp[prev][y+1][x-2]) + (y+2>=N || x+1>=N ? 0 : dp[prev][y+2][x+1]) + (y+1>=N || x+2>=N ? 0 : dp[prev][y+1][x+2]) + (y<1 || x+2>=N ? 0 : dp[prev][y-1][x+2]) + (y<2 || x+1>=N ? 0 : dp[prev][y-2][x+1]);
				}
			}
		}
		return dp[K%2][r][c] / Math.pow(8.0, K);
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_688_Knight_Probability_in_Chessboard solution = new xLC_688_Knight_Probability_in_Chessboard();
		System.out.println(solution.knightProbability(3, 1, 0, 0)); //0.25
		System.out.println(solution.knightProbability(3, 2, 0, 0)); //0.0625
		System.out.println(solution.knightProbability(8, 30, 6, 4)); //0.00019
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
