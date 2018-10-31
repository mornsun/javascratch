/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given an array of integers A, consider all non-empty subsequences of A.

 For any sequence S, let the width of S be the difference between the maximum and minimum element of S.

 Return the sum of the widths of all subsequences of A.

 As the answer may be very large, return the answer modulo 10^9 + 7.

 Example 1:

 Input: [2,1,3]
 Output: 6
 Explanation:
 Subsequences are [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3].
 The corresponding widths are 0, 0, 0, 1, 1, 2, 2.
 The sum of these widths is 6.

 Note:

 1 <= A.length <= 20000
 1 <= A[i] <= 20000

 Related Topic
 Array, Math

 * @author Chauncey
 *
 */
public class LC_891_Sum_of_Subsequence_Widths
{
    public int sumSubseqWidths(int[] A)
    {
        if (A == null || A.length == 0) {
            return 0;
        }

        Arrays.sort(A);
        int sum = 0;
        for (int i = 0; i<A.length; ++i) {
            for (int j=i+1; j<A.length; ++j) {
                int n = j-i-1;
                int product = 1;
                if (n >= 30 ) { //1000000007) {
                    product <<= 30;
                    product %= 1000000007;
                    n -= 30;
                }
                for (int k=0; k<n; ++k) {
                    product <<= 1;
                    if (product > 1000000007) {
                        product %= 1000000007;
                    }
                }
                sum += (A[j] - A[i]) * product;
                if (sum > 1000000007) {
                    sum %= 1000000007;
                }
            }
        }
        return sum;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_891_Sum_of_Subsequence_Widths solution = new LC_891_Sum_of_Subsequence_Widths();
		System.out.println(solution.sumSubseqWidths(new int[]{2,1,3})); //6
        System.out.println(solution.sumSubseqWidths(new int[]{2,1})); //1
        System.out.println(solution.sumSubseqWidths(new int[]{5,69,89,92,31,16,25,45,63,40,16,56,24,40,75,82,40,12,50,62,92,44,67,38,92,22,91,24,26,21,100,42,23,56,64,43,95,76,84,79,89,4,16,94,16,77,92,9,30,13})); //857876214
		
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
