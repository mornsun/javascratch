/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * A chess knight can move as indicated in the chess diagram below:

 This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.
 Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.
 How many distinct numbers can you dial in this manner?
 Since the answer may be large, output the answer modulo 10^9 + 7.


 Example 1:
 Input: 1
 Output: 10
 Example 2:
 Input: 2
 Output: 20
 Example 3:
 Input: 3
 Output: 46

 Note:
 1 <= N <= 5000

 * @author Chauncey
 * beat 93.09%
 */
public class LC_935_Knight_Dialer
{
    private static int[][] graph = new int[][]{{4,6},{6,8},{7,9},{4,8},{3,9,0},{},{1,7,0},{2,6},{1,3},{2,4}};

    public int knightDialer(int N) {
        int MOD = 1_000_000_007;
        int[] dp = new int[]{1,1,1,1,1,1,1,1,1,1};
        for (int i=1; i<N; ++i) {
            int[] dp1 = new int[10];
            for (int j=0; j<10; ++j) {
                for (int to : graph[j]) {
                    dp1[to] += dp[j];
                    dp1[to] %= MOD;
                }
            }
            dp = dp1;
        }

        int sum=0;
        for (int j=0; j<10; ++j) {
            sum += dp[j];
            sum %= MOD;
        }
        return sum;
    }

    public int knightDialer1(int N) {
        int MOD = 1_000_000_007;
        int[][] moves = new int[][]{
                {4,6},{6,8},{7,9},{4,8},{3,9,0},
                {},{1,7,0},{2,6},{1,3},{2,4}};

        int[][] dp = new int[2][10];
        Arrays.fill(dp[0], 1);
        for (int hops = 0; hops < N-1; ++hops) {
            Arrays.fill(dp[~hops & 1], 0);
            for (int node = 0; node < 10; ++node)
                for (int nei: moves[node]) {
                    dp[~hops & 1][nei] += dp[hops & 1][node];
                    dp[~hops & 1][nei] %= MOD;
                }
        }

        long ans = 0;
        for (int x: dp[~N & 1])
            ans += x;
        return (int) (ans % MOD);
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_935_Knight_Dialer solution = new LC_935_Knight_Dialer();
		System.out.println(solution.knightDialer(1)); //10
        System.out.println(solution.knightDialer(2)); //20
        System.out.println(solution.knightDialer(3)); //46
        System.out.println(solution.knightDialer(4)); //104

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
