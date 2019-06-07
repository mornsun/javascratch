/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	Given an array A of integers, return the length of the longest arithmetic subsequence in A.
 *
 * Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1, and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
 *
 * Example 1:
 *
 * Input: [3,6,9,12]
 * Output: 4
 * Explanation:
 * The whole array is an arithmetic sequence with steps of length = 3.
 * Example 2:
 *
 * Input: [9,4,7,2,10]
 * Output: 3
 * Explanation:
 * The longest arithmetic subsequence is [4,7,10].
 * Example 3:
 *
 * Input: [20,1,15,3,10,5,8]
 * Output: 4
 * Explanation:
 * The longest arithmetic subsequence is [20,15,10,5].
 *
 * Note:
 *
 * 2 <= A.length <= 2000
 * 0 <= A[i] <= 10000
 *
 * Dynamic Programming

 * @author Chauncey
 * Runtime: 326 ms, faster than 68.73%
 */
public class LC_1027_Longest_Arithmetic_Sequence
{
    public int longestArithSeqLength(int[] A) {
        Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();
        int res = 2;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                Map<Integer, Integer> m = dp.computeIfAbsent(A[j] - A[i], d -> new HashMap<>());
                m.put(j, m.getOrDefault(i, 1) + 1);
                res = Math.max(res, m.get(j));
            }
        }
        return res;
    }

    public int longestArithSeqLength1(int[] A) {

        if (A==null && A.length==0)
            return 0;

        HashMap<Integer, HashMap<Integer, Integer>> dp = new HashMap<Integer, HashMap<Integer, Integer>>();
        int max = 0;
        for (int i=1; i<A.length; ++i) {
            HashMap<Integer, Integer> curr = dp.get(i);
            if (curr == null) {
                curr = new HashMap<Integer, Integer>();
                dp.put(i, curr);
            }
            for (int j=0; j<i; ++j) {
                HashMap<Integer, Integer> prev = dp.get(j);
                if (prev==null || prev.isEmpty()) {
                    curr.put(A[i]-A[j], 1);
                    if (max==0)
                        max = 1;
                } else {
                    Integer prev_cnt = prev.get(A[i]-A[j]);
                    if (prev_cnt==null)
                        prev_cnt = 0;
                    curr.put(A[i]-A[j], prev_cnt+1);
                    if (max<=prev_cnt)
                        max = prev_cnt+1;
                }
            }
        }

        return max+1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1027_Longest_Arithmetic_Sequence solution = new LC_1027_Longest_Arithmetic_Sequence();
		System.out.println(solution.longestArithSeqLength(new int[]{12,28,13,6,34,36,53,24,29,2,23,0,22,25,53,34,23,50,35,43,53,11,48,56,44,53,31,6,31,57,46,6,17,42,48,28,5,24,0,14,43,12,21,6,30,37,16,56,19,45,51,10,22,38,39,23,8,29,60,18}));
        System.out.println(solution.longestArithSeqLength(new int[]{3,6,9,12}));
        System.out.println(solution.longestArithSeqLength(new int[]{9,4,7,2,10}));
        System.out.println(solution.longestArithSeqLength(new int[]{20,1,15,3,10,5,8}));

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
