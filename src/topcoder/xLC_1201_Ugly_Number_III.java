/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 	Write a program to find the n-th ugly number.
 *
 * Ugly numbers are positive integers which are divisible by a or b or c.
 *
 * Example 1:
 *
 * Input: n = 3, a = 2, b = 3, c = 5
 * Output: 4
 * Explanation: The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.
 *
 * Example 2:
 *
 * Input: n = 4, a = 2, b = 3, c = 4
 * Output: 6
 * Explanation: The ugly numbers are 2, 3, 4, 6, 8, 9, 10, 12... The 4th is 6.
 *
 * Example 3:
 *
 * Input: n = 5, a = 2, b = 11, c = 13
 * Output: 10
 * Explanation: The ugly numbers are 2, 4, 6, 8, 10, 11, 12, 13... The 5th is 10.
 *
 * Example 4:
 *
 * Input: n = 1000000000, a = 2, b = 217983653, c = 336916467
 * Output: 1999999984
 *
 * Constraints:
 *
 * 1 <= n, a, b, c <= 10^9
 * 1 <= a * b * c <= 10^18
 * It's guaranteed that the result will be in range [1, 2 * 10^9]
 *
 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Ugly Number III.
 * Memory Usage: 33.2 MB, less than 100.00% of Java online submissions for Ugly Number III.
 */
public class xLC_1201_Ugly_Number_III
{
	public int nthUglyNumber(int n, int a, int b, int c) {
		int lo = 1, hi = 2000000000;
		long ab = (long)a*b/gcd(a, b);
		long bc = (long)b*c/gcd(b, c);
		long ac = (long)a*c/gcd(a, c);
		long abc = (long)a*bc/gcd(a, bc);
		while (lo<hi) {
			int m = lo + (hi-lo>>1);
			int cnt = (int)(m/a + m/b + m/c - m/ab - m/bc - m/ac + m/abc);
			if (cnt>=n)
				hi = m;
			else
				lo = m+1;
		}
		return lo;
	}

	public long gcd(long num1, long num2) {
		long max = Math.abs(num1);
		long min = Math.abs(num2);

		while (max > 0) {
			if (max < min) {
				long x = max;
				max = min;
				min = x;
			}
			max %= min;
		}

		return min;
	}

	public static long lcm(long number1, long number2) {
		if (number1 == 0 || number2 == 0) {
			return 0;
		}
		long absNumber1 = Math.abs(number1);
		long absNumber2 = Math.abs(number2);
		long absHigherNumber = Math.max(absNumber1, absNumber2);
		long absLowerNumber = Math.min(absNumber1, absNumber2);
		long lcm = absHigherNumber;
		while (lcm % absLowerNumber != 0) {
			lcm += absHigherNumber;
		}
		return lcm;
	}

		/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_1201_Ugly_Number_III solution = new xLC_1201_Ugly_Number_III();
        System.out.println(solution.nthUglyNumber(3, 2, 3, 5)); //4
		System.out.println(solution.nthUglyNumber(4, 2, 3, 4)); //6
		System.out.println(solution.nthUglyNumber(5, 2, 11, 13)); //10
		System.out.println(solution.nthUglyNumber(1000000000, 2, 217983653, 336916467)); //1999999984
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
