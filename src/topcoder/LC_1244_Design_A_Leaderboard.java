/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	Design a Leaderboard class, which has 3 functions:
 *
 * addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
 * top(K): Return the score sum of the top K players.
 * reset(playerId): Reset the score of the player with the given id to 0. It is guaranteed that the player was added to the leaderboard before calling this function.
 * Initially, the leaderboard is empty.
 *
 * Example 1:
 *
 * Input:
 * ["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
 * [[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
 * Output:
 * [null,null,null,null,null,null,73,null,null,null,141]
 *
 * Explanation:
 * Leaderboard leaderboard = new Leaderboard ();
 * leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
 * leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
 * leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
 * leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
 * leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.top(1);           // returns 73;
 * leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
 * leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
 * leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
 *
 * Constraints:
 *
 * 1 <= playerId, K <= 10000
 * It's guaranteed that K is less than or equal to the current number of players.
 * 1 <= score <= 100
 * There will be at most 1000 function calls.
 *
 * HashTable, Sort, Design
 *
 * Hint:
 * What data structure can we use to keep the players' data?
 * Keep a map (dictionary) of player scores.
 * For each top(K) function call, find the maximum K scores and add them.
 *
 * @author Chauncey
 * Runtime: 54 ms, faster than 100.00% of Java online submissions for Design A Leaderboard.
 * Memory Usage: 43.2 MB, less than 100.00% of Java online submissions for Design A Leaderboard.
 */
public class LC_1244_Design_A_Leaderboard
{
	private HashMap<Integer, Integer> players = new HashMap<>();
	private TreeMap<Integer, Integer> boards = new TreeMap<>(Collections.reverseOrder());

	public LC_1244_Design_A_Leaderboard() {

	}

	public void addScore(int playerId, int score) {
		int prev = players.getOrDefault(playerId, 0);
		if (prev!=0) {
			int cnt = boards.get(prev);
			if (cnt==1)
				boards.remove(prev);
			else
				boards.put(prev, cnt-1);
		}
		int curr = prev+score;
		players.put(playerId, curr);
		int cnt = boards.getOrDefault(curr, 0);
		boards.put(curr, cnt+1);
	}

	public int top(int K) {
		int sum = 0;
		for (Map.Entry<Integer, Integer> entry : boards.entrySet()) {
			if (entry.getValue()>K)
				sum += entry.getKey()*K;
			else
				sum += entry.getKey()*entry.getValue();
			K -= entry.getValue();
			if (K<=0) break;
		}
		return sum;
	}

	public void reset(int playerId) {
		int prev = players.getOrDefault(playerId, 0);
		if (prev==0)
			return;
		int cnt = boards.get(prev);
		if (cnt==1)
			boards.remove(prev);
		else
			boards.put(prev, cnt-1);
		players.put(playerId, 0);
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1244_Design_A_Leaderboard solution = new LC_1244_Design_A_Leaderboard();
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
