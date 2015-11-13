package topcoder;

import java.util.*;

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
 *
 */
public class LongestIncreasingSubsequence
{
    public int lengthOfLIS(int[] nums) {
    	if (null == nums || nums.length == 0)
    		return 0;
    	int max = 1;
    	//TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
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
		LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
		int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
		System.out.println(solution.lengthOfLIS(nums));
	}

}
