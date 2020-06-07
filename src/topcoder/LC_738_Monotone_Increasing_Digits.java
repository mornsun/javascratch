/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 *  Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.
 *
 * (Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)
 *
 * Example 1:
 *
 * Input: N = 10
 * Output: 9
 *
 * Example 2:
 *
 * Input: N = 1234
 * Output: 1234
 *
 * Example 3:
 *
 * Input: N = 332
 * Output: 299
 *
 * Note: N is an integer in the range [0, 10^9].
 *
 * Greedy
 *
 * @author Chauncey
 * Runtime: 1 ms, faster than 91.18% of Java online submissions for Monotone Increasing Digits.
 * Memory Usage: 36.9 MB, less than 20.00% of Java online submissions for Monotone Increasing Digits.
 */
public class LC_738_Monotone_Increasing_Digits
{
    public int monotoneIncreasingDigits(int N) {
        if (N<10) return N;
        ArrayList<Integer> ds = new ArrayList<>();
        int n=N;

        while (n>0) {
            ds.add(n%10);
            n = n/10;
        }
        Collections.reverse(ds);

        n=ds.size();
        int i=1;
        for (; i<n; ++i) {
            if (ds.get(i)>=ds.get(i-1)) continue;
            else break;
        }
        if (i<n) {
            int k;
            for (k=i; k<n; ++k)
                ds.set(k, 9);
            for (k=i-1; k>0; --k) {
                if (ds.get(k)==ds.get(k-1)) {
                    ds.set(k, 9);
                } else {
                    break;
                }
            }
            ds.set(k, ds.get(k)-1);
        }

        n = 0;
        for (int d : ds) {
            n *= 10;
            n += d;
        }
        return n;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_738_Monotone_Increasing_Digits solution = new LC_738_Monotone_Increasing_Digits();
		System.out.println(solution.monotoneIncreasingDigits(10)); //9

        System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
