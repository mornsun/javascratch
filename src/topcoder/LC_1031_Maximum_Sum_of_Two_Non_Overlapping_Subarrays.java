/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 	Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)
 *
 * Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:
 *
 * 0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
 * 0 <= j < j + M - 1 < i < i + L - 1 < A.length.
 *
 * Example 1:
 *
 * Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
 * Output: 20
 * Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
 *
 * Example 2:
 *
 * Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
 * Output: 29
 * Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
 *
 * Example 3:
 *
 * Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
 * Output: 31
 * Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.
 *
 * Note:
 *
 * L >= 1
 * M >= 1
 * L + M <= A.length <= 1000
 * 0 <= A[i] <= 1000
 *
 * Array

 * @author Chauncey
 * Runtime: 3 ms, faster than 40.01%
 */
public class LC_1031_Maximum_Sum_of_Two_Non_Overlapping_Subarrays
{
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int res = 0, Lsum = 0, Lmax = 0, Msum = 0, Mmax = 0;
        for (int i = 0; i < A.length; ++i) {
            Msum += A[i];
            if (i - M >= 0) Msum -= A[i - M];
            if (i - M >= 0) Lsum += A[i - M];
            if (i - M - L >= 0) Lsum -= A[i - L - M];
            Lmax = Math.max(Lmax, Lsum);
            res = Math.max(res, Lmax + Msum);
        }
        Lsum = Lmax = Msum = Mmax = 0;
        for (int i = 0; i < A.length; ++i) {
            Lsum += A[i];
            if (i - L >= 0) Lsum -= A[i - L];
            if (i - L >= 0) Msum += A[i - L];
            if (i - M - L >= 0) Msum -= A[i - L - M];
            Mmax = Math.max(Mmax, Msum);
            res = Math.max(res, Mmax + Lsum);
        }
        return res;
    }

    public int maxSumTwoNoOverlap_deprecated(int[] A, int L, int M) {
        if (A==null || A.length==0 || L<=0 || M<=0 || A.length<L+M) return 0;

        int N = A.length;
        int[] sums = new int[N+1];
        for (int i=1; i<=N; ++i) {
            sums[i] = sums[i-1] + A[i-1];
        }

        int max = 0;
        for (int i=L; i<=N; ++i) {
            int l = sums[i] - sums[i-L];
            for (int j=M; j<=i-L; ++j) {
                int m = sums[j] - sums[j-M];
                max = Math.max(max, l+m);
            }
            for (int j=i+M; j<=N; ++j) {
                int m = sums[j] - sums[j-M];
                max = Math.max(max, l+m);
            }
        }

        return max;

    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1031_Maximum_Sum_of_Two_Non_Overlapping_Subarrays solution = new LC_1031_Maximum_Sum_of_Two_Non_Overlapping_Subarrays();
        System.out.println(solution.maxSumTwoNoOverlap(new int[]{0,6,5,2,2,5,1,9,4}, 1, 2)); //20
        System.out.println(solution.maxSumTwoNoOverlap(new int[]{8,20,6,2,20,17,6,3,20,8,12}, 5, 4)); //108

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
