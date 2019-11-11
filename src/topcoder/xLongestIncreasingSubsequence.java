package topcoder;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?

Credits:
Special thanks to @pbrother for adding this problem and creating all test cases.

Subscribe to see which companies asked this question

Hide Tags Dynamic Programming Binary Search

 * @author Chauncey
 * Runtime: 1 ms, faster than 93.75% of Java online submissions for Longest Increasing Subsequence.
 * Memory Usage: 36.6 MB, less than 100.00% of Java online submissions for Longest Increasing Subsequence.
 *
 */
public class xLongestIncreasingSubsequence
{
	public int lengthOfLIS(int[] nums) {

		if (nums==null || nums.length==0) return 0;
		int N = nums.length;
		int[] dp = new int[N];
		int right = 0;

		for (int n : nums) {
			int lo = 0, hi = right;
			while (lo<hi) {
				int m = lo + (hi-lo>>1);
				if (n>dp[m]) {
					lo = m + 1;
				} else {
					hi = m;
				}
			}
			dp[lo] = n;
			if (lo==right) right++;
		}
		return right;
	}

    public int lengthOfLIS2(int[] nums) {
    	if (null == nums || nums.length == 0)
    		return 0;
    	int[] cnts = new int[nums.length];
    	int right = 0;
    	for (int n : nums) {
    		int lo = 0;
    		int hi = right-1;
    		while (lo <= hi) {
    			int m = lo+(hi-lo>>1);
    			if (cnts[m] < n) {
    				lo = m+1;
    			} else if (cnts[m] > n){
    				hi = m-1;
    			} else {
    				lo = m;
    				break;
    			}
    		}
    		if (lo < right) {
    			cnts[lo] = n;
    		} else {
    			cnts[right++] = n;
    		}
    	}
    	return right;
    }
    
    public int lengthOfLIS1(int[] nums) {
    	if (null == nums || nums.length == 0)
    		return 0;
    	int max = 1;
    	int[] cnts = new int[nums.length];
    	for (int i=0; i<nums.length; ++i) {
    		int cnt = 0;
    		for (int j=0; j<i; ++j) {
    			if (nums[j] < nums[i] && cnts[j] > cnt) cnt = cnts[j];
    		}
			cnts[i] = cnt + 1;
			if (cnts[i] > max) max = cnts[i];
    	}
    	return max;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		xLongestIncreasingSubsequence solution = new xLongestIncreasingSubsequence();
		//System.out.println(solution.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
		//2 4 5 6 7 12
		System.out.println(solution.lengthOfLIS(new int[]{3,5,6,2,5,4,19,5,6,7,12}));
	}

}
