/**
 * 
 */
package topcoder;

import java.util.ArrayList;

/**
 * 	Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
 Since the answer may be large, return the answer modulo 10^9 + 7.

 Example 1:

 Input: [3,1,2,4]
 Output: 17
 Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.

 Note:

 1 <= A.length <= 30000
 1 <= A[i] <= 30000

 Related Topics
 Stack, Array

 * @author Chauncey
 * Runtime: 32 ms, faster than 91.19%
 */
public class LC_907_Sum_of_Subarray_Minimums
{
	private final static int MOD = 1_000_000_007;

	public int sumSubarrayMins(int[] A) {
		if (A==null || A.length==0)
			return 0;

		ArrayList<int[]> stack = new ArrayList<>();
		stack.add(new int[]{A[0], 0, A[0]});

		int ret = A[0];

		for (int i=1; i<A.length; ++i) {

			while (!stack.isEmpty() && stack.get(stack.size()-1)[0] >= A[i])
				stack.remove(stack.size()-1);

			int sum = 0;
			if (!stack.isEmpty()) {
				int[] lst = stack.get(stack.size()-1);
				sum = lst[2] + A[i] * (i - lst[1]);
			} else {
				sum = A[i] * (i + 1);
			}
			ret = (ret + sum) % MOD;
			stack.add(new int[]{A[i], i, sum});
			//System.out.println(last+" x "+(last_i + 1));
		}

		return ret;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_907_Sum_of_Subarray_Minimums solution = new LC_907_Sum_of_Subarray_Minimums();
		System.out.println(solution.sumSubarrayMins(new int[]{3,1,2,4})); //17

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
