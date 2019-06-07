/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	We have a collection of rocks, each rock has a positive integer weight.
 *
 * Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
 *
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)
 *
 *
 *
 * Example 1:
 *
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
 * we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
 * we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.
 *
 *
 * Note:
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 *
 * Dynamic Programming

 * @author Chauncey
 * Runtime: 1 ms, faster than 98.06%
 */
public class LC_1049_Last_Stone_Weight_II
{
	public int lastStoneWeightII(int[] stones) {
		if (stones == null || stones.length==0)
			return 0;
		int sum = 0;
		boolean[] dp = new boolean[1501];
		dp[0] = true;
		for (int a : stones) {
			sum += a;
			for (int i=sum; i>=a; --i) {
				dp[i] |= dp[i-a];
			}
		}
		for (int i=sum/2; i>=0; --i) {
			if (dp[i])
				return sum-i-i;
		}
		return 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1049_Last_Stone_Weight_II solution = new LC_1049_Last_Stone_Weight_II();
		System.out.println(solution.lastStoneWeightII(new int[]{2,7,4,1,8,1})); //[0, 0, 1, 1, 0, 0, 0, 0]


		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
