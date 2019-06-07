/**
 * 
 */
package topcoder;

import java.util.Arrays;

/**
 * 	On an infinite number line, the position of the i-th stone is given by stones[i].  Call a stone an endpoint stone if it has the smallest or largest position.
 *
 * Each turn, you pick up an endpoint stone and move it to an unoccupied position so that it is no longer an endpoint stone.
 *
 * In particular, if the stones are at say, stones = [1,2,5], you cannot move the endpoint stone at position 5, since moving it to any position (such as 0, or 3) will still keep that stone as an endpoint stone.
 *
 * The game ends when you cannot make any more moves, ie. the stones are in consecutive positions.
 *
 * When the game ends, what is the minimum and maximum number of moves that you could have made?  Return the answer as an length 2 array: answer = [minimum_moves, maximum_moves]
 *
 * Example 1:
 *
 * Input: [7,4,9]
 * Output: [1,2]
 * Explanation:
 * We can move 4 -> 8 for one move to finish the game.
 * Or, we can move 9 -> 5, 4 -> 6 for two moves to finish the game.
 * Example 2:
 *
 * Input: [6,5,4,3,10]
 * Output: [2,3]
 * We can move 3 -> 8 then 10 -> 7 to finish the game.
 * Or, we can move 3 -> 7, 4 -> 8, 5 -> 9 to finish the game.
 * Notice we cannot move 10 -> 2 to finish the game, because that would be an illegal move.
 * Example 3:
 *
 * Input: [100,101,104,102,103]
 * Output: [0,0]
 *
 * Note:
 *
 * 3 <= stones.length <= 10^4
 * 1 <= stones[i] <= 10^9
 * stones[i] have distinct values.
 *
 * Array, Sliding window
 *
 * @author Chauncey
 * Runtime: 6 ms, faster than 21.87%
 */
public class LC_1040_Moving_Stones_Until_Consecutive_II
{
    public int[] numMovesStonesII(int[] stones)
    {
        if (stones==null || stones.length<=2)
            return new int[]{0,0};

        Arrays.sort(stones);
        int N = stones.length;

        int l = 0;
        int low = N;
        for (int r=1; r<N; ++r) {
            if (stones[r]-stones[l]+1 > N) {
                while(stones[r]-stones[++l]+1 > N);
            }
            if (r - l + 1 == N - 1 && stones[r] - stones[l] == N - 2)
                low = Math.min(low, 2);
            else
                low = Math.min(low, N - (r - l + 1));
        }

        return new int[]{low, Math.max(stones[N-1]-stones[1]-N+2, stones[N-2]-stones[0]-N+2)};

    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1040_Moving_Stones_Until_Consecutive_II solution = new LC_1040_Moving_Stones_Until_Consecutive_II();
		System.out.println(Arrays.toString(solution.numMovesStonesII(new int[]{7,4,9}))); //[1,2]
        System.out.println(Arrays.toString(solution.numMovesStonesII(new int[]{6,5,4,3,10}))); //[2,3]
        System.out.println(Arrays.toString(solution.numMovesStonesII(new int[]{100,101,104,102,103}))); //[0,0]
        System.out.println(Arrays.toString(solution.numMovesStonesII(new int[]{8,7,6,5,10}))); //[1,1]
        System.out.println(Arrays.toString(solution.numMovesStonesII(new int[]{8,7,6,5,2}))); //[2,2]
        System.out.println(Arrays.toString(solution.numMovesStonesII(new int[]{1,500000000,1000000000}))); //[2,499999999]

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
