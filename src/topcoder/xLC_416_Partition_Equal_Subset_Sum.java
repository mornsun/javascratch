/**
 * 
 */
package topcoder;

/**
 * 	Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * Note:
 *     Each of the array element will not exceed 100.
 *     The array size will not exceed 200.
 *
 * Example 1:
 *
 * Input: [1, 5, 11, 5]
 *
 * Output: true
 *
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * Example 2:
 *
 * Input: [1, 2, 3, 5]
 *
 * Output: false
 *
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 * Dynamic Programming
 * 
 * @author Chauncey
 * Runtime: 14 ms, faster than 63.49% of Java online submissions for Partition Equal Subset Sum.
 * Memory Usage: 38 MB, less than 50.79% of Java online submissions for Partition Equal Subset Sum.
 */
public class xLC_416_Partition_Equal_Subset_Sum
{
	public boolean canPartition(int[] nums) {
		if (nums==null || nums.length==0)
			return true;

		int total=0;
		for (int n : nums) {
			total+=n;
		}
		if ( (total & 1)!=0 ) return false;
		total = total/2;

		boolean[] dp = new boolean[total+1];
		dp[0] = true;
		for (int n : nums) {
			for (int i=total; i>0; --i) {
				if (i-n<0) continue;
				dp[i] = dp[i-n] || dp[i];
			}
		}
		return dp[total];

	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_416_Partition_Equal_Subset_Sum solution = new xLC_416_Partition_Equal_Subset_Sum();
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
