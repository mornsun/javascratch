/**
 * 
 */
package topcoder;

import java.util.Arrays;

/**
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.
 *
 * Example 1:
 *
 * Input: 12
 * Output: 21
 *
 *
 * Example 2:
 *
 * Input: 21
 * Output: -1
 *
 * String
 *
 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Next Greater Element III.
 * Memory Usage: 33.2 MB, less than 10.00% of Java online submissions for Next Greater Element III.
 */
public class xLC_556_Next_Greater_Element_III
{
	//next_permutation
	public int nextGreaterElement(int n) {

		if (n == 0) return n;
		int[] bits = new int[16];
		int cnt = 0;
		while (n > 0) {
			bits[cnt++] = n % 10;
			n /= 10;
		}
		for (int i = 1; i < cnt; ++i) {
			if (bits[i] < bits[i - 1]) {
				int min = bits[i - 1];
				int min_j = i - 1;
				for (int j = 0; j < i - 1; ++j) {
					if (bits[j] > bits[i] && bits[j] < min) {
						min = bits[j];
						min_j = j;
					}
				}
				bits[min_j] = bits[i];
				bits[i] = min;
				Arrays.sort(bits, 0, i);
				//reverse order
				for (int j=i/2-1; j>=0; --j) {
					min = bits[j];
					bits[j] = bits[i-j-1];
					bits[i-j-1] = min;
				}

				long res = 0;
				for (int j=cnt-1; j>=0; --j) {
					res *= 10;
					res += bits[j];
				}
				return res>Integer.MAX_VALUE ? -1 : (int)res;
			}
		}
		return -1;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_556_Next_Greater_Element_III solution = new xLC_556_Next_Greater_Element_III();
        System.out.println(solution.nextGreaterElement(12)); //21
		System.out.println(solution.nextGreaterElement(230241)); //230412
		System.out.println(solution.nextGreaterElement(13542)); //14235
		System.out.println(solution.nextGreaterElement(1999999999)); //-1
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
