/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.HashSet;

/**
 * A sequence X_1, X_2, ..., X_n is fibonacci-like if:
 n >= 3
 X_i + X_{i+1} = X_{i+2} for all i + 2 <= n
 Given a strictly increasing array A of positive integers forming a sequence, find the length of the longest fibonacci-like subsequence of A.  If one does not exist, return 0.
 (Recall that a subsequence is derived from another sequence A by deleting any number of elements (including none) from A, without changing the order of the remaining elements.  For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].)


 Example 1:
 Input: [1,2,3,4,5,6,7,8]
 Output: 5
 Explanation:
 The longest subsequence that is fibonacci-like: [1,2,3,5,8].
 Example 2:
 Input: [1,3,7,11,12,14,18]
 Output: 3
 Explanation:
 The longest subsequence that is fibonacci-like:
 [1,11,12], [3,11,14] or [7,11,18].

 Note:
 3 <= A.length <= 1000
 1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
 (The time limit has been reduced by 50% for submissions in Java, C, and C++.)

 Related Topics
 Array, Dynamic Programming

 * @author Chauncey
 * beat 72.41%
 */
public class LC_873_Length_of_Longest_Fibonacci_Subsequence
{
    public int lenLongestFibSubseq(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i=2; i<A.length; ++i) {
            set.add(A[i]);
        }

        int max = 0;
        for (int i=0; i<A.length; ++i) {
            for (int j=i+1; j<A.length; ++j) {
                int sum = A[i]+A[j];
                if (set.contains(sum)) {
                    int cnt = 3;
                    int x1 = A[j];
                    int x2 = sum;
                    sum = x1+x2;
                    while (set.contains(sum)) {
                        ++cnt;
                        x1 = x2;
                        x2 = sum;
                        sum = x1+x2;
                        //System.out.println(x1+":"+x2+":"+sum);
                    }
                    max = Math.max(max, cnt);
                }
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

		LC_873_Length_of_Longest_Fibonacci_Subsequence solution = new LC_873_Length_of_Longest_Fibonacci_Subsequence();
		System.out.println(solution.lenLongestFibSubseq(new int[]{1,2,3,4,5,6,7,8})); //5
        System.out.println(solution.lenLongestFibSubseq(new int[]{1,3,7,11,12,14,18})); //3
        System.out.println(solution.lenLongestFibSubseq(new int[]{1,3,5})); //2

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
