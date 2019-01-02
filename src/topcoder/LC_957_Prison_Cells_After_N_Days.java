/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	There are 8 prison cells in a row, and each cell is either occupied or vacant.

 Each day, whether the cell is occupied or vacant changes according to the following rules:

 If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 Otherwise, it becomes vacant.

 (Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)

 We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.

 Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)

 Example 1:

 Input: cells = [0,1,0,1,1,0,0,1], N = 7
 Output: [0,0,1,1,0,0,0,0]
 Explanation:
 The following table summarizes the state of the prison on each day:
 Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 Day 7: [0, 0, 1, 1, 0, 0, 0, 0]

 Example 2:

 Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
 Output: [0,0,1,1,1,1,1,0]

 Note:

 cells.length == 8
 cells[i] is in {0, 1}
 1 <= N <= 10^9

 Related Topics
 Hash Table

 * @author Chauncey
 * Runtime: 18 ms, faster than 34.39%
 */
public class LC_957_Prison_Cells_After_N_Days
{
	public int[] prisonAfterNDays(int[] cells, int N) {
		if (cells==null || cells.length!=8 || N==0)
			return cells;

		HashSet<Integer> set = new HashSet<>();
		ArrayList<Integer> list = new ArrayList<>();
		int n = arrayToInt(cells);
		//int i=1;
		while (N-- != 0) {
			n = nextInt(n);
			//System.out.println(i++ + " day:" + Arrays.toString(intToArray(n))+":  "+Integer.toBinaryString(n));
			if (set.contains(n)) {
				int idx = N % set.size();
				return intToArray(list.get(idx));
			}
			set.add(n);
			list.add(n);
		}
		return intToArray(n);
	}

	private static int arrayToInt(int[] cells) {
		int ret = 0;
		for (int i = 0; i < cells.length; ++i) {
			if (cells[i] == 1) {
				ret |= 1<<i;
			}
		}
		return ret;
	}

	private static int[] intToArray(int n) {
		int[] ret = new int[8];
		for (int i=0; i<8; ++i) {
			if ((n & (1<<i)) != 0)
				ret[i] = 1;
		}
		return ret;
	}

	private static int nextInt(int curr) {
		int ret = 0;
		int mask = 5;
		for (int i=1; i<7; ++i) {
			int window = curr & mask;
			if (window == 0 || window == 5) {
				ret |= 1 << i;
			}
			curr >>= 1;
		}
		return ret;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_957_Prison_Cells_After_N_Days solution = new LC_957_Prison_Cells_After_N_Days();
		System.out.println(Arrays.toString(solution.prisonAfterNDays(new int[]{0,1,0,1,1,0,0,1}, 7))); //[0, 0, 1, 1, 0, 0, 0, 0]


		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
