/**
 * 
 */
package topcoder;

/**
 * 	Given a positive integer K, you need find the smallest positive integer N such that N is divisible by K, and N only contains the digit 1.
 *
 * Return the length of N.  If there is no such N, return -1.
 *
 * Example 1:
 *
 * Input: 1
 * Output: 1
 * Explanation: The smallest answer is N = 1, which has length 1.
 *
 * Example 2:
 *
 * Input: 2
 * Output: -1
 * Explanation: There is no such positive integer N divisible by 2.
 *
 * Example 3:
 *
 * Input: 3
 * Output: 3
 * Explanation: The smallest answer is N = 111, which has length 3.
 *
 * Note:
 *
 * 1 <= K <= 10^5
 *
 * Hint 1:
 * 11111 = 1111 * 10 + 1 We only need to store remainders modulo K.
 * Hint 2:
 * If we never get a remainder of 0, why would that happen, and how would we know that?
 *
 * Math
 *
 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Difference Between Node and Ancestor.
 * Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Maximum Difference Between Node and Ancestor.
 */
public class xLC_1015_Smallest_Integer_Divisible_by_K
{
	public int smallestRepunitDivByK(int K) {

		if (K<=0) return 0;
		int N=1;
		boolean[] got = new boolean[K+1];
		int res = 0;
		while (true) {
			res++;
			N = N%K;
			if (N==0) return res;
			else {
				if (got[N]) return -1;
				got[N] = true;
				N = N*10+1;
			}
		}
//return res;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_1015_Smallest_Integer_Divisible_by_K solution = new xLC_1015_Smallest_Integer_Divisible_by_K();
		System.out.println(solution.smallestRepunitDivByK(1)); //1
		System.out.println(solution.smallestRepunitDivByK(2)); //-1
		System.out.println(solution.smallestRepunitDivByK(3)); //3
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
