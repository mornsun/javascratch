/**
 * 
 */
package topcoder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

/**
 * 	You are given the number of rows n_rows and number of columns n_cols of a 2D binary matrix where all values are initially 0. Write a function flip which chooses a 0 value uniformly at random, changes it to 1, and then returns the position [row.id, col.id] of that value. Also, write a function reset which sets all values back to 0. Try to minimize the number of calls to system's Math.random() and optimize the time and space complexity.
 *
 * Note:
 *
 * 1 <= n_rows, n_cols <= 10000
 * 0 <= row.id < n_rows and 0 <= col.id < n_cols
 * flip will not be called when the matrix has no 0 values left.
 * the total number of calls to flip and reset will not exceed 1000.
 * Example 1:
 *
 * Input:
 * ["Solution","flip","flip","flip","flip"]
 * [[2,3],[],[],[],[]]
 * Output: [null,[0,1],[1,2],[1,0],[1,1]]
 * Example 2:
 *
 * Input:
 * ["Solution","flip","flip","reset","flip"]
 * [[1,2],[],[],[],[]]
 * Output: [null,[0,0],[0,1],null,[0,0]]
 * Explanation of Input Syntax:
 *
 * The input is two lists: the subroutines called and their arguments. Solution's constructor has two arguments, n_rows and n_cols. flip and reset have no arguments. Arguments are always wrapped with a list, even if there aren't any.
 *
 * @author Chauncey
 * Runtime: 29 ms, faster than 100.00% of Java online submissions for Random Flip Matrix.
 * Memory Usage: 38.3 MB, less than 100.00% of Java online submissions for Random Flip Matrix.
 */
public class xLC_519_Random_Flip_Matrix
{
	Random rand = new Random();
	private HashMap<Integer, Integer> map = new HashMap<>();
	int total, rows, cols;

	public xLC_519_Random_Flip_Matrix(int n_rows, int n_cols) {
		rows = n_rows;
		cols = n_cols;
		total = rows*cols;
	}

	public int[] flip() {
		int i = rand.nextInt(total--);
		int x = map.getOrDefault(i, i);
		map.put(i, map.getOrDefault(total, total));
		map.put(total, x);
		return new int[]{x/cols, x%cols};
	}

	public void reset() {
		total = rows*cols;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_519_Random_Flip_Matrix solution = new xLC_519_Random_Flip_Matrix(5,5);
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
