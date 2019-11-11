/**
 * 
 */
package topcoder;

/**
 * 	Given an unsorted array of integers, find the number of longest increasing subsequence.
 *
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
 * Example 2:
 * Input: [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
 * Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
 *
 * DP
 *
 * @author Chauncey
 * Runtime: 9 ms, faster than 55.75% of Java online submissions for Number of Longest Increasing Subsequence.
 * Memory Usage: 39.3 MB, less than 11.11% of Java online submissions for Number of Longest Increasing Subsequence.
 */
public class xLC_673_Number_of_Longest_Increasing_Subsequence
{
    public int findNumberOfLIS(int[] nums) {
        if (nums==null || nums.length==0) return 0;
        int N = nums.length;
        int[] lens = new int[N];
        int[] cnts = new int[N];
        int max = 0, res = 0;

        for (int i=0; i<N; ++i) {
            lens[i] = cnts[i] = 1;
            for (int j=0; j<i; ++j) {
                if (nums[i] > nums[j]) {
                    if (lens[i] < lens[j] + 1) {
                        lens[i] = lens[j] + 1;
                        cnts[i] = cnts[j];
                    } else if (lens[i] == lens[j] + 1) {
                        cnts[i] += cnts[j];
                    }
                }
            }
            if (max==lens[i]) res += cnts[i];
            else if (max<lens[i]) {
                max = lens[i];
                res = cnts[i];
            }
        }
        return res;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_673_Number_of_Longest_Increasing_Subsequence solution = new xLC_673_Number_of_Longest_Increasing_Subsequence();
        System.out.println(solution.findNumberOfLIS(new int[]{1,3,5,4,7})); // 2
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
