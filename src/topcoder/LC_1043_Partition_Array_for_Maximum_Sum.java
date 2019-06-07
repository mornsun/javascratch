/**
 * 
 */
package topcoder;

/**
 * 	Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.  After partitioning, each subarray has their values changed to become the maximum value of that subarray.
 *
 * Return the largest sum of the given array after partitioning.
 *
 * Example 1:
 *
 * Input: A = [1,15,7,9,2,5,10], K = 3
 * Output: 84
 * Explanation: A becomes [15,15,15,9,10,10,10]
 *
 * Note:
 *
 * 1 <= K <= A.length <= 500
 * 0 <= A[i] <= 10^6
 *
 * Graph
 *
 * Hint 1:
 * Think dynamic programming: dp[i] will be the answer for array A[0], ..., A[i-1].
 * Hint 2:
 * For j = 1 .. k that keeps everything in bounds, dp[i] is the maximum of dp[i-j] + max(A[i-1], ..., A[i-j]) * j .
 *
 * @author Chauncey
 * Runtime: 4 ms, faster than 95.80%
 */
public class LC_1043_Partition_Array_for_Maximum_Sum
{
    public int maxSumAfterPartitioning(int[] A, int K) {
        if (A==null || A.length==0 || K<=0)
            return 0;

        int[] dp = new int[A.length];
        int max = 0;
        for (int i=0; i<K && i<A.length; ++i) {
            max = Math.max(max, A[i]);
            dp[i] = max*(i+1);
        }
        for (int i=K; i<A.length; ++i) {
            max = 0;
            for (int k=1; k<=K; ++k) {
                max = Math.max(max, A[i-k+1]);
                dp[i] = Math.max(dp[i], dp[i-k]+max*k);
            }
        }

        return dp[A.length-1];
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1043_Partition_Array_for_Maximum_Sum solution = new LC_1043_Partition_Array_for_Maximum_Sum();
		System.out.println(solution.maxSumAfterPartitioning(new int[]{1,15,7,9,2,5,10}, 3)); //84

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
