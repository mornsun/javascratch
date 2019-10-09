/**
 * 
 */
package topcoder;

/**
 * 	Given an integer array arr and an integer k, modify the array by repeating it k times.
 *
 * For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
 *
 * Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.
 *
 * As the answer can be very large, return the answer modulo 10^9 + 7.
 *
 * Example 1:
 *
 * Input: arr = [1,2], k = 3
 * Output: 9
 *
 * Example 2:
 *
 * Input: arr = [1,-2,1], k = 5
 * Output: 2
 *
 * Example 3:
 *
 * Input: arr = [-1,-2], k = 7
 * Output: 0
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * 1 <= k <= 10^5
 * -10^4 <= arr[i] <= 10^4
 *
 * @author Chauncey
 * Runtime: 3 ms, faster than 100.00% of Java online submissions for K-Concatenation Maximum Sum.
 * Memory Usage: 49.7 MB, less than 100.00% of Java online submissions for K-Concatenation Maximum Sum.
 */
public class LC_1191_K_Concatenation_Maximum_Sum
{
	public int kConcatenationMaxSum(int[] arr, int k) {
		if (arr==null || arr.length==0)
			return 0;

		int maxIn = 0;
		int sum = 0;
		for (int n : arr) {
			sum += n;
			if (sum<0) {
				sum = 0;
				continue;
			}
			if (sum>maxIn)
				maxIn = sum;
		}

		int maxPre = 0;
		sum = 0;
		for (int n : arr) {
			sum += n;
			if (sum>maxPre) maxPre = sum;
		}

		int maxSuff = 0;
		sum = 0;
		for (int i=arr.length-1; i>=0; --i) {
			sum += arr[i];
			if (sum>maxSuff) maxSuff = sum;
		}

		long total = (long)sum*(k-2) + maxPre + maxSuff;
		int max = Math.max(maxIn, maxPre+maxSuff);
		return total>max ? (int)(total%1000000007) : max;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1191_K_Concatenation_Maximum_Sum solution = new LC_1191_K_Concatenation_Maximum_Sum();
        System.out.println(solution.kConcatenationMaxSum(new int[]{1,2}, 3)); //9
		System.out.println(solution.kConcatenationMaxSum(new int[]{1,-2,1}, 5)); //2
		System.out.println(solution.kConcatenationMaxSum(new int[]{-1,-2}, 7)); //0
		System.out.println(solution.kConcatenationMaxSum(new int[]{-9,13,4,-16,-12,-16,3,-7,5,-16,16,8,-1,-13,15,3}, 6)); //36
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
