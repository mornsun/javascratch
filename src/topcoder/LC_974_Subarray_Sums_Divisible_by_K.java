/**
 * 
 */
package topcoder;

/**
 * 	Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.

 Example 1:

 Input: A = [4,5,0,-2,-3,1], K = 5
 Output: 7
 Explanation: There are 7 subarrays with a sum divisible by K = 5:
 [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

 Note:

 1 <= A.length <= 30000
 -10000 <= A[i] <= 10000
 2 <= K <= 10000

 Related Topics
 Array

 * @author Chauncey
 * Runtime: 7 ms, faster than 92.02% of Java online submissions for Subarray Sums Divisible by K.
 * Memory Usage: 43.7 MB, less than 29.41% of Java online submissions for Subarray Sums Divisible by K.
 */
public class LC_974_Subarray_Sums_Divisible_by_K
{
	public int subarraysDivByK(int[] A, int K) {
		if (A==null && A.length==0)
			return 0;

		int[] p = new int[A.length+1];
		for (int i=0; i<A.length; ++i) {
			p[i+1] = p[i] + A[i];
		}

		int[] counts = new int[K];
		for (int n : p) {
			counts[(n%K+K)%K]++;
		}

		int res = 0;
		for (int n : counts) {
			res += (n * (n-1)) >> 1;
		}

		return res;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_974_Subarray_Sums_Divisible_by_K solution = new LC_974_Subarray_Sums_Divisible_by_K();
		System.out.println(solution.subarraysDivByK(new int[]{4,5,0,-2,-3,1}, 5)); //7

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
