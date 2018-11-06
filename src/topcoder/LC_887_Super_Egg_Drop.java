/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * You are given K eggs, and you have access to a building with N floors from 1 to N.

 Each egg is identical in function, and if an egg breaks, you cannot drop it again.

 You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, and any egg dropped at or below floor F will not break.

 Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N).

 Your goal is to know with certainty what the value of F is.

 What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?

 Example 1:

 Input: K = 1, N = 2
 Output: 2
 Explanation:
 Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
 Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
 If it didn't break, then we know with certainty F = 2.
 Hence, we needed 2 moves in the worst case to know what F is with certainty.
 Example 2:

 Input: K = 2, N = 6
 Output: 3
 Example 3:

 Input: K = 3, N = 14
 Output: 4

 Note:

 1 <= K <= 100
 1 <= N <= 10000

 Related Topic
 Math, Binary Search, Dynamic Programming

 * @author Chauncey
 *
 */
public class LC_887_Super_Egg_Drop
{
    public int superEggDrop(int K, int N) {
        if (N == 0) {
            return 0;
        }
        if (K <= 0 || N < 0) {
            return -1;
        }
        if (K == 1) {
            return N;
        }
        if (N == 1) {
            return 1;
        }
        //System.out.println(K + " " + N);
        int[] dp = new int[N+1];
        for (int n=1; n<=N; ++n) {
            dp[n] = n;
        }
        for (int k=1; k<K; ++k) {
            //System.out.println(Arrays.toString(dp));
            int[] dp2 = new int[N+1];
            dp2[1] = 1;
            int start = 1;
            for (int n=2; n<=N; ++n) {
                /*dp2[n] = dp[n];
                int c = start;
                int min = Integer.MAX_VALUE;
                for (; c<=n; ++c) {
                    dp2[n] = Math.min(dp2[n], Math.max(dp[c-1], dp2[n-c])+1);
                    if (dp2[n] > min) {
                        break;
                    }
                    min = dp2[n];
                }
                start = c-1;*/
                // Let's find dp2[n] = dp(k, n)
                // Increase our optimal x while we can make our answer better.
                // Notice max(dp[x-1], dp2[n-x]) > max(dp[x], dp2[n-x-1])
                // is simply max(T1(x-1), T2(x-1)) > max(T1(x), T2(x)).
                while (start < n && Math.max(dp[start-1], dp2[n-start]) > Math.max(dp[start], dp2[n-start-1]))
                    start++;

                // The final answer happens at this x.
                dp2[n] = 1 + Math.max(dp[start-1], dp2[n-start]);
            }
            dp = dp2;
        }
        //System.out.println(Arrays.toString(dp));
        return dp[N];
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_887_Super_Egg_Drop solution = new LC_887_Super_Egg_Drop();
		System.out.println(solution.superEggDrop(1, 2)); //2
        System.out.println(solution.superEggDrop(2, 6)); //3
        System.out.println(solution.superEggDrop(3, 14)); //4
        System.out.println(solution.superEggDrop(1, 1)); //1
        System.out.println(solution.superEggDrop(1, 3)); //3
        System.out.println(solution.superEggDrop(2, 1)); //1
        System.out.println(solution.superEggDrop(2, 7)); //4
        System.out.println(solution.superEggDrop(6, 10000)); //16
		
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
