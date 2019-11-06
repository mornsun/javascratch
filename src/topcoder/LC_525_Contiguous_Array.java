/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 	Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 *
 * Example 1:
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 * Example 2:
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * Note: The length of the given binary array will not exceed 50,000.
 *
 * HashMap
 *
 * @author Chauncey
 * Runtime: 20 ms, faster than 92.61% of Java online submissions for Contiguous Array.
 * Memory Usage: 51.1 MB, less than 100.00% of Java online submissions for Contiguous Array.
 */
public class LC_525_Contiguous_Array
{
    public int findMaxLength(int[] nums) {

        if (nums==null || nums.length==0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int max = 0;
        int sum = 0;
        for (int i=0; i<nums.length; ++i) {
            sum += nums[i] == 0 ? -1 : 1;
            Integer prev = map.get(sum);
            if (prev == null) {
                map.put(sum, i);
            } else {
                max = Math.max(max, i-prev);
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

		LC_525_Contiguous_Array solution = new LC_525_Contiguous_Array();
        System.out.println(solution.findMaxLength(new int[]{0,1})); //2
        System.out.println(solution.findMaxLength(new int[]{0,1,0})); //2
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
