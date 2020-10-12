/**
 * 
 */
package topcoder;

/**
 * 	Given an integer n, you must transform it into 0 using the following operations any number of times:
 *
 *     Change the rightmost (0th) bit in the binary representation of n.
 *     Change the ith bit in the binary representation of n if the (i-1)th bit is set to 1 and the (i-2)th through 0th bits are set to 0.
 *
 * Return the minimum number of operations to transform n into 0.
 *
 * Example 1:
 *
 * Input: n = 0
 * Output: 0
 *
 * Example 2:
 *
 * Input: n = 3
 * Output: 2
 * Explanation: The binary representation of 3 is "11".
 * "11" -> "01" with the 2nd operation since the 0th bit is 1.
 * "01" -> "00" with the 1st operation.
 *
 * Example 3:
 *
 * Input: n = 6
 * Output: 4
 * Explanation: The binary representation of 6 is "110".
 * "110" -> "010" with the 2nd operation since the 1st bit is 1 and 0th through 0th bits are 0.
 * "010" -> "011" with the 1st operation.
 * "011" -> "001" with the 2nd operation since the 0th bit is 1.
 * "001" -> "000" with the 1st operation.
 *
 * Example 4:
 *
 * Input: n = 9
 * Output: 14
 *
 * Example 5:
 *
 * Input: n = 333
 * Output: 393
 *
 * Hint 1: The fastest way to convert n to zero is to remove all set bits starting from the leftmost one. Try some simple examples to learn the rule of how many steps are needed to remove one set bit.
 * Hint 2: consider n=2^k case first, then solve for all n.
 *
 * Constraints:
 *
 *     0 <= n <= 109
 *
 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Minimum One Bit Operations to Make Integers Zero.
 * Memory Usage: 35.7 MB, less than 7.37% of Java online submissions for Minimum One Bit Operations to Make Integers Zero.
 */
public class LC_1611_Minimum_One_Bit_Operations_to_Make_Integers_Zero
{
	public int minimumOneBitOperations(int n) {
		return minimumOneBitOperations(n, 0);
	}

	public int minimumOneBitOperations(int n, int res) {
		if (n==0)
			return res;
		int b=1;
		while ((b<<1) < n)
			b<<=1;
		return minimumOneBitOperations(n^(b|(b>>1)), res+b);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1611_Minimum_One_Bit_Operations_to_Make_Integers_Zero solution = new LC_1611_Minimum_One_Bit_Operations_to_Make_Integers_Zero();
        System.out.println(solution.minimumOneBitOperations(0)); //0
		System.out.println(solution.minimumOneBitOperations(3)); //2
		System.out.println(solution.minimumOneBitOperations(6)); //4
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
