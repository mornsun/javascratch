/**
 * 
 */
package topcoder;

import java.util.HashMap;
import java.util.Random;

/**
 * 	Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.
 *
 * Note:
 *
 * 1 <= w.length <= 10000
 * 1 <= w[i] <= 10^5
 * pickIndex will be called at most 10000 times.
 * Example 1:
 *
 * Input:
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output: [null,0]
 * Example 2:
 *
 * Input:
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output: [null,0,1,1,1,0]
 * Explanation of Input Syntax:
 *
 * The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.
 *
 * Binary Search, Random
 *
 * @author Chauncey
 * Runtime: 20 ms, faster than 92.61% of Java online submissions for Contiguous Array.
 * Memory Usage: 51.1 MB, less than 100.00% of Java online submissions for Contiguous Array.
 */
public class LC_528_Random_Pick_with_Weight
{
    private int[] arr;
    private int total;
    private Random rand = new Random();

    public LC_528_Random_Pick_with_Weight(int[] w) {
        arr = new int[w.length];
        total = 0;
        for (int i=0; i<w.length; ++i) {
            total += w[i];
            arr[i] = total;
        }
    }

    public int pickIndex() {
        int t = rand.nextInt(total);
        int lo = 0;
        int hi = arr.length-1;
        while (lo<hi) {
            int m = lo + (hi-lo>>1);
            if (t>=arr[m])
                lo = m+1;
            else
                hi = m;
        }
        return lo;
    }

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_528_Random_Pick_with_Weight solution = new LC_528_Random_Pick_with_Weight(new int[]{1,3});
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
