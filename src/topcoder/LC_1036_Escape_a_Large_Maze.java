/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	In a 1 million by 1 million grid, the coordinates of each grid square are (x, y) with 0 <= x, y < 10^6.
 *
 * We start at the source square and want to reach the target square.  Each move, we can walk to a 4-directionally adjacent square in the grid that isn't in the given list of blocked squares.
 *
 * Return true if and only if it is possible to reach the target square through a sequence of moves.
 *
 * Example 1:
 *
 * Input: blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
 * Output: false
 * Explanation:
 * The target square is inaccessible starting from the source square, because we can't walk outside the grid.
 * Example 2:
 *
 * Input: blocked = [], source = [0,0], target = [999999,999999]
 * Output: true
 * Explanation:
 * Because there are no blocked cells, it's possible to reach the target square.
 *
 * Note:
 *
 * 0 <= blocked.length <= 200
 * blocked[i].length == 2
 * 0 <= blocked[i][j] < 10^6
 * source.length == target.length == 2
 * 0 <= source[i][j], target[i][j] < 10^6
 * source != target
 *
 * Breadth-first Search

 * @author Chauncey
 * Runtime: 53 ms, faster than 61.07%
 */
public class LC_1036_Escape_a_Large_Maze
{
	/*public boolean isEscapePossible(int[][] blocked, int[] source, int[] target)
	{
		if (source==null || source.length!=2)
			return false;

		if (target==null || target.length!=2)
			return false;

		if (blocked==null || blocked.length==0 || blocked[0].length!=2)
			return true;

		HashSet<int[]> blocks = new HashSet<>(blocked.length);
		for (int[] block : blocked)
			blocks.add(block);

		while (!blocks.isEmpty()) {
			int[] pos = blocks.iterator().next();
			LinkedList<int[]> queue = new LinkedList<>();
			queue.push(pos);
			while (!queue.isEmpty()) {
				int[] curr = queue.poll();
				for (int i=y-1)
				if (blocks.contains(()))
			}

		}
		Iterator<Long> iter = blocks.iterator();


	}*/

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1036_Escape_a_Large_Maze solution = new LC_1036_Escape_a_Large_Maze();
//		System.out.println(solution.isEscapePossible(new int[]{1,2,3}));
//		System.out.println(solution.minScoreTriangulation(new int[]{3,7,4,5}));
//		System.out.println(solution.minScoreTriangulation(new int[]{1,3,1,4,1,5}));

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
