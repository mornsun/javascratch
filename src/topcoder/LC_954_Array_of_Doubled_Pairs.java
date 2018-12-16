/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 	Given an array of integers A with even length, return true if and only if it is possible to reorder it such that A[2 * i + 1] = 2 * A[2 * i] for every 0 <= i < len(A) / 2.

 Example 1:

 Input: [3,1,3,6]
 Output: false

 Example 2:

 Input: [2,1,2,6]
 Output: false

 Example 3:

 Input: [4,-2,2,-4]
 Output: true
 Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].

 Example 4:

 Input: [1,2,4,16,8,4]
 Output: false


 Note:

 0 <= A.length <= 30000
 A.length is even
 -100000 <= A[i] <= 100000

 Related Topics:
 Array, Hash Table


 * @author Chauncey
 * beat 70.60%
 */
public class LC_954_Array_of_Doubled_Pairs
{
	public boolean canReorderDoubled(int[] A) {
		if (A==null || A.length==0)
			return true;
		if (A.length%2==1)
			return false;

		// count[x] = the number of occurrences of x in A
		HashMap<Integer, Integer> count = new HashMap();
		for (int x: A)
			count.put(x, count.getOrDefault(x, 0) + 1);

		// B = A as Integer[], sorted by absolute value
		Integer[] B = new Integer[A.length];
		for (int i = 0; i < A.length; ++i)
			B[i] = A[i];
		Arrays.sort(B, Comparator.comparingInt(Math::abs));

		for (int x: B) {
			// If this can't be consumed, skip
			if (count.get(x) == 0) continue;
			// If this doesn't have a doubled partner, the answer is false
			if (count.getOrDefault(2*x, 0) <= 0) return false;

			// Write x, 2*x
			count.put(x, count.get(x) - 1);
			count.put(2*x, count.get(2*x) - 1);
		}

		// If we have written everything, the answer is true
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_954_Array_of_Doubled_Pairs solution = new LC_954_Array_of_Doubled_Pairs();
		System.out.println(solution.canReorderDoubled(new int[]{})); //true
		System.out.println(solution.canReorderDoubled(new int[]{3,1,3,6})); //false
		System.out.println(solution.canReorderDoubled(new int[]{2,1,2,6})); //false
		System.out.println(solution.canReorderDoubled(new int[]{4,-2,2,-4})); //true
		System.out.println(solution.canReorderDoubled(new int[]{1,2,4,16,8,4})); //false
		System.out.println(solution.canReorderDoubled(new int[]{-2,-2,1,-2,-1,2})); //false
		System.out.println(solution.canReorderDoubled(new int[]{1,2,1,-8,8,-4,4,-4,2,-2})); //true
		System.out.println(solution.canReorderDoubled(new int[]{8,16,32,4})); //true

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
