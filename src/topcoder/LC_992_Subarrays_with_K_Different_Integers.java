/**
 * 
 */
package topcoder;

/**
 * 	Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.

 (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

 Return the number of good subarrays of A.

 Example 1:

 Input: A = [1,2,1,2,3], K = 2
 Output: 7
 Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].

 Example 2:

 Input: A = [1,2,1,3,4], K = 3
 Output: 3
 Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].

 Note:

 1 <= A.length <= 20000
 1 <= A[i] <= A.length
 1 <= K <= A.length

 Related Topics
 Two Pointers

 * @author Chauncey
 * Runtime: 7 ms, faster than 92.02% of Java online submissions for Subarray Sums Divisible by K.
 * Memory Usage: 43.7 MB, less than 29.41% of Java online submissions for Subarray Sums Divisible by K.
 */
public class LC_992_Subarrays_with_K_Different_Integers
{
	public int subarraysWithKDistinct(int[] A, int K) {
		return 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_992_Subarrays_with_K_Different_Integers solution = new LC_992_Subarrays_with_K_Different_Integers();
		System.out.println(solution.subarraysWithKDistinct(new int[]{1,2,1,2,3}, 2)); //7
		System.out.println(solution.subarraysWithKDistinct(new int[]{1,2,1,3,4}, 3)); //3

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
